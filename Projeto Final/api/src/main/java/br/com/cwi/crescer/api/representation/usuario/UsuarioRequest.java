package br.com.cwi.crescer.api.representation.usuario;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    Long id;

    @NotBlank
    String username;

    @NotBlank
    String email;

    @NotBlank
    String avatar;

}
