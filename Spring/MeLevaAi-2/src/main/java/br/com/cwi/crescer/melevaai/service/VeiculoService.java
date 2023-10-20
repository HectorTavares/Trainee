package br.com.cwi.crescer.melevaai.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.representation.request.VeiculoCompletoRequest;
import br.com.cwi.crescer.melevaai.representation.response.VeiculoCompletoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.domain.Veiculo;
import br.com.cwi.crescer.melevaai.exception.MotoristaNaoCadastradoException;
import br.com.cwi.crescer.melevaai.mapper.VeiculoMapper;
import br.com.cwi.crescer.melevaai.repository.VeiculoRepository;

@Service
public class VeiculoService {

    private VeiculoMapper mapper = new VeiculoMapper();

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private MotoristaService motoristaService;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public void cadastrarVeiculo(VeiculoCompletoRequest request) throws MotoristaNaoCadastradoException {

        Motorista motorista = motoristaService.consultarMotoristaPorCpf(request.getCpf());

        Veiculo veiculo = VeiculoMapper.toDomain(request);

        veiculo.setProprietario(motorista);

        veiculo.setId(veiculoRepository.findAll().size() + 1);

        veiculoRepository.save(veiculo);
    }

    public Veiculo pegarVeiculoDisponivel() {
        List<Veiculo> veiculosDisponiveis = veiculoRepository.findAll().stream()
                .filter(v -> !v.getProprietario().isEmCorrida())
                .collect(Collectors.toList());

        return veiculosDisponiveis.get((int) (Math.random() * veiculoRepository.findAll().size() - 1));

    }

    public List<VeiculoCompletoResponse> listarVeiculos() {

        return veiculoRepository.
                findAll().
                stream().
                map(VeiculoMapper::toResponse).
                collect(Collectors.toList());
    }


}
