package br.com.cwi.crescer.melevaai.fixture;

import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.domain.CarteiraNacionalHabilitacao;
import br.com.cwi.crescer.melevaai.domain.Categoria;
import br.com.cwi.crescer.melevaai.domain.Motorista;

import java.time.LocalDate;
import java.util.ArrayList;

public class CriarMotorista {

    public static Motorista criarMotorista(){
        CarteiraNacionalHabilitacao cnh = new CarteiraNacionalHabilitacao();
        cnh.setNumero("23131231231");
        cnh.setCategoria(Categoria.A);
        cnh.setDataVencimento(LocalDate.now().plusYears(20));

        Motorista motorista  =  new Motorista();
        motorista.setEmCorrida(false);
        motorista.setNome("Motorista");
        motorista.setCpf(new CPF("222.222.222-22"));
        motorista.setCnh(cnh);
        motorista.setEmail("Email@Email.com");
        motorista.setDataNascimento(LocalDate.now().minusYears(30));

        return motorista;
    }



}
