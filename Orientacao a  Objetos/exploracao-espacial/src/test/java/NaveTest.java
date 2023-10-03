import entities.Nave;
import entities.Planeta;
import org.junit.Test;
import org.junit.Assert;
import recursos.*;

import java.util.ArrayList;
import java.util.List;

public class NaveTest {

    @Test
    public void deveTerValorTotalQuandoExistirRecursosNoPlaneta() {
        //Arrange
        List<Recursos> listaRecursos = new ArrayList<>();
        listaRecursos.add(new Agua());      //180 //10 //18
        listaRecursos.add(new Oxigenio());  //300 //2  //150
        listaRecursos.add(new Silicio());   //60 //16  //3.75
        listaRecursos.add(new Ouro());      //120 //25 // 4.8
        listaRecursos.add(new Ferro());     //30 //32  // 0.93
        Planeta venus = new Planeta(5, listaRecursos);
        int somatotalEsperada = 690;

        //Act
        int somaTotalCalculada = venus.valorTotal();

        //Assert
        Assert.assertEquals(somatotalEsperada, somaTotalCalculada);

    }

    @Test
    public void deveTerValorPorPesoQuandoExistirRecursosNoPlaneta() {
        //Arrange
        List<Recursos> listaRecursos = new ArrayList<>();
        listaRecursos.add(new Agua());      //180 //10 //18
        listaRecursos.add(new Oxigenio());  //300 //2  //150
        listaRecursos.add(new Silicio());   //60 //16  //3.75
        listaRecursos.add(new Ouro());      //120 //25 // 4.8
        listaRecursos.add(new Ferro());     //30 //32  // 0.93
        Planeta venus = new Planeta(5, listaRecursos);
        int valorPorPesoEsperado = 175;

        //Act
        int valorPorPesoCalculado = venus.valorPorPeso();

        //Assert
        Assert.assertEquals(valorPorPesoEsperado, valorPorPesoCalculado);
    }

    @Test
    public void deveFicarADerivaQuandoFaltarCombustivelParaIrAteUmPlaneta() {
        //Arrange
        int posicaoEsperada = 3;
        Nave milleniumFalcon = new Nave(9);
        Planeta tatooine = new Planeta(4, new ArrayList<>());

        //Act
        milleniumFalcon.explorar(tatooine);
        int posicaoResultante = milleniumFalcon.getPosicao();

        //Assert
        Assert.assertEquals(posicaoEsperada, posicaoResultante);
    }

    @Test
    public void deveTerValorTotalZeradoQuandoNaoExistirNenhumRecurso() {
        //Arrange
        List<Recursos> listaRecursos = new ArrayList<>();
        Planeta venus = new Planeta(5, listaRecursos);
        int somatotalEsperada = 0;

        //Act
        int somaTotalCalculada = venus.valorTotal();

        //Assert
        Assert.assertEquals(somatotalEsperada, somaTotalCalculada);
    }

    @Test
    public void deveTerValorPorPesoZeradoQuandoNaoExistirNenhumRecurso() {
        //Arrange
        List<Recursos> listaRecursos = new ArrayList<>();
        Planeta venus = new Planeta(5, listaRecursos);
        int valorPorPesoEsperado = 0;

        //Act
        int valorPorPesoCalculado = venus.valorPorPeso();

        //Assert
        Assert.assertEquals(valorPorPesoEsperado, valorPorPesoCalculado);
    }

    @Test
    public void deveRetornarListaDeRecursosQuandoExistirRecursosNoPlanetaExplorado(){
        //Arrange
        Nave apollo = new Nave(290);
        List<Recursos> listaRecursos = new ArrayList<>();
        listaRecursos.add(new Agua());      //180 //10 //18
        listaRecursos.add(new Oxigenio());  //300 //2  //150
        listaRecursos.add(new Silicio());   //60 //16  //3.75
        listaRecursos.add(new Ouro());      //120 //25 // 4.8
        listaRecursos.add(new Ferro());     //30 //32  // 0.93


        Planeta marte = new Planeta(4, listaRecursos);


        //Act
       List<Recursos> resultado = apollo.explorar(marte);


        //Assert
        Assert.assertEquals(listaRecursos,resultado);
    }

    @Test
    public void deveRetornarListaDeRecursosVaziaQuandoNaoExistirRecursosNoPlanetaExplorado(){
        //Arrange
        Nave apollo = new Nave(20);
        List<Recursos> listaRecursos = new ArrayList<>();

        Planeta marte = new Planeta(4, listaRecursos);


        //Act
        List<Recursos> resultado = apollo.explorar(marte);


        //Assert
        Assert.assertEquals(listaRecursos,resultado);
    }

    @Test
    public void deveRetornarListaDeRecursosVaziaQuandoNaoConseguirVoltarAPosicaoDeOrigem(){
        //Arrange
        Nave apollo = new Nave(14);
        List<Recursos> listaRecursos = new ArrayList<>();

        Planeta marte = new Planeta(4, listaRecursos);


        //Act
        List<Recursos> resultado = apollo.explorar(marte);


        //Assert
        Assert.assertEquals(listaRecursos,resultado);
    }
}
