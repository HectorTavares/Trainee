package br.com.cwi.crescer.api.controller;


import br.com.cwi.crescer.api.representation.notificacao.NotificacaoResponse;
import br.com.cwi.crescer.api.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    NotificacaoService service;

    @GetMapping("/pegar")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificacaoResponse> pegarTodasNotificacoes(@RequestHeader("authorization") String token) {

        return service.pegarTodasNotificacoes(token);
    }

    @DeleteMapping("/visualizar")
    @ResponseStatus(HttpStatus.OK)
    public void visualizar(@RequestHeader("authorization") String token, @RequestParam("idNotificacao") Long idNotificacao) {
        service.visualizarNotificacaoPorId(idNotificacao, token);
    }

    @DeleteMapping("/visualizarTodos")
    @ResponseStatus(HttpStatus.OK)
    public void visualizarTodos(@RequestHeader("authorization") String token) {
        service.visualizarTodasNotificacoesPorIdDestinatario(token);
    }
}
