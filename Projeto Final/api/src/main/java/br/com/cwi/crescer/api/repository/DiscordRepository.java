package br.com.cwi.crescer.api.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


import java.util.List;

@FeignClient(url = "https://discord.com/api", name = "discord")
public interface DiscordRepository {

    @GetMapping(value = "/users/@me")
    Object buscarDadosUsuario(@RequestHeader("authorization") String token, @RequestHeader("User-Agent") String agent);

    @GetMapping(value = "/users/@me/guilds")
    List<Object> buscarGuildasDoUsuarioLogado(@RequestHeader("authorization") String token, @RequestHeader("User-Agent") String agent);

}
