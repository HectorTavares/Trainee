package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.domain.Passageiro;
import br.com.cwi.crescer.melevaai.representation.request.PassageiroCompletoRequest;
import br.com.cwi.crescer.melevaai.representation.request.CadastroPassageiroSecurityRequest;
import br.com.cwi.crescer.melevaai.representation.response.PassageiroCompletoResponse;

public class PassageiroMapper {

    public static Passageiro toDomain(PassageiroCompletoRequest request) {
        return new Passageiro(request.getNome(), request.getEmail(), request.getDataNascimento(), new CPF(request.getCpf()));
    }


    public static CadastroPassageiroSecurityRequest toCadastroPassageiroSecurity(Passageiro passageiro){

        CadastroPassageiroSecurityRequest request = new CadastroPassageiroSecurityRequest();

        String[] name = passageiro.getNome().split(" ");
        final String role_passageiro = "ROLE_PASSAGEIRO";

        request.setEmail(passageiro.getEmail());
        request.setFirstName(passageiro.getNome());
        request.setLastName(name[name.length-1]);
        request.setFirstName(name[0]);
        request.setPassword("Senhadetestes123");
        //ver isso

        return request;
    }

    public static PassageiroCompletoResponse toResponse(Passageiro passageiro) {
        PassageiroCompletoResponse response = new PassageiroCompletoResponse();

        response.setNome(passageiro.getNome());
        response.setEmail(passageiro.getEmail());
        response.setDataNascimento(passageiro.getDataNascimento());
        response.setCpf(passageiro.getCpf().getNumero());
        response.setConta(passageiro.getConta());

        return response;
    }
}
