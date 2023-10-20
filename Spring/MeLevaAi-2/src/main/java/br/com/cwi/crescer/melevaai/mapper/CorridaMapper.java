package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.domain.Coordenadas;
import br.com.cwi.crescer.melevaai.domain.Corrida;
import br.com.cwi.crescer.melevaai.representation.request.ChamarCorridaRequest;
import br.com.cwi.crescer.melevaai.representation.response.ChamarCorridaResponse;
import br.com.cwi.crescer.melevaai.representation.response.CoordenadasResponse;
import br.com.cwi.crescer.melevaai.representation.response.CorridaResponse;
import br.com.cwi.crescer.melevaai.representation.response.VeiculoChamarCorridaResponse;

public class CorridaMapper {

    public static Corrida toDomain(ChamarCorridaRequest request) {
        return new Corrida(new Coordenadas(request.getXInicial(), request.getYInicial()), new Coordenadas(request.getXFinal(), request.getYFinal()));
    }

    public static CorridaResponse toCorridaResponse(Corrida corrida){

        CorridaResponse corridaResponse = new CorridaResponse();
        CoordenadasResponse finais = new CoordenadasResponse();
        finais.setX(corrida.getFinais().getX());
        finais.setY(corrida.getFinais().getY());
        CoordenadasResponse iniciais = new CoordenadasResponse();
        iniciais.setX(corrida.getIniciais().getX());
        iniciais.setY(corrida.getIniciais().getY());

        corridaResponse.setNomeMotorista(corrida.getVeiculo().getProprietario().getNome());
        corridaResponse.setNomePassageiro(corrida.getPassageiro().getNome());
        corridaResponse.setIdCorrida(corrida.getId());
        corridaResponse.setCoordenadasFinais(finais);
        corridaResponse.setCoordenadasIniciais(iniciais);

        return corridaResponse;
    }


    public static ChamarCorridaResponse corridaToChamarCorridaResponse(Corrida corrida, int tempo) {

         return new ChamarCorridaResponse(
                corrida.getId(),
                new VeiculoChamarCorridaResponse(
                        corrida.getVeiculo().getPlaca(),
                        corrida.getVeiculo().getMarca(),
                        corrida.getVeiculo().getModelo(),
                        corrida.getVeiculo().getCor(),
                        corrida.getVeiculo().getFoto(),
                        corrida.getVeiculo().getProprietario().getNome()),
                tempo);

    }
}
