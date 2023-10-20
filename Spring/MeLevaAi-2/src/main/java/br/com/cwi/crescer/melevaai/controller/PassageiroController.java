package br.com.cwi.crescer.melevaai.controller;

import java.util.List;

import br.com.cwi.crescer.melevaai.exception.PassageiroNaoCadastradoException;
import br.com.cwi.crescer.melevaai.representation.response.PassageiroCompletoResponse;
import br.com.cwi.crescer.melevaai.service.PassageiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/privado/passageiros")
public class PassageiroController {

    @Autowired
    PassageiroService passageiroService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PassageiroCompletoResponse> listarPassageiros() {

        return passageiroService.listarTodosPassageiros();
    }

    @PutMapping("/{cpf}/conta-virtual")
    @ResponseStatus(HttpStatus.OK)
    public void depositar(@PathVariable String cpf, @RequestParam(value = "valor") double valor) throws PassageiroNaoCadastradoException {

        passageiroService.depositar(valor, cpf);
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public PassageiroCompletoResponse getPassageiroByCpf(@PathVariable String cpf) throws PassageiroNaoCadastradoException {



        return passageiroService.consultarPassageiroResponsePorCpf(cpf);
    }


}
