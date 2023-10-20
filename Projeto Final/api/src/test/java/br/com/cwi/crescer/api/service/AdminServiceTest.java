package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.representation.usuario.UsuarioResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioMonitorFixture;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    private static final Long ADMIN_ID = Long.valueOf("903377647562784848");

    @InjectMocks
    private AdminService adminService;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @Test(expected = UnauthorizedException.class)
    public void aoValidarUsuarioNaoAutorizadoDeveRetornarException() {

        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token"))
                .thenReturn(usuario);

        adminService.validarAdministrador("token");
    }

    @Test
    public void aoValidarUsuarioAutorizadoNaoDeveRetornarException() {
        Usuario usuario = usuarioMonitorFixture();

        when(usuarioService.pegarUsuarioLogado("token"))
                .thenReturn(usuario);

        adminService.validarAdministrador("token");
    }

    @Test(expected = UnauthorizedException.class)
    public void aoDelegarSemAutorizacaoDeveRetornarException() {

        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token"))
                .thenReturn(usuario);

        adminService.delegarMonitor("email", "token");

    }

    @Test
    public void aoDelegarUsuarioTendoAutorizacaoDeveDarPermissaoDeMonitorComSucesso() {

        Usuario usuarioMonitor = usuarioMonitorFixture();
        Usuario usuarioComum = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token"))
                .thenReturn(usuarioMonitor);

        when(usuarioService.pegarUsuarioPorEmail(usuarioComum.getEmail()))
                .thenReturn((usuarioComum));

        adminService.delegarMonitor(usuarioComum.getEmail(), "token");

        assertTrue(usuarioComum.getIsMonitor());

    }

    @Test(expected = UnauthorizedException.class)
    public void aoRevogarSemAutorizacaoDeveRetornarException() {

        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token"))
                .thenReturn(usuario);

        adminService.revogarMonitor("email", "token");

    }

    @Test
    public void aoRevogarUsuarioTendoAutorizacaoDeveRetirarPermissaoDeMonitorComSucesso() {

        Usuario usuarioMonitor1 = usuarioMonitorFixture();
        Usuario usuarioMonitor2 = usuarioMonitorFixture();

        when(usuarioService.pegarUsuarioLogado("token"))
                .thenReturn(usuarioMonitor1);

        when(usuarioService.pegarUsuarioPorEmail(usuarioMonitor2.getEmail()))
                .thenReturn((usuarioMonitor2));

        adminService.revogarMonitor(usuarioMonitor2.getEmail(), "token");

        assertFalse(usuarioMonitor2.getIsMonitor());

    }

    @Test(expected = UnauthorizedException.class)
    public void deveRetornarErroAoTentarRevogarMonitorSemPermissao() {
        Usuario usuario = usuarioMonitorFixture();
        usuario.setId(ADMIN_ID);
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        when(usuarioService.pegarUsuarioPorEmail(usuario.getEmail())).thenReturn(usuario);
        adminService.revogarMonitor(usuario.getEmail(), "token");
        verify(repository, times(1)).save(usuario);
    }

    @Test
    public void deveBuscarUsuariosCorretamente(){
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = usuarioComumFixture();
        usuarios.add(usuario);
        Page<Usuario> page = new PageImpl<>(usuarios);

        when(usuarioService.pegarUsuarioLogado("token"))
                .thenReturn(usuario);

        when(repository.usuariosBusca(any(), any(), any(), any()))
                .thenReturn(page);

        List<UsuarioResponse> response = adminService.buscarUsuarios("busca", "token", pageable).getContent();

        verify(repository, times(1)).usuariosBusca("busca", usuario.getId(), Long.valueOf("903377647562784848"), pageable);

        assertEquals(response.get(0).getEmail(), usuario.getEmail());
    }
}
