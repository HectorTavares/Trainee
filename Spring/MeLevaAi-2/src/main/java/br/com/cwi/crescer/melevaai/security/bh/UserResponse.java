package br.com.cwi.crescer.melevaai.security.bh;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;
}
