package br.com.cwi.crescer.melevaai.mapper;


import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.domain.CarteiraNacionalHabilitacao;
import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.representation.CadastraMotoristaRequest;
import br.com.cwi.crescer.melevaai.representation.CNHCompletaRequestResponse;


public class MotoristaMapper {

    public static Motorista toDomain(CadastraMotoristaRequest request) {
        CNHCompletaRequestResponse cnhRequest = request.getCnh();

        CarteiraNacionalHabilitacao cnh = new CarteiraNacionalHabilitacao(cnhRequest.getNumero(),
                cnhRequest.getCategoria(), cnhRequest.getDataVencimento());

        CPF cpf = new CPF(request.getCpf());

        return new Motorista(request.getNome(),request.getEmail(),request.getDataNascimento(), cpf,cnh);

    }

}

