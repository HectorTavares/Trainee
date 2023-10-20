package br.com.cwi.crescer.melevaai.controller;


import br.com.cwi.crescer.melevaai.exception.*;


import br.com.cwi.crescer.melevaai.representation.response.MotoristaResponse;
import br.com.cwi.crescer.melevaai.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;





@RestController
@RequestMapping("/private/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MotoristaResponse> listarMotoristas() {

        return motoristaService.listarMotoristas();
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public MotoristaResponse getMotoristaByCpf(@PathVariable String cpf)
        throws MotoristaNaoCadastradoException {

        return motoristaService.consultarMotorista(cpf);
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public void excluirMotorista(@PathVariable String cpf) throws MotoristaNaoCadastradoException {

        motoristaService.excluirMotorista(cpf);
    }

    @PutMapping("/{cpf}/conta-virtual")
    @ResponseStatus(HttpStatus.OK)
    public void sacar(@PathVariable String cpf, @RequestParam(value = "valor") double valor) throws MotoristaNaoCadastradoException {
      motoristaService.sacar(valor,cpf);
    }

}

