package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.representation.usuario.UsuarioResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    private static final Long ADMIN_ID = Long.valueOf("903377647562784848");

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UsuarioRepository repository;

    @Autowired
    UsuarioService usuarioService;


    public Page<UsuarioResponse> buscarUsuarios(String busca, String token, Pageable pageable) {

        Long logado = usuarioService.pegarUsuarioLogado(token).getId();

        Page<Usuario> usuarios = repository.usuariosBusca(busca, logado, ADMIN_ID, pageable);

        return usuarios.map(usuario -> modelMapper.map(usuario, UsuarioResponse.class));
    }

    public void validarAdministrador(String token) {
        Usuario usuario = usuarioService.pegarUsuarioLogado(token);

        if (!usuario.getId().equals(ADMIN_ID) && usuario.getIsMonitor().equals(false)) {
            throw new UnauthorizedException("Usuário Não é Administrador");
        }
    }

    public void delegarMonitor(String email, String token) {
        validarAdministrador(token);
        Usuario usuario = usuarioService.pegarUsuarioPorEmail(email);
        usuario.setIsMonitor(true);
        repository.save(usuario);
    }

    public void revogarMonitor(String email, String token) {
        validarAdministrador(token);
        Usuario usuario = usuarioService.pegarUsuarioPorEmail(email);
        if (usuario.getId().equals(ADMIN_ID)) {
            throw new UnauthorizedException("Este usuário não pode ser Revogado!");
        }
        usuario.setIsMonitor(false);
        repository.save(usuario);
    }
}
