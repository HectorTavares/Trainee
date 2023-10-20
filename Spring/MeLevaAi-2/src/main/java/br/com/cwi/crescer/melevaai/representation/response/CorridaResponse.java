package br.com.cwi.crescer.melevaai.representation.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorridaResponse {


    private String nomeMotorista;

    private String nomePassageiro;

    private Integer idCorrida;

    private CoordenadasResponse coordenadasIniciais;

    private CoordenadasResponse coordenadasFinais;

}
