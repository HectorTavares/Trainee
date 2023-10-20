package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.exception.IdadeMinimaException;
import br.com.cwi.crescer.melevaai.exception.PassageiroJaCadastradoException;
import br.com.cwi.crescer.melevaai.representation.request.PassageiroCompletoRequest;
import br.com.cwi.crescer.melevaai.service.PassageiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/publico/passageiros")
public class PublicoPassageiroController {

    @Autowired
    PassageiroService passageiroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarPassageiro(@RequestBody @Valid PassageiroCompletoRequest request) throws PassageiroJaCadastradoException, IdadeMinimaException {

        passageiroService.criarPassageiro(request);
    }
}
