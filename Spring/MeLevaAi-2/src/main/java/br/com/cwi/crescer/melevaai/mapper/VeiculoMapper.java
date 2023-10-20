package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.domain.CarteiraNacionalHabilitacao;
import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.representation.request.CadastraMotoristaRequest;
import br.com.cwi.crescer.melevaai.representation.request.VeiculoCompletoRequest;
import br.com.cwi.crescer.melevaai.representation.response.CNHCompletaRequestResponse;
import br.com.cwi.crescer.melevaai.domain.Veiculo;
import br.com.cwi.crescer.melevaai.representation.response.VeiculoCompletoResponse;

public class VeiculoMapper {

    public static Veiculo toDomain(VeiculoCompletoRequest request) {
        return new Veiculo(request.getPlaca(),request.getMarca(),request.getModelo(),request.getCor(),request.getAno(),request.getFoto(),request.getCategoria(),request.getQuantidadeLugares());
    }

    public static VeiculoCompletoResponse toResponse(Veiculo veiculo) {
        CarteiraNacionalHabilitacao cnh = veiculo.getProprietario().getCnh();
        CNHCompletaRequestResponse cnhRequest = new CNHCompletaRequestResponse();

        cnhRequest.setNumero(cnh.getNumero());
        cnhRequest.setCategoria(cnh.getCategoria());
        cnhRequest.setDataVencimento(cnh.getDataVencimento());

        Motorista motorista = veiculo.getProprietario();
        CadastraMotoristaRequest motoristaRequest = new CadastraMotoristaRequest();

        motoristaRequest.setNome(motorista.getNome());
        motoristaRequest.setEmail(motorista.getEmail());
        motoristaRequest.setCpf(motorista.getCpf().getNumero());
        motoristaRequest.setDataNascimento(motorista.getDataNascimento());
        motoristaRequest.setCnh(cnhRequest);

        VeiculoCompletoResponse response = new VeiculoCompletoResponse();

        response.setAno(veiculo.getAno());
        response.setCategoria(veiculo.getCategoria());
        response.setCor(veiculo.getCor());
        response.setFoto(veiculo.getFoto());
        response.setMarca(veiculo.getMarca());
        response.setPlaca(veiculo.getPlaca());
        response.setQuantidadeLugares(veiculo.getQuantidadeLugares());
        response.setModelo(veiculo.getModelo());
        response.setProprietario(motoristaRequest);

        return response;
    }
}