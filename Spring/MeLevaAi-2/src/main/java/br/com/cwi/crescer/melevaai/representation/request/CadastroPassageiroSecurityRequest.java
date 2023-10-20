package br.com.cwi.crescer.melevaai.representation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CadastroPassageiroSecurityRequest {

    private String email;

    private String firstName;


    private String lastName;

    private String password;

    private String[] roles;


}
