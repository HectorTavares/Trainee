package br.com.cwi.crescer.melevaai.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cwi.crescer.melevaai.exception.CategoriaVeiculoCnhException;
import br.com.cwi.crescer.melevaai.exception.MotoristaNaoCadastradoException;
import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.melevaai.mapper.VeiculoMapper;
import br.com.cwi.crescer.melevaai.representation.VeiculoCompletoRequest;
import br.com.cwi.crescer.melevaai.representation.VeiculoCompletoRequestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.domain.Veiculo;

import static br.com.cwi.crescer.melevaai.controller.MotoristaController.buscarMotoristaPeloCpf;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    public static final List<Veiculo> veiculos = new ArrayList<>();
    private static final VeiculoMapper veiculoMapper = new VeiculoMapper();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarVeiculo(@RequestBody VeiculoCompletoRequest request) throws MotoristaNaoCadastradoException, CategoriaVeiculoCnhException {

        Motorista motorista = buscarMotoristaPeloCpf(request.getCpfProprietario());

        if(motorista == null) {
            throw new MotoristaNaoCadastradoException("Motorista não cadastrado");
        }

        Veiculo veiculo = VeiculoMapper.toDomain(request);
        veiculo.setProprietario(motorista);

        veiculo.validaCategoriaVeiculoCnh();

        if (existeVeiculo(veiculo)) {
            throw new ValidacaoNegocioException("Já existe este veiculo");
        }

        veiculo.setId(veiculos.size()+1);
        veiculos.add(veiculo);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculoCompletoRequestResponse> listarVeiculos() {

        return veiculos.stream()
                .map(veiculoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static boolean existeVeiculo(Veiculo veiculo) {
        for (Veiculo veic: veiculos) {
            if(veic.getPlaca().equals(veiculo.getPlaca())){
                return true;
            }
        }
        return false;
    }

    public static boolean isProprietario(String cpf) {
        for (Veiculo veiculo: veiculos) {
            if(veiculo.getProprietario().getCpf().getNumero().equals(cpf)){
                return true;
            }
        }
        return false;
    }
}


