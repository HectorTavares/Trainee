package br.com.cwi.crescer.melevaai.representation.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChamarCorridaRequest {
    @NotNull
    private double xInicial;
    @NotNull
    private double yInicial;
    @NotNull
    private double xFinal;
    @NotNull
    private double yFinal;
}
