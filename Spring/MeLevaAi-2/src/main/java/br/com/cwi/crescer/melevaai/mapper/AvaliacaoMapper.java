package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.representation.request.AvaliacaoNotaRequest;


public class AvaliacaoMapper {
    public static Integer toDomain(AvaliacaoNotaRequest request) {
        return request.getNota();
    }
}
