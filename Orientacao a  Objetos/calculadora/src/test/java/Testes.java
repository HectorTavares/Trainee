import entities.Calculadora;
import org.junit.Assert;
import org.junit.Test;

public class Testes {
    //Testes soma()
    @Test
    public void deveSomarCorretamenteQuandoOsValoresForemInteiros() {
        //Arrange
        double valor1 = 2;
        double valor2 = 2;
        double resultadoEsperado = 4;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.soma(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);
    }

    @Test
    public void deveSomarCorretamenteQuandoOsNumerosPossuemPontosFlutuantes() {
        //Arrange
        double valor1 = 10.7;
        double valor2 = 9.3;
        double resultadoEsperado = 20;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.soma(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);
    }

    //Testes divisao()
    @Test
    public void deveDividirCorretamenteQuandoNumerosForemInteiros() {
        //Arrange
        double valor1 = 10;
        double valor2 = 5;
        double resultadoEsperado = 2;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.divisao(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);
    }

    @Test
    public void deveDividirCorretamenteQuandoNumerosPossuemPontosFlutuantes() {
        //Arrange
        double valor1 = 12.2;
        double valor2 = 3.7;
        double resultadoEsperado = 3.29;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.divisao(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);
    }

    //Testes multiplicacao()
    @Test
    public void deveMultiplicarCorretamenteQuandoNumerosForemInteiros() {
        //Arrange
        double valor1 = 2;
        double valor2 = 10;
        double resultadoEsperado = 20;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.multiplicacao(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);

    }

    @Test
    public void deveMultiplicarCorretamenteQuandoNumerosPossuemPontosFlutuantes() {
        //Arrange
        double valor1 = 5.1;
        double valor2 = 10.5;
        double resultadoEsperado = 53.55;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.multiplicacao(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);

    }

    //Testes subtracao()
    @Test
    public void deveSubtrairCorretamenteQuandoOsValoresForemInteiros() {
        //Arrange
        double valor1 = 10;
        double valor2 = 7;
        double resultadoEsperado = 3;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.subtracao(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);
    }

    @Test
    public void deveSubtrairCorretamenteQuandoOsNumerosPossuemPontosFlutuantes() {
        //Arrange
        double valor1 = 22.4;
        double valor2 = 10.8;
        double resultadoEsperado = 11.6;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.subtracao(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);
    }

    //Testes raizQuadrada()
    @Test
    public void deveCalcularCorretamenteARaizQuadradaQuandoONumeroForInteiro(){
        //Arrange
        double valor1=81;
        double resultadoEsperado=9;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.raizQuadrada(valor1);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);

    }

    @Test
    public void deveCalcularCorretamenteARaizQuadradaQuandoONumeroPossuirPontoFlutuante(){
        //Arrange
        double valor1=220.5;
        double resultadoEsperado=14.849;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.raizQuadrada(valor1);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);

    }

    //Testes exponenciacao()
    @Test
    public void deveCalcularOExpoenteCorretamenteQuandoOsValoresForemInteiros() {
        //Arrange
        double valor1 = 10;
        double valor2 = 2;
        double resultadoEsperado = 100;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.exponenciacao(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);
    }

    @Test
    public void deveCalcularOExpoenteCorretamenteQuandoOsNumerosPossuemPontosFlutuantes() {
        //Arrange
        double valor1 = 5.4;
        double valor2 = 2.1;
        double resultadoEsperado = 34.52;
        Calculadora calculadora = new Calculadora();

        //Act
        double valorCalculado = calculadora.exponenciacao(valor1, valor2);

        //Assert
        Assert.assertEquals(resultadoEsperado, valorCalculado, 0.01);
    }


}
