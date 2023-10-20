package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.DiscordRepository;
import br.com.cwi.crescer.api.representation.guilda.GuildasResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static br.com.cwi.crescer.api.fixture.GuildasFixture.guildasFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscordServiceTest {

    private static final String USER_AGENT = "User-Agent";

    @InjectMocks
    private DiscordService discordService;

    @Mock
    private DiscordRepository repository;

    @Test
    public void quandoChamarMetodoBuscarUsuarioDeveRetornarUsuario(){

        Usuario usuario = usuarioComumFixture();

        when(repository.buscarDadosUsuario("token", USER_AGENT))
                .thenReturn(usuario);

        Usuario usuarioEsperado = discordService.buscarDadosUsuario("token");

        assertEquals(usuarioEsperado.getId(), usuario.getId());

    }

    @Test(expected = UnauthorizedException.class)
    public void quandoMetodoBuscarUsuarioNaoForAutorizadoDeveRetornarException(){

        when(repository.buscarDadosUsuario("token", USER_AGENT))
                .thenThrow(UnauthorizedException.class);

        discordService.buscarDadosUsuario("token");

    }

    @Test
    public void quandoChamarMetodoBuscarGuildasDoUsuarioLogadoDeveRetornaListaDeGuildas(){

        List<GuildasResponse> guildas = guildasFixture();

        when(repository.buscarGuildasDoUsuarioLogado("token", USER_AGENT))
                .thenReturn(Collections.singletonList(guildas));

        List<GuildasResponse> guildasEsperadas = discordService.buscarGuildasDoUsuarioLogado("token");

        assertEquals(guildasEsperadas.get(0).getId(), guildas.get(0).getId());

    }

    @Test(expected = UnauthorizedException.class)
    public void quandoMetodoBuscarGuildasDoUsuarioLogadoNaoForAutorizadoDeveRetornarException(){

        when(repository.buscarGuildasDoUsuarioLogado("token", USER_AGENT))
                .thenThrow(UnauthorizedException.class);

        discordService.buscarGuildasDoUsuarioLogado("token");

    }
}
