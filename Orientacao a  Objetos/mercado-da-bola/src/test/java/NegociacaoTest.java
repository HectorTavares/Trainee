import negociacao.Negociacao;
import org.junit.Assert;
import org.junit.Test;
import clube.*;
import jogador.*;
import jogador.apetite.*;

import java.math.BigDecimal;

public class NegociacaoTest {
    @Test
    public void deveSerPossivelNegociarUmGoleiroComUmClubeQueTemSaldoEmCaixa() {
        //Arrange
        Negociacao negociacao = new Negociacao();
        Clube clube = new Clube("Grêmio", 10, BigDecimal.valueOf(100000000));
        Goleiro goleiro = new Goleiro("Marcelo Grohe", 33, null, 8, new Indiferente(), BigDecimal.valueOf(800500), 12);

        //Act
        boolean foiPossivelNegociar = negociacao.negociar(clube, goleiro);

        //Assert
        Assert.assertTrue(foiPossivelNegociar);
    }

    @Test
    public void naoDeveSerPossivelNegociarUmAtacanteComUmClubeQueTemReputacaoHistoricaMenorQueASua() {
        //Arrange
        Negociacao negociacao = new Negociacao();
        Clube clube = new Clube("Internacional", 3, BigDecimal.valueOf(100000000));
        Atacante atacante = new Atacante("Cristiano Ronaldo", 35, null, 10, new Conservador(), BigDecimal.valueOf(800500), 20);

        //Act
        boolean foiPossivelNegociar = negociacao.negociar(clube, atacante);

        //Assert
        Assert.assertFalse(foiPossivelNegociar);
    }

    //naoDeveSerPossivelNegociarPorFaltaDeCaixaDisponivel
    //deveCalcularCorretamenteOPrecoDoMeioCampoComMenosDeTrintaAnos
    //deveCalcularCorretamenteOPrecoDoMeioCampoComMaisDeTrintaAnos

    @Test
    public void naoDeveSerPossivelNegociarPorFaltaDeCaixaDisponivel() {
        //Arrange
        Negociacao negociacao = new Negociacao();
        Clube clube = new Clube("Inter", 2, BigDecimal.valueOf(2000));
        Zagueiro zagueiro = new Zagueiro("Geromel", 33, null, 8, new Indiferente(), BigDecimal.valueOf(800500));

        //Act
        boolean foiPossivelNegociar = negociacao.negociar(clube, zagueiro);

        //Assert
        Assert.assertFalse(foiPossivelNegociar);
    }

    // deveCalcularCorretamenteOPrecoDoMeioCampoComMenosDeTrintaAnos
    //deveCalcularCorretamenteOPrecoDoMeioCampoComMaisDeTrintaAnos
    @Test
    public void deveCalcularCorretamenteOPrecoDoMeioCampoComMenosDeTrintaAnos() {
        //Arrange
        Clube gremio = new Clube("Grêmio", 10, BigDecimal.valueOf(100000000));
        MeioCampo meioCampo = new MeioCampo("Jean Pyerre", 23, gremio, 5, new Indiferente(), BigDecimal.valueOf(10000));
        double precoEsperado = 10000;

        //Act
        double precoCalculado = meioCampo.valorDeCompra();

        //Assert
        Assert.assertEquals(precoEsperado, precoCalculado, 0.01);

    }

    @Test
    public void deveCalcularCorretamenteOPrecoDoMeioCampoComMaisDeTrintaAnos() {
        //Arrange
        Clube gremio = new Clube("Grêmio", 10, BigDecimal.valueOf(100000000));
        MeioCampo meioCampo = new MeioCampo("Douglas", 34, gremio, 5, new Indiferente(), BigDecimal.valueOf(10000));
        double precoEsperado = 7000;

        //Act
        double precoCalculado = meioCampo.valorDeCompra();

        //Assert
        Assert.assertEquals(precoCalculado, precoEsperado, 0.01);
    }

    @Test
    public void deveNegociarQuandoAsCodicoesForemFavoraveis() {
        //Arrange
        Negociacao negociacao = new Negociacao();
        Clube gremio = new Clube("Grêmio", 1, BigDecimal.valueOf(100000000));
        Clube inter = new Clube("Inter", 2, BigDecimal.valueOf(2000));
        MeioCampo meioCampo = new MeioCampo("Jobson", 34, inter, 10, new Indiferente(), BigDecimal.valueOf(10000));

        //Act
        boolean foiPossivelNegociar = negociacao.negociar(gremio, meioCampo);

        //Assert
        Assert.assertTrue(foiPossivelNegociar);
    }

