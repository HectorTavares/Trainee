package br.com.cwi.crescer.melevaai.representation.avaliacao;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AvaliacaoNotaRequest {
    @NotNull
    private Integer nota;
}
