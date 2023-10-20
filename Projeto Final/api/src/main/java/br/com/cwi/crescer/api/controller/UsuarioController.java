package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.representation.usuario.UsuarioResponse;
import br.com.cwi.crescer.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void logarUsuario(@RequestHeader("authorization") String token) {
        usuarioService.logarUsuario(token);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse usuarioLogado(@RequestHeader("authorization") String token) {
        return usuarioService.pegarUsuarioLogadoResponse(token);
    }

    @GetMapping("/ranking")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioResponse> listarUsuariosPorReputacao(@RequestHeader("authorization") String token, Pageable pageable) {
        return usuarioService.listarUsuariosporReputacao(pageable);
    }

}
