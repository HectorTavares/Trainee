package br.com.cwi.crescer.melevaai.fixture;

import br.com.cwi.crescer.melevaai.domain.Categoria;
import br.com.cwi.crescer.melevaai.representation.request.CadastraMotoristaRequest;
import br.com.cwi.crescer.melevaai.representation.response.CNHCompletaRequestResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class CadastraMotoristaRequestFixture {

    public static CadastraMotoristaRequest CriarCadastraMotoristaRequest(){
        CadastraMotoristaRequest request = new CadastraMotoristaRequest();
        CNHCompletaRequestResponse cnh = new CNHCompletaRequestResponse();

        cnh.setCategoria(Categoria.A);
        cnh.setNumero("10239283");
        cnh.setDataVencimento(LocalDate.now().plusYears(30));

        request.setCnh(cnh);
        request.setNome("Motorista Request");
        request.setCpf("000.000.000-00");
        request.setEmail("Email@Email.com");
        request.setDataNascimento(LocalDate.now().minusYears(30));


        return request;
    }

    public static CadastraMotoristaRequest CriarCadastraMotoristaRequestComCnhInvalida(){
       CadastraMotoristaRequest request =  CriarCadastraMotoristaRequest();
       request.getCnh().setDataVencimento(LocalDate.now().plusYears(1));
        return request;
    }

}
