package br.com.cwi.crescer.api.representation.usuario;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    String email;
    String username;
    String avatar;
    Boolean isMonitor;
    Double reputacao;

}
