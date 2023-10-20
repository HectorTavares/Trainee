package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.exception.IdadeMinimaException;
import br.com.cwi.crescer.melevaai.exception.MotoristaJaCadastradoException;
import br.com.cwi.crescer.melevaai.representation.request.CadastraMotoristaRequest;
import br.com.cwi.crescer.melevaai.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/motoristas")
public class PublicoMotoristaController {


    @Autowired
    private MotoristaService motoristaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarMotorista(@RequestBody @Valid CadastraMotoristaRequest request)
            throws MotoristaJaCadastradoException, IdadeMinimaException {

        motoristaService.criarMotorista(request);
    }

}
