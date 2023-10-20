package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.domain.Passageiro;
import br.com.cwi.crescer.melevaai.representation.PassageiroCompletoRequest;
import br.com.cwi.crescer.melevaai.representation.PassageiroCompletoResponse;

public class PassageiroMapper {

    public static Passageiro toDomain(PassageiroCompletoRequest request) {
        return new Passageiro(request.getNome(), request.getEmail(), request.getDataNascimento(), new CPF(request.getCpf()));
    }

    public PassageiroCompletoResponse toResponse(Passageiro passageiro) {
        PassageiroCompletoResponse response = new PassageiroCompletoResponse();

        response.setNome(passageiro.getNome());
        response.setEmail(passageiro.getEmail());
        response.setDataNascimento(passageiro.getDataNascimento());
        response.setCpf(passageiro.getCpf().getNumero());
        response.setConta(passageiro.getConta());

        return response;
    }
}
