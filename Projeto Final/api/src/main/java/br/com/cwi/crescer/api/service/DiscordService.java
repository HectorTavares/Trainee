package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.DiscordRepository;
import br.com.cwi.crescer.api.representation.guilda.GuildasResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscordService {

    private static final String USER_AGENT = "User-Agent";

    @Autowired
    DiscordRepository repository;

    ModelMapper modelMapper = new ModelMapper();

    public Usuario buscarDadosUsuario(String token) {

        try {
            Object objetoUsuario = repository.buscarDadosUsuario(token, USER_AGENT);
            return modelMapper.map(objetoUsuario, Usuario.class);
        } catch (Exception exception) {
            throw new UnauthorizedException("Não Autorizado.");
        }
    }

    public List<GuildasResponse> buscarGuildasDoUsuarioLogado(String token) {

        try {
            List<Object> guildas = repository.buscarGuildasDoUsuarioLogado(token, USER_AGENT);
            return guildas
                    .stream()
                    .map(guilda -> modelMapper.map(guilda, GuildasResponse.class))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new UnauthorizedException("Não Autorizado.");
        }
    }
}