    @Test
    public void naoDeveNegociarQuandoAReputacaoDoClubeFor0() {
        //Arrange
        Negociacao negociacao = new Negociacao();

        Clube inter = new Clube("Inter", 0, BigDecimal.valueOf(200000));
        Zagueiro zagueiro = new Zagueiro("Joao", 19, null, 5, new Indiferente(), BigDecimal.valueOf(10000));

        //Act
        boolean foiPossivelNegociar = negociacao.negociar(inter, zagueiro);

        //Assert
        Assert.assertFalse(foiPossivelNegociar);
    }

    @Test
    public void deveRetornarNomeDoClubeAtualQuandoEstiverEmUmCLube() {
        //Arrange

        Clube inter = new Clube("Inter", 0, BigDecimal.valueOf(200000));
        Zagueiro zagueiro = new Zagueiro("Joao", 19, inter, 5, new Indiferente(), BigDecimal.valueOf(10000));
        String resultadoEsperado = "Inter";
        //Act
        String resultadoRetornado = zagueiro.getNomeClubeAtual();

        //Assert
        Assert.assertEquals(resultadoRetornado, resultadoEsperado);
    }

    @Test
    public void deveRetornarSemClubeQuandoNaoEstiverEmNenhumClube() {
        //Arrange

        Zagueiro zagueiro = new Zagueiro("Joao", 19, null, 5, new Indiferente(), BigDecimal.valueOf(10000));
        String resultadoEsperado = "Sem Clube";
        //Act
        String resultadoRetornado = zagueiro.getNomeClubeAtual();

        //Assert
        Assert.assertEquals(resultadoRetornado, resultadoEsperado);
    }


    @Test
    public void deveCalcularCorretamenteOPrecoDoAtacanteComMaisDeTrintaAnos() {
        //Arrange
        Clube gremio = new Clube("Grêmio", 10, BigDecimal.valueOf(100000000));
        Atacante atacante = new Atacante("Douglas", 34, gremio, 5, new Indiferente(), BigDecimal.valueOf(1000), 10);
        double precoEsperado = 825;

        //Act
        double precoCalculado = atacante.valorDeCompra();

        //Assert
        Assert.assertEquals(precoCalculado, precoEsperado, 0.01);
    }

    @Test
    public void deveCalcularCorretamenteOPrecoDoAtacanteComMenosDeTrintaAnos() {
        //Arrange
        Clube gremio = new Clube("Grêmio", 10, BigDecimal.valueOf(100000000));
        Atacante atacante = new Atacante("Douglas", 19, gremio, 5, new Indiferente(), BigDecimal.valueOf(1000), 10);
        double precoEsperado = 1100;

        //Act
        double precoCalculado = atacante.valorDeCompra();

        //Assert
        Assert.assertEquals(precoCalculado, precoEsperado, 0.01);
    }

    @Test
    public void deveCalcularPrecoDeLateralQuandoIdadeForMaiorQue28() {
        //Arrange
        Clube gremio = new Clube("Grêmio", 10, BigDecimal.valueOf(100000000));
        Lateral lateral = new Lateral("Rafinha", 34, gremio, new Indiferente(), BigDecimal.valueOf(1000), 10, 0);
        double precoEsperado = 700;

        //Act
        double precoCalculado = lateral.valorDeCompra();

        //Assert
        Assert.assertEquals(precoCalculado, precoEsperado, 0.01);//Arrange

    }

    @Test
    public void deveCalcularPrecoDeLateralQuandoIdadeForMenorQue28() {
        //Arrange
        Clube gremio = new Clube("Grêmio", 10, BigDecimal.valueOf(100000000));
        Lateral lateral = new Lateral("Vanderson", 19, gremio, new Indiferente(), BigDecimal.valueOf(1000), 10, 0);
        double precoEsperado = 1000;

        //Act
        double precoCalculado = lateral.valorDeCompra();

        //Assert
        Assert.assertEquals(precoEsperado, precoCalculado, 0.01);//Arrange

    }

    @Test
    public void deveRetornarApetiteDeMercenario() {
        //Arrange
        Mercenario ronaldinho = new Mercenario();
        double resultadoEsperado = 1.80;

        //Act
        double resultadoObtido = ronaldinho.getAPETITE();

        //Assert
        Assert.assertEquals(resultadoEsperado, resultadoObtido, 00.1);
    }

