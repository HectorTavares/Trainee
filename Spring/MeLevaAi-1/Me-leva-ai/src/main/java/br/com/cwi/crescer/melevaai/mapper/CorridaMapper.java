package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.domain.Corrida;
import br.com.cwi.crescer.melevaai.representation.chamarCorrida.ChamarCorridaRequest;
import br.com.cwi.crescer.melevaai.representation.iniciarCorrida.IniciarCorridaResponse;

public class CorridaMapper {

    public static Corrida toDomain(ChamarCorridaRequest request) {
        return new Corrida(request.getIniciais(),request.getFinais());
    }

}
