package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.representation.usuario.UsuarioResponse;
import br.com.cwi.crescer.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/busca")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioResponse> listarUsuarios(@RequestParam String busca,
                                                @RequestHeader("authorization") String token,
                                                Pageable pageable) {

        return adminService.buscarUsuarios(busca, token, pageable);
    }

    @GetMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    public void validarAdministrador(@RequestHeader("authorization") String token) {
        adminService.validarAdministrador(token);
    }

    @PostMapping(value = "/delegar", params = {"email"})
    @ResponseStatus(HttpStatus.OK)
    public void delegarMonitor(@RequestHeader("authorization") String token, @RequestParam String email) {
        adminService.delegarMonitor(email, token);
    }

    @PostMapping(value = "/revogar", params = {"email"})
    @ResponseStatus(HttpStatus.OK)
    public void validarAdministrador(@RequestHeader("authorization") String token, @RequestParam String email) {
        adminService.revogarMonitor(email, token);
    }

}
