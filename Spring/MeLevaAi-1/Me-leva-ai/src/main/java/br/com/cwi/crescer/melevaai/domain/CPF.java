package br.com.cwi.crescer.melevaai.domain;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CPF {

    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\x2D\\d{2}$)")
    private String numero;

}
