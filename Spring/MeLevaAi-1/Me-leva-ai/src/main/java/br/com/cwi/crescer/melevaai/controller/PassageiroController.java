package br.com.cwi.crescer.melevaai.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.exception.IdadeMinimaException;
import br.com.cwi.crescer.melevaai.exception.MotoristaNaoCadastradoException;
import br.com.cwi.crescer.melevaai.exception.PassageiroNaoCadastradoException;
import br.com.cwi.crescer.melevaai.mapper.PassageiroMapper;
import br.com.cwi.crescer.melevaai.representation.PassageiroCompletoRequest;
import br.com.cwi.crescer.melevaai.representation.PassageiroCompletoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.cwi.crescer.melevaai.domain.Passageiro;
import br.com.cwi.crescer.melevaai.exception.PassageiroJaCadastradoException;

import javax.validation.Valid;

@RestController
@RequestMapping("/passageiros")
public class PassageiroController {
    private static final List<Passageiro> passageiros = new ArrayList<>();
    private static final PassageiroMapper passageiroMapper = new PassageiroMapper();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarPassageiro(@RequestBody @Valid PassageiroCompletoRequest request) throws PassageiroJaCadastradoException, IdadeMinimaException {

        Passageiro passageiro = PassageiroMapper.toDomain(request);

        passageiro.validaIdadeMinima();

        if (buscarPassageiroPorCpf(passageiro.getCpf().getNumero()) != null) {
            throw new PassageiroJaCadastradoException("Passageiro Já cadastrado");
        }

        passageiro.setId(passageiros.size() + 1);
        passageiros.add(passageiro);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PassageiroCompletoResponse> listarPassageiros() {

        return passageiros.stream()
                .map(passageiroMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{cpf}/conta-virtual")
    @ResponseStatus(HttpStatus.OK)
    public void depositar(@PathVariable String cpf, @RequestParam(value = "valor") double valor) throws PassageiroNaoCadastradoException {
        Passageiro passageiro = buscarPassageiroPorCpf(cpf);

        if (passageiro == null){
            throw new PassageiroNaoCadastradoException("Passageiro não cadastrado");
        }

        passageiro.depositar(valor);
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Passageiro getPassageiroByCpf(@PathVariable String cpf) throws PassageiroNaoCadastradoException {

        Passageiro passageiro = buscarPassageiroPorCpf(cpf);

        if (passageiro == null) {
            throw new PassageiroNaoCadastradoException("Passageiro não cadastrado");
        }

        return passageiro;
    }

    public static Passageiro buscarPassageiroPorCpf(String cpf) {
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getCpf().getNumero().equals(cpf)) {
                return passageiro;
            }
        }
        return null;
    }
}
