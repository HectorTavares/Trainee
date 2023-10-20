package br.com.cwi.crescer.melevaai.representation.chamarCorrida;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChamarCorridaRequest {
    @NotNull
    private double iniciais []= new double [2];
    @NotNull
    private double finais []= new double [2];
}
