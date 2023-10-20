package br.com.cwi.crescer.melevaai.representation.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChamarCorridaResponse {
    public ChamarCorridaResponse( Integer idCorrida ,VeiculoChamarCorridaResponse veiculo, double tempoCorrida) {
        this.idCorrida = idCorrida;
        this.veiculo = veiculo;
        this.tempoCorrida = tempoCorrida;
    }

    @NotNull
    private int idCorrida;
    @NotNull
    private VeiculoChamarCorridaResponse veiculo;
    @NotNull
    private double tempoCorrida;
}
