package br.com.cwi.crescer.melevaai.representation.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IniciarCorridaResponse {
    public IniciarCorridaResponse(double tempoEstimadoDoPercursoEmSegundos, double valorEstimadoCorrida) {
        this.tempoEstimadoDoPercursoEmSegundos = tempoEstimadoDoPercursoEmSegundos;
        this.valorEstimadoCorrida = valorEstimadoCorrida;
    }

    @NotNull
    private double tempoEstimadoDoPercursoEmSegundos;
    @NotNull
    private double valorEstimadoCorrida;
}
