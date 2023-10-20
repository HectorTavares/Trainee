package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NotFoundException;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.representation.guilda.GuildasResponse;
import br.com.cwi.crescer.api.representation.usuario.UsuarioResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class UsuarioService {

    private static final String CRESCER_ID = "840227367531184128";
    private static final Long ADMIN_ID = Long.valueOf("903377647562784848");

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UsuarioRepository repository;

    @Autowired
    DiscordService discordService;

    public void logarUsuario(String token) {

        Usuario usuario = discordService.buscarDadosUsuario(token);
        List<GuildasResponse> guildas = discordService.buscarGuildasDoUsuarioLogado(token);
        if (!usuario.getId().equals(ADMIN_ID) && !verificarSeUsuarioEstaNoCrescer(guildas)) {
            throw new UnauthorizedException("Você Não é um Usuário Autorizado.");
        }
        verificarMonitoria(usuario);
        verificarDadosPrevios(usuario);
        repository.save(gerarUsuarioComAvatar(usuario));
    }

    public boolean verificarSeUsuarioEstaNoCrescer(List<GuildasResponse> guildas) {

        for (GuildasResponse g : guildas) {
            if (g.getId().equals(CRESCER_ID)) {
                return true;
            }
        }
        return false;
    }

    public void verificarMonitoria(Usuario usuario) {
        Usuario usuarioValidado = repository.findUsuarioById(usuario.getId());

        if (!isNull(usuarioValidado) && Boolean.TRUE.equals(usuarioValidado.getIsMonitor())) {
            usuario.setIsMonitor(true);
        }
        if (usuario.getId().equals(ADMIN_ID)) {
            usuario.setIsMonitor(true);
        }
    }

    public void verificarDadosPrevios(Usuario usuario) {
        Usuario usuarioValidado = repository.findUsuarioById(usuario.getId());

        if (!isNull(usuarioValidado)) {
            usuario.setReputacao(usuarioValidado.getReputacao());
        }
    }

    public Usuario gerarUsuarioComAvatar(Usuario usuario) {
        String avatar = new StringBuilder()
                .append("https://cdn.discordapp.com/avatars/")
                .append(usuario.getId())
                .append("/")
                .append(usuario.getAvatar())
                .append(".png")
                .toString();
        usuario.setAvatar(avatar);
        return usuario;
    }

    public void atualizarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    public Usuario pegarUsuarioLogado(String token) {
        return repository.findByEmail(discordService.buscarDadosUsuario(token).getEmail());
    }

    public UsuarioResponse pegarUsuarioLogadoResponse(String token) {
        Usuario usuario = pegarUsuarioLogado(token);
        return modelMapper.map(usuario, UsuarioResponse.class);
    }

    public Usuario pegarUsuarioPorId(Long id) {
        Usuario usuario = repository.findUsuarioById(id);
        if (isNull(usuario)) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return usuario;
    }

    public Usuario pegarUsuarioPorEmail(String email) {
        Usuario usuario = repository.findByEmail(email);
        if (isNull(usuario)) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return usuario;

    }

    public Page<UsuarioResponse> listarUsuariosporReputacao(Pageable pageable) {

        Page<Usuario> usuarios = repository.findAllByOrderByReputacaoDesc(pageable);

        List<UsuarioResponse> usuarioResponses = usuarios
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponse.class))
                .collect(Collectors.toList());

        return new PageImpl<>(usuarioResponses);
    }

    public void verificarSeUsuarioEstaCadastrado(Long id) {

        boolean isNotUsuarioCadastrado = !repository.existsById(id);
        if (isNotUsuarioCadastrado) {
            throw new NotFoundException("Usuario não cadastrado");
        }

    }

}

