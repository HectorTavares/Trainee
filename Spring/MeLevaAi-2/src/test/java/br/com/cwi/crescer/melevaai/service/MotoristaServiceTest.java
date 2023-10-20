package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.domain.Veiculo;
import br.com.cwi.crescer.melevaai.exception.MotoristaJaCadastradoException;
import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.melevaai.fixture.CriarMotorista;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.representation.request.CadastraMotoristaRequest;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.hibernate.mapping.Any;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.parser.Entity;

import static br.com.cwi.crescer.melevaai.fixture.CadastraMotoristaRequestFixture.CriarCadastraMotoristaRequest;
import static br.com.cwi.crescer.melevaai.fixture.CadastraMotoristaRequestFixture.CriarCadastraMotoristaRequestComCnhInvalida;
import static br.com.cwi.crescer.melevaai.fixture.CriarMotorista.criarMotorista;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MotoristaServiceTest {

    @InjectMocks
    private MotoristaService tested;

    @Mock
    private MotoristaRepository repository;

    @Test
    @DisplayName("Deve criar um motorista corretamente")
    public void DeveCriarMotoristaCorretamente() throws MotoristaJaCadastradoException {

        CadastraMotoristaRequest request = CriarCadastraMotoristaRequest();
        Motorista motorista = criarMotorista();

        when(repository.findByCpf(new CPF(request.getCpf()))).thenReturn(null);

        tested.criarMotorista(request);

        verify(repository).save(motorista);

    }


}