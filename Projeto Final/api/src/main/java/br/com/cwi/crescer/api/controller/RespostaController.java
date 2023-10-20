package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.representation.resposta.RespostaRequest;
import br.com.cwi.crescer.api.representation.resposta.RespostaResponse;
import br.com.cwi.crescer.api.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resposta")
public class RespostaController {

    @Autowired
    RespostaService service;

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.OK)
    public void responder(@RequestHeader("authorization") String token, @RequestBody RespostaRequest request) {
        service.criarResposta(token, request);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<RespostaResponse> listarRespostasPorId(@RequestHeader("authorization") String token, @RequestParam("idPergunta") Long idPergunta) {
        return service.listarRespostasPorId(idPergunta);
    }

    @PostMapping("/aprovacao")
    @ResponseStatus(HttpStatus.OK)
    public void mudarAprovacaoDaResposta(@RequestHeader("authorization") String token, @RequestParam("idResposta") Long idResposta) {
        service.mudarAprovacaoDaResposta(token, idResposta);
    }
}
