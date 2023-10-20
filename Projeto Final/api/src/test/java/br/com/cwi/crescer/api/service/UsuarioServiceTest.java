package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NotFoundException;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.representation.guilda.GuildasResponse;
import br.com.cwi.crescer.api.representation.usuario.UsuarioResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.api.fixture.GuildasFixture.guildasFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioMonitorFixture;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private DiscordService discordService;

    @Test
    public void seUsuarioEstiverNoCrescerDeveRetornarVerdadeiroNaVerificacao() {

        List<GuildasResponse> guildasComCrescer = guildasFixture();

        Boolean comCrescer = usuarioService.verificarSeUsuarioEstaNoCrescer(guildasComCrescer);

        assertTrue(comCrescer);
    }

    @Test
    public void seUsuarioNaoEstiverNoCrescerDeveRetornarFalsoNaVerificacao() {

        List<GuildasResponse> guildasSemCrescer = new ArrayList<>();

        GuildasResponse guilda = new GuildasResponse();
        guilda.setId("123456789");
        guildasSemCrescer.add(guilda);

        Boolean semCrescer = usuarioService.verificarSeUsuarioEstaNoCrescer(guildasSemCrescer);

        assertFalse(semCrescer);
    }

    @Test(expected = UnauthorizedException.class)
    public void quandoUsuarioNaoForCrescerDeveRetornarException() {

        Usuario usuario = usuarioComumFixture();
        List<GuildasResponse> guildas = new ArrayList<>();

        when(discordService.buscarDadosUsuario(any()))
                .thenReturn(usuario);

        when(discordService.buscarGuildasDoUsuarioLogado(any()))
                .thenReturn(guildas);

        usuarioService.logarUsuario("token");

    }

    @Test
    public void deveRetornarAvatarNoFormatoCorretoParaArmazenamentoDoBanco() {

        Usuario usuario = usuarioComumFixture();
        Usuario usuario_db = usuarioService.gerarUsuarioComAvatar(usuario);

        String esperado = "https://cdn.discordapp.com/avatars/123456789/123456789.png";

        assertEquals(esperado, usuario_db.getAvatar());

    }

    @Test
    public void deveChamarRepositoryComSucessoQuandoChamarMetodoLogarUsuario() {

        Usuario usuario = usuarioComumFixture();
        List<GuildasResponse> guildas = guildasFixture();

        when(discordService.buscarDadosUsuario(any()))
                .thenReturn(usuario);

        when(discordService.buscarGuildasDoUsuarioLogado(any()))
                .thenReturn(guildas);

        usuarioService.logarUsuario("token");

        verify(repository, times(1)).save(usuario);

    }

    @Test
    public void deveChamarRepositoryComSucessoQuandoChamarMetodoAtualizarUsuario() {

        Usuario usuario = usuarioComumFixture();
        usuarioService.atualizarUsuario(usuario);

        verify(repository, times(1)).save(usuario);

    }

    @Test
    public void deveRetornarUsuarioQuandoChamarMetodoPegarUsuarioPorId() {

        Usuario usuario = usuarioComumFixture();

        when(repository.findUsuarioById(usuario.getId()))
                .thenReturn(usuario);

        Usuario usuarioEsperado = usuarioService.pegarUsuarioPorId(usuario.getId());

        assertEquals(usuarioEsperado.getId(), usuario.getId());

    }

    @Test(expected = NotFoundException.class)
    public void quandoBuscarUsuarioInexistentePeloIdDeveRetornarException() {

        Usuario usuario = usuarioComumFixture();

        when(repository.findUsuarioById(usuario.getId()))
                .thenReturn(null);

        usuarioService.pegarUsuarioPorId(usuario.getId());

    }

    @Test
    public void quandoChamarMetodoUsuarioLogadoDeveRetornarUsuario() {

        Usuario usuario = usuarioComumFixture();

        when(discordService.buscarDadosUsuario("token"))
                .thenReturn(usuario);

        when(repository.findByEmail(usuario.getEmail()))
                .thenReturn(usuario);

        UsuarioResponse usuarioEsperado = usuarioService.pegarUsuarioLogadoResponse("token");

        assertEquals(usuarioEsperado.getEmail(), usuario.getEmail());
    }

    @Test
    public void deveRetornarUsuarioComSucessoAtravesDoEmail() {

        Usuario usuario = usuarioComumFixture();
        when(repository.findByEmail(usuario.getEmail())).thenReturn(usuario);
        Usuario usuarioRetornado = usuarioService.pegarUsuarioPorEmail(usuario.getEmail());
        Assert.assertEquals(usuario, usuarioRetornado);

    }

    @Test(expected = NotFoundException.class)
    public void deveRetornarErroSeNaoEncontrarUsuarioAtravesDoEmail() {
        Usuario usuario = usuarioComumFixture();
        when(repository.findByEmail(usuario.getEmail())).thenReturn(null);
        Usuario usuarioRetornado = usuarioService.pegarUsuarioPorEmail(usuario.getEmail());
        Assert.assertEquals(usuario, usuarioRetornado);
    }

    @Test
    public void deveVerificarSeUsuarioEstaCadastrado() {
        Usuario usuario = usuarioComumFixture();
        when(repository.existsById(usuario.getId())).thenReturn(true);
        usuarioService.verificarSeUsuarioEstaCadastrado(usuario.getId());
    }

    @Test(expected = NotFoundException.class)
    public void deveRetornarErroSeUsuarioNaoEstiverCadastrado() {
        Usuario usuario = usuarioComumFixture();
        when(repository.existsById(usuario.getId())).thenReturn(false);
        usuarioService.verificarSeUsuarioEstaCadastrado(usuario.getId());
    }

    @Test
    public void deveListarUsuariosCorretamente(){
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

        when(repository.findAllByOrderByReputacaoDesc(pageable))
                .thenReturn(page);

        List<UsuarioResponse> response = usuarioService.listarUsuariosporReputacao(pageable).getContent();

        verify(repository, times(1)).findAllByOrderByReputacaoDesc(pageable);

        assertEquals(response.get(0).getEmail(), usuario.getEmail());

    }

    @Test
    public void deveConferirCorretamenteDadosPrevios(){
        Usuario usuarioPrevio = usuarioComumFixture();
        usuarioPrevio.setReputacao(10.0);

        Usuario usuario = usuarioComumFixture();
        usuario.setReputacao(0.0);

        when(repository.findUsuarioById(usuario.getId()))
                .thenReturn(usuarioPrevio);

        usuarioService.verificarDadosPrevios(usuario);

        assertEquals(usuarioPrevio.getReputacao(), usuario.getReputacao());

    }

    @Test
    public void deveVerificarMonitoriaCorretamente(){
        Usuario usuario = usuarioComumFixture();
        Usuario monitor = usuarioMonitorFixture();

        when(repository.findUsuarioById(usuario.getId()))
                .thenReturn(monitor);

        usuarioService.verificarMonitoria(usuario);

        assertTrue(usuario.getIsMonitor());
    }

    @Test
    public void deveVerificarAdministradorCorretamente(){
        Usuario usuario = usuarioComumFixture();
        usuario.setId(903377647562784848L);

        when(repository.findUsuarioById(usuario.getId()))
                .thenReturn(usuario);

        usuarioService.verificarMonitoria(usuario);

        assertTrue(usuario.getIsMonitor());

    }
}

