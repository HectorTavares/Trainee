package br.com.cwi.crescer.melevaai.controller;

import java.util.List;

import br.com.cwi.crescer.melevaai.exception.CategoriaVeiculoCnhException;
import br.com.cwi.crescer.melevaai.exception.MotoristaNaoCadastradoException;

import br.com.cwi.crescer.melevaai.representation.request.VeiculoCompletoRequest;
import br.com.cwi.crescer.melevaai.representation.response.VeiculoCompletoResponse;
import br.com.cwi.crescer.melevaai.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/privado/veiculos")
public class VeiculoController {

    @Autowired
    VeiculoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarVeiculo(@RequestBody VeiculoCompletoRequest request) throws MotoristaNaoCadastradoException, CategoriaVeiculoCnhException {
        service.cadastrarVeiculo(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculoCompletoResponse> listarVeiculos() {
        return service.listarVeiculos();
    }

}