    @Test
    public void deveRetornarApetiteDeConservador() {
        //Arrange
        Conservador borre = new Conservador();
        double resultadoEsperado = 1.40;

        //Act
        double resultadoObtido = borre.getAPETITE();

        //Assert
        Assert.assertEquals(resultadoEsperado, resultadoObtido, 00.1);
    }

    @Test
    public void deveCalcularPrecoDeZagueiroQuandoIdadeForMenorQue28() {
        //Arrange
        Zagueiro zagueiro = new Zagueiro("Ruan", 19, null, 4, new Indiferente(), BigDecimal.valueOf(1000));
        double precoEsperado = 1000;

        //Act
        double precoCalculado = zagueiro.valorDeCompra();

        //Assert
        Assert.assertEquals(precoEsperado, precoCalculado, 0.01);//Arrange

    }

    @Test
    public void deveSerPossivelNegociarUmAtacanteComUmClubeQueTemReputacaoHistoricaMaiorQueASua() {
        //Arrange
        Negociacao negociacao = new Negociacao();
        Clube clube = new Clube("Internacional", 10, BigDecimal.valueOf(100000000));
        Atacante atacante = new Atacante("Cristiano Ronaldo", 35, null, 1, new Conservador(), BigDecimal.valueOf(800500), 20);

        //Act
        boolean foiPossivelNegociar = negociacao.negociar(clube, atacante);

        //Assert
        Assert.assertTrue(foiPossivelNegociar);
    }

    @Test
    public void deveTransferirJogadorComOutroClube() {
        //Arrange
        Clube inter = new Clube("Inter", 5, BigDecimal.valueOf(100000000));
        Clube gremio = new Clube("Grêmio", 10, BigDecimal.valueOf(100000000));
        Lateral lateral = new Lateral("Vanderson", 19, gremio, new Indiferente(), BigDecimal.valueOf(1000), 10, 0);
        Negociacao negociacao = new Negociacao();

        //Act
        boolean resultado = negociacao.negociar(inter, lateral);

        //Assert
        Assert.assertTrue(resultado);
    }

    @Test
    public void devePossuirInteresseEmNegociarMeioCampoQuandoReputacaoDeClubeFor2PontosMenor() {
        Negociacao negociacao = new Negociacao();
        Clube inter = new Clube("Inter", 2, BigDecimal.valueOf(100000000));
        MeioCampo meioCampo = new MeioCampo("Jobson", 20, inter, 10, new Indiferente(), BigDecimal.valueOf(10000));

        boolean resultado = meioCampo.possuiInteresseDeTranferencia(inter);

        Assert.assertTrue(resultado);

    }

    @Test
    public void naoDevePossuirInteresseEmNegociarMeioCampoQuandoReputacaoDeClubeFor2PontosMenor() {
        Negociacao negociacao = new Negociacao();
        Clube inter = new Clube("Inter", 10, BigDecimal.valueOf(100000000));
        MeioCampo meioCampo = new MeioCampo("Jobson", 20, null, 5, new Indiferente(), BigDecimal.valueOf(10000));

        boolean resultado = meioCampo.possuiInteresseDeTranferencia(inter);

        Assert.assertFalse(resultado);

    }

    @Test
    public void naoDeveNegociarMeioCampoQuandoReputacaoDeClubeFor2PontosMenor() {
        Negociacao negociacao = new Negociacao();
        Clube gremio = new Clube("Gremio", 10, BigDecimal.valueOf(100000000));
        Clube inter = new Clube("Inter", 10, BigDecimal.valueOf(100000000));
        MeioCampo meioCampo = new MeioCampo("Jobson", 20, gremio, 5, new Indiferente(), BigDecimal.valueOf(10000));

        boolean resultado = negociacao.negociar(inter, meioCampo);

        Assert.assertFalse(resultado);

    }

    @Test
    public void naoDeveNegociarMeioCampoQuandoReputacaoDeClubeFor2PontosMenorEClubeNaoTemDinheiro() {
        Negociacao negociacao = new Negociacao();
        Clube gremio = new Clube("Gremio", 10, BigDecimal.valueOf(100000000));
        Clube inter = new Clube("Inter", 2, BigDecimal.valueOf(100));
        MeioCampo meioCampo = new MeioCampo("Jobson", 20, gremio, 10, new Indiferente(), BigDecimal.valueOf(10000));

        boolean resultado = negociacao.negociar(inter, meioCampo);

        Assert.assertFalse(resultado);

    }
}
