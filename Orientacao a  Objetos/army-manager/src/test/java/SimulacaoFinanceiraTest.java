
import org.junit.Assert;
import org.junit.Test;

import financeiro.*;
import militares.*;
import veiculos.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class SimulacaoFinanceiraTest {
    private Tanque criarTanque() {

        Elite piloto = new Elite(BigDecimal.valueOf(3000),
                LocalDate.now().plusDays(20),
                LocalDate.now().plusDays(20),
                LocalDate.now().plusDays(20),
                LocalDate.now().plusDays(20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new PilotoTanque(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));

        return new Tanque(piloto, tripulacao, 0.22, BigDecimal.valueOf(3.46));
    }

    private Aviao criarAviao() {

        EspecialistaDoAr piloto = new EspecialistaDoAr(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20), LocalDate.now().plusDays(20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(2500)));

        return new Aviao(piloto, tripulacao, 0.14, BigDecimal.valueOf(10));
    }

    @Test
    public void deveOSalarioDoMilitar() {
        Militar militar = new Militar(BigDecimal.valueOf(2500));
        BigDecimal resultadoEsperado = BigDecimal.valueOf(2500);

        BigDecimal resultadoObtido = militar.getSalario();

        Assert.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void deveCalcularOCustoTotalDaMissaoCorretamente() {

        ArrayList<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(criarAviao());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());

        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(1137, veiculos, 1);

        BigDecimal custoTotal = simulacao.getCustoTotalMissao();
        // Se esse teste falhar por centavos de diferença (pequenas diferenças de arredondamento) o valor do teste pode ser ajustado.
        Assert.assertEquals(BigDecimal.valueOf(213623.80), custoTotal);
    }

    @Test
    public void naoDeveValidarTodasAsTropas() {

        ArrayList<Veiculo> veiculos = new ArrayList<>();
        BigDecimal resultadoEsperado = BigDecimal.valueOf(213623.8140);
        veiculos.add(criarAviao());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque());

        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(1137, veiculos, 1);


        Assert.assertFalse(simulacao.todasTripulacoesValidas());
    }

    @Test
    public void deveCalcularOCustoTotalComCombustivelCorretamente() {

        ArrayList<Veiculo> veiculos = new ArrayList<>();

        EspecialistaDoAr piloto = new EspecialistaDoAr(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20), LocalDate.now().plusDays(20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(2500)));
        veiculos.add(new Aviao(piloto, tripulacao, 0.20, BigDecimal.valueOf(10)));

        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(100, veiculos, 1);
        BigDecimal custoTotal = simulacao.getCustoTotalMissao();
        Assert.assertEquals(BigDecimal.valueOf(14500.0), custoTotal);
    }

    @Test
    public void deveCalcularOCustoToralComSalariosCorretamente() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();

        EspecialistaDoAr piloto = new EspecialistaDoAr(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20), LocalDate.now().plusDays(20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(17000)));
        tripulacao.add(new Militar(BigDecimal.valueOf(17000)));
        tripulacao.add(new Militar(BigDecimal.valueOf(17000)));
        tripulacao.add(new Militar(BigDecimal.valueOf(17000)));

        veiculos.add(new Aviao(piloto, tripulacao, 0.20, BigDecimal.valueOf(10)));

        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(100, veiculos, 1);
        BigDecimal custoTotal = simulacao.getCustoTotalMissao();
        Assert.assertEquals(BigDecimal.valueOf(80000.0), custoTotal);
    }

    @Test
    public void deveRetornarSalarioDoEspecialistaTerrestreCorretamente() {
        EspecialistaTerrestre piloto = new EspecialistaTerrestre(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20), LocalDate.now().plusDays(20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        ArrayList<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(new Caminhao(piloto, tripulacao, 0.20, BigDecimal.valueOf(10)));

        BigDecimal resultado = piloto.getSalario();

        Assert.assertEquals(BigDecimal.valueOf(7000), resultado);
    }

    @Test
    public void deveCalcularCorretamenteOCustoDaOperacaoComVariosMilitares() {

        EspecialistaTerrestre especialistaTerrestre = new EspecialistaTerrestre(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20), LocalDate.now().plusDays(20));
        EspecialistaDoAr especialistaDoAr = new EspecialistaDoAr(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20), LocalDate.now().plusDays(20));
        Elite militarElite = new Elite(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20));
        PilotoAviao pilotoAviao = new PilotoAviao(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));
        PilotoHelicoptero pilotoHelicoptero = new PilotoHelicoptero(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));
        PilotoTanque pilotoTanque = new PilotoTanque(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));
        PilotoCaminhao pilotoCaminhao = new PilotoCaminhao(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));


        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        ArrayList<Veiculo> veiculos = new ArrayList<>();

        veiculos.add(new Caminhao(especialistaTerrestre, tripulacao, 0.20, BigDecimal.valueOf(10)));
        veiculos.add(new Helicoptero(especialistaDoAr, tripulacao, 0.20, BigDecimal.valueOf(10)));
        veiculos.add(new Helicoptero(pilotoHelicoptero, tripulacao, 0.20, BigDecimal.valueOf(10)));
        veiculos.add(new Caminhao(pilotoCaminhao, tripulacao, 0.20, BigDecimal.valueOf(10)));
        veiculos.add(new Aviao(pilotoAviao, tripulacao, 0.20, BigDecimal.valueOf(10)));
        veiculos.add(new Tanque(pilotoTanque, tripulacao, 0.20, BigDecimal.valueOf(10)));
        veiculos.add(new Aviao(militarElite, tripulacao, 0.20, BigDecimal.valueOf(10)));


        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(100, veiculos, 1);
        BigDecimal custoTotal = simulacao.getCustoTotalMissao();


        Assert.assertEquals(BigDecimal.valueOf(226800.0), custoTotal);

    }

    @Test
    public void deveNegarValidacaoDaTripulacaoDoCaminhaoQuandoValidadeDaLicencaEstiverInvalida() {
        PilotoCaminhao pilotoCaminhao = new PilotoCaminhao(BigDecimal.valueOf(7000), LocalDate.now());

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        Caminhao caminhao = new Caminhao(pilotoCaminhao, tripulacao, 0.20, BigDecimal.valueOf(10));

        boolean resultado = caminhao.tripulacaoValida();

        Assert.assertFalse(resultado);

    }

    @Test
    public void deveNegarValidacaoDaTripulacaoDoCaminhaoQuandoTripulacaoForMenorQue5() {
        PilotoCaminhao pilotoCaminhao = new PilotoCaminhao(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        Caminhao caminhao = new Caminhao(pilotoCaminhao, tripulacao, 0.20, BigDecimal.valueOf(10));

        boolean resultado = caminhao.tripulacaoValida();

        Assert.assertFalse(resultado);
    }

    @Test
    public void deveNegarValidacaoDaTripulacaoDoCaminhaoQuandoTripulacaoForMaiorQue30() {
        PilotoCaminhao pilotoCaminhao = new PilotoCaminhao(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));

        ArrayList<Militar> tripulacao = new ArrayList<>();


        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        Caminhao caminhao = new Caminhao(pilotoCaminhao, tripulacao, 0.20, BigDecimal.valueOf(10));

        boolean resultado = caminhao.tripulacaoValida();

        Assert.assertFalse(resultado);
    }

    @Test
    public void deveRetornarOPilotoDoCaminhaoCorretamente() {
        PilotoCaminhao pilotoCaminhao = new PilotoCaminhao(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        Caminhao caminhao = new Caminhao(pilotoCaminhao, tripulacao, 0.20, BigDecimal.valueOf(10));


        Assert.assertEquals(pilotoCaminhao, caminhao.getPiloto());
    }

    @Test
    public void deveRetornarPilotoDoHelicopteroCorretamente() {
        PilotoHelicoptero pilotoHelicoptero = new PilotoHelicoptero(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        Helicoptero helicoptero = new Helicoptero(pilotoHelicoptero, tripulacao, 0.20, BigDecimal.valueOf(10));


        Assert.assertEquals(pilotoHelicoptero, helicoptero.getPiloto());
    }

    @Test
    public void deveNegarValidacaoDaTripulacaoDoHelicopteroQuandoTripulacaoForMaiorQue10() {
        PilotoHelicoptero pilotoHelicoptero = new PilotoHelicoptero(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        Helicoptero helicoptero = new Helicoptero(pilotoHelicoptero, tripulacao, 0.20, BigDecimal.valueOf(10));

        boolean resultado = helicoptero.tripulacaoValida();

        Assert.assertFalse(resultado);
    }

    @Test
    public void deveNegarValidacaoDaTripulacaoDoHelicopteroQuandoValidadeDaLicencaEstiverInvalida() {
        PilotoHelicoptero pilotoHelicoptero = new PilotoHelicoptero(BigDecimal.valueOf(7000), LocalDate.now());

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        Helicoptero helicoptero = new Helicoptero(pilotoHelicoptero, tripulacao, 0.20, BigDecimal.valueOf(10));

        boolean resultado = helicoptero.tripulacaoValida();

        Assert.assertFalse(resultado);
    }

    @Test
    public void deveRetornarPilotoDoAviaoCorretamente() {
        PilotoAviao pilotoAviao = new PilotoAviao(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));

        Aviao aviao = new Aviao(pilotoAviao, tripulacao, 0.20, BigDecimal.valueOf(10));


        Assert.assertEquals(pilotoAviao, aviao.getPiloto());
    }

    @Test
    public void deveNegarValidacaoDaTripulacaoDoAviaoQuandoValidadeDaLicencaEstiverInvalida() {
        PilotoAviao pilotoAviao = new PilotoAviao(BigDecimal.valueOf(7000), LocalDate.now());

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));


        Aviao aviao = new Aviao(pilotoAviao, tripulacao, 0.20, BigDecimal.valueOf(10));

        boolean resultado = aviao.tripulacaoValida();

        Assert.assertFalse(resultado);


    }

    @Test
    public void deveNegarValidacaoDaTripulacaoQuandoQuantidadeForMaiorQue1(){
        PilotoAviao pilotoAviao = new PilotoAviao(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));
        tripulacao.add(new Militar(BigDecimal.valueOf(1700)));



        Aviao aviao = new Aviao(pilotoAviao, tripulacao, 0.20, BigDecimal.valueOf(10));

        boolean resultado = aviao.tripulacaoValida();

        Assert.assertFalse(resultado);
    }

}
