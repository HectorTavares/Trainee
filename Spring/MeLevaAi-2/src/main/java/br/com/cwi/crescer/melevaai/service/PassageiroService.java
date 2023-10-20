package br.com.cwi.crescer.melevaai.service;

import static br.com.cwi.crescer.melevaai.mapper.PassageiroMapper.toDomain;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.List;
import java.util.stream.Collectors;

import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.exception.IdadeMinimaException;
import br.com.cwi.crescer.melevaai.mapper.PassageiroMapper;
import br.com.cwi.crescer.melevaai.representation.request.PassageiroCompletoRequest;
import br.com.cwi.crescer.melevaai.representation.response.PassageiroCompletoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.melevaai.domain.Passageiro;
import br.com.cwi.crescer.melevaai.exception.PassageiroJaCadastradoException;
import br.com.cwi.crescer.melevaai.exception.PassageiroNaoCadastradoException;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;


@Service
public class PassageiroService {

    @Autowired
    private PassageiroRepository repository;


    public void criarPassageiro(PassageiroCompletoRequest request) throws PassageiroJaCadastradoException, IdadeMinimaException {

        Passageiro passageiro = toDomain(request);

        Passageiro passageiroRepository =  repository.findByCpf(passageiro.getCpf());

        if (nonNull(passageiroRepository)) {
            throw new PassageiroJaCadastradoException("Passageiro Já cadastrado");
        }

        passageiro.validaIdadeMinima();

        repository.save(passageiro);

    }

    public List<PassageiroCompletoResponse> listarTodosPassageiros() {
        List<Passageiro> passageiros = repository.findAll();

        return passageiros.stream()
                .map(PassageiroMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void deletarPassageiro(String cpf) throws PassageiroNaoCadastradoException {
        Passageiro passageiro = consultarPassageiroPorCpf(cpf);

        repository.delete(passageiro);
    }

    public void depositar(double valor,String cpf) throws PassageiroNaoCadastradoException {

        Passageiro passageiro = consultarPassageiroPorCpf(cpf);


        passageiro.depositar(valor);

        repository.save(passageiro);
    }

    public Passageiro consultarPassageiroPorCpf(final String cpf) throws PassageiroNaoCadastradoException {
        final Passageiro passageiro = repository.findByCpf(new CPF(cpf));

        if (isNull(passageiro)) {
            throw new PassageiroNaoCadastradoException("Motorista não cadastrado");
        }

        return passageiro;
    }


    public PassageiroCompletoResponse consultarPassageiroResponsePorCpf(final String cpf)
            throws PassageiroNaoCadastradoException {

        final Passageiro passageiro = consultarPassageiroPorCpf(cpf);

        return PassageiroMapper.toResponse(passageiro);
    }


}

