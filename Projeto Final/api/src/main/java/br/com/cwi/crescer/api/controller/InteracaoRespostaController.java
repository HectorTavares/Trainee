package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.representation.interacao.resposta.InteracaoRespostaResponse;
import br.com.cwi.crescer.api.service.InteracaoRespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interacaoResposta")
public class InteracaoRespostaController {

    @Autowired
    InteracaoRespostaService service;


    @PostMapping("/positiva")
    public void interagirPositivamente(@RequestParam("idResposta") Long idResposta, @RequestHeader("authorization") String token) {

        service.avaliarPositivamenteResposta(token, idResposta);
    }

    @PostMapping("/negativa")
    public void interagirNegativamente(@RequestParam("idResposta") Long idResposta, @RequestHeader("authorization") String token) {

        service.avaliarNegativamenteResposta(token, idResposta);
    }

    @GetMapping("/relevancia")
    public Integer pegarRelevanciaperguntaPorId(@RequestHeader("authorization") String token, @RequestParam("idResposta") Long idResposta) {

        return service.pegarRelevanciaPorId(idResposta);
    }

    @GetMapping("/interacaoUsuario")
    public InteracaoRespostaResponse pegarInteracaoDoUsuario(@RequestHeader("authorization") String token, @RequestParam("idResposta") Long idResposta) {

        return service.interacaoUsuario(idResposta, token);
    }

}
