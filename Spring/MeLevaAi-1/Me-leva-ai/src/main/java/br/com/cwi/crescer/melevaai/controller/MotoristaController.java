package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.domain.Passageiro;
import br.com.cwi.crescer.melevaai.exception.*;

import br.com.cwi.crescer.melevaai.mapper.MotoristaMapper;
import br.com.cwi.crescer.melevaai.representation.CadastraMotoristaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import static br.com.cwi.crescer.melevaai.controller.VeiculoController.isProprietario;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    private static List<Motorista> motoristas = new ArrayList<>();
    private static MotoristaMapper motoristaMapper = new MotoristaMapper();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarMotorista(@RequestBody @Valid CadastraMotoristaRequest request)
            throws MotoristaJaCadastradoException, IdadeMinimaException {

        Motorista motorista = motoristaMapper.toDomain(request);

        motorista.validaIdadeMinima();

        if (buscarMotoristaPeloCpf(motorista.getCpf().getNumero()) != null) {
            throw new MotoristaJaCadastradoException("Motorista já cadastrado");
        }
        if(motorista.getCnh().isVencida()) {
            throw new ValidacaoNegocioException("CNH Vencida");
        }

        motorista.setId(motoristas.size()+1);
        motoristas.add(motorista);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Motorista> listarMotoristas() {
        return motoristas;
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Motorista getMotoristaByCpf(@PathVariable String cpf)
        throws MotoristaNaoCadastradoException {

        Motorista motorista = buscarMotoristaPeloCpf(cpf);

        if (motorista == null) {
            throw new MotoristaNaoCadastradoException("Motorista não cadastrado");
        }

        return motorista;
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirMotorista(@PathVariable String cpf) throws MotoristaNaoCadastradoException {

        Motorista motorista = buscarMotoristaPeloCpf(cpf);

        if (motorista == null) {
            throw new MotoristaNaoCadastradoException("Motorista não cadastrado");
        }

        if(isProprietario(cpf)){
            throw new ValidacaoNegocioException("Não pode ser excluido pois está vinculado a um veiculo");
        }

        motoristas.remove(motorista);
    }

    @PutMapping("/{cpf}/conta-virtual")
    @ResponseStatus(HttpStatus.OK)
    public void sacar(@PathVariable String cpf, @RequestParam(value = "valor") double valor) throws PassageiroNaoCadastradoException, MotoristaNaoCadastradoException {
        Motorista motorista = buscarMotoristaPeloCpf(cpf);

        if (motorista == null){
            throw new MotoristaNaoCadastradoException("Motorista não cadastrado");
        }

        motorista.sacar(valor);
    }

    public static Motorista buscarMotoristaPeloCpf(String cpf) {
        for (Motorista m : motoristas) {
            if (m.getCpf().getNumero().equals(cpf)) {
                return m;
            }
        }
        return null;
    }
}

