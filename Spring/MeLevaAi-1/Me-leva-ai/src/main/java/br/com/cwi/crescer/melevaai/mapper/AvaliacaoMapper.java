package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.representation.avaliacao.AvaliacaoNotaRequest;

public class AvaliacaoMapper {
    public Integer toDomain(AvaliacaoNotaRequest request) {
        return request.getNota();
    }
}
