package br.com.cwi.crescer.melevaai.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class CPF {

    private String numero;

}
