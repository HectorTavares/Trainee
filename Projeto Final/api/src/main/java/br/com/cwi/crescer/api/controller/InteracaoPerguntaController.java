package br.com.cwi.crescer.api.controller;


import br.com.cwi.crescer.api.representation.interacao.pergunta.InteracaoPerguntaResponse;
import br.com.cwi.crescer.api.service.InteracaoPerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/interacaoPergunta")
public class InteracaoPerguntaController {

    @Autowired
    InteracaoPerguntaService service;


    @PostMapping("/positiva")
    public void interagirPositivamente(@RequestParam("idPergunta") Long idPergunta, @RequestHeader("authorization") String token) {

        service.avaliarPositivamentePergunta(token, idPergunta);
    }

    @PostMapping("/negativa")
    public void interagirNegativamente(@RequestParam("idPergunta") Long idPergunta, @RequestHeader("authorization") String token) {

        service.avaliarNegativamentePergunta(token, idPergunta);
    }

    @GetMapping("/relevancia")
    public Integer pegarRelevanciaperguntaPorId(@RequestHeader("authorization") String token, @RequestParam("idPergunta") Long idPergunta) {

        return service.pegarRelevanciaPorId(idPergunta);
    }

    @GetMapping("/interacaoUsuario")
    public InteracaoPerguntaResponse pegarInteracaoDoUsuario(@RequestHeader("authorization") String token, @RequestParam("idPergunta") Long idPergunta) {

        return service.interacaoUsuario(idPergunta, token);
    }

}
