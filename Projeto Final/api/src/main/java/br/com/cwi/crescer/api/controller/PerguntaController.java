package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.representation.pergunta.PerguntaRequest;
import br.com.cwi.crescer.api.representation.pergunta.PerguntaResponse;
import br.com.cwi.crescer.api.representation.pergunta.PesquisaPerguntaRequest;
import br.com.cwi.crescer.api.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @Autowired
    PerguntaService service;


    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.OK)
    public void criarPergunta(@RequestHeader("authorization") String token, @RequestBody PerguntaRequest request) {

        service.criarPergunta(request, token);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public Page<PerguntaResponse> listarTodas(@RequestHeader("authorization") String token, Pageable pageable) {
        return service.listarTodasPerguntas(pageable);
    }

    @GetMapping("/buscarPergunta")
    @ResponseStatus(HttpStatus.OK)
    public PerguntaResponse buscarPergunta(@RequestHeader("authorization") String token, @RequestParam("idPergunta") Long idPergunta) {
        return service.buscarPerguntaResponsePorId(idPergunta);
    }

    @PostMapping("/pesquisar")
    @ResponseStatus(HttpStatus.OK)
    public Page<PerguntaResponse> pesquisarPerguntas(@RequestHeader("authorization") String token, @RequestBody PesquisaPerguntaRequest request, Pageable pageable) {
        return service.pesquisarPerguntas(request.getBusca(), request.getTags(), pageable);
    }
}
