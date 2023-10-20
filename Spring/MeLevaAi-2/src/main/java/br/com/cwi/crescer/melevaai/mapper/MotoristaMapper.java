package br.com.cwi.crescer.melevaai.mapper;


import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.domain.CarteiraNacionalHabilitacao;
import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.representation.request.CadastraMotoristaRequest;
import br.com.cwi.crescer.melevaai.representation.response.CNHCompletaRequestResponse;
import br.com.cwi.crescer.melevaai.representation.response.MotoristaResponse;
import org.modelmapper.ModelMapper;


public class MotoristaMapper {

    static ModelMapper modelMapper = new ModelMapper();

    public static Motorista toDomain(CadastraMotoristaRequest request) {
        CNHCompletaRequestResponse cnhRequest = request.getCnh();

        CarteiraNacionalHabilitacao cnh = new CarteiraNacionalHabilitacao();
        cnh.setCategoria(cnhRequest.getCategoria());
        cnh.setDataVencimento(cnhRequest.getDataVencimento());
        cnh.setNumero(cnhRequest.getNumero());

        CPF cpf = new CPF(request.getCpf());

        return new Motorista(request.getNome(),request.getEmail(),request.getDataNascimento(), cpf,cnh);

    }

    public static MotoristaResponse toResponse(Motorista motorista){

        return modelMapper.map(motorista, MotoristaResponse.class);
    }

}

