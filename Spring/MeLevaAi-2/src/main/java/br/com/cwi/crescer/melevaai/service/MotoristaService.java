package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.exception.MotoristaJaCadastradoException;
import br.com.cwi.crescer.melevaai.exception.MotoristaNaoCadastradoException;
import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.melevaai.mapper.MotoristaMapper;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.representation.request.CadastraMotoristaRequest;
import br.com.cwi.crescer.melevaai.representation.response.MotoristaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository repository;

    public void criarMotorista(CadastraMotoristaRequest request) throws MotoristaJaCadastradoException {

        Motorista motorista = MotoristaMapper.toDomain(request);

        if (repository.findByCpf(motorista.getCpf()) != null) {
            throw new MotoristaJaCadastradoException("Motorista já cadastrado");
        }

        if(motorista.getCnh().isVencida()) {
            throw new ValidacaoNegocioException("CNH Vencida");
        }

        repository.save(motorista);
    }

    public List<MotoristaResponse> listarMotoristas() {
        return repository.findAll().stream()
                .map(MotoristaMapper::toResponse)
                .collect(Collectors.toList());
    }


    public Motorista consultarMotoristaPorCpf(final String cpf)  throws MotoristaNaoCadastradoException{
        existeMotorista(cpf);

        return repository.findByCpf(new CPF(cpf));
    }

    public MotoristaResponse consultarMotorista(final String cpf)
            throws MotoristaNaoCadastradoException {

        existeMotorista(cpf);

        final Motorista motorista = repository.findByCpf(new CPF(cpf));

        return MotoristaMapper.toResponse(motorista);
    }

    public void excluirMotorista(final String cpf) throws MotoristaNaoCadastradoException {

        existeMotorista(cpf);

        Motorista motorista = repository.findByCpf(new CPF(cpf));

        repository.delete(motorista);
    }

    public void sacar(double valor,String cpf) throws MotoristaNaoCadastradoException {

        Motorista motorista = repository.findByCpf(new CPF(cpf));

        existeMotorista(cpf);

        motorista.sacar(valor);

        repository.save(motorista);
    }




    public void existeMotorista(String cpf) throws MotoristaNaoCadastradoException {

        if (!repository.existsByCpf(new CPF(cpf))){
            throw new MotoristaNaoCadastradoException("Motorista não cadastrado");
        }
    }
}

