package financeiro;

import militares.Militar;
import veiculos.Veiculo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SimulacaoFinanceira {

    private int distanciaViajem;
    private List<Veiculo> veiculosDaMissao;
    private int duracaoDaMissao; //(em meses)

    public SimulacaoFinanceira(int distanciaViajem, List<Veiculo> veiculosDaMissao, int duracaoDaMissao) {
        this.distanciaViajem = distanciaViajem;
        this.veiculosDaMissao = veiculosDaMissao;
        this.duracaoDaMissao = duracaoDaMissao;
    }

    public BigDecimal getCustoTotalMissao() {
        BigDecimal custoSalario = BigDecimal.valueOf(0);
        BigDecimal custoCombustivel = BigDecimal.valueOf(0);

        for (Veiculo veiculo : veiculosDaMissao) {
            custoCombustivel = custoCombustivel.add(gastoCombustivel(veiculo));
            List<Militar> tripulacao = veiculo.getTripulacao();

            custoSalario = custoSalario.add(veiculo.getSalarioPiloto());
            for (Militar militar : tripulacao) {
                custoSalario = custoSalario.add(militar.getSalario());
            }
        }
        custoSalario = custoSalario.multiply(BigDecimal.valueOf(duracaoDaMissao));
        return custoSalario.add(custoCombustivel);
    }

    public BigDecimal gastoCombustivel(Veiculo veiculo) {

        BigDecimal gasto;
        //distancia da viagem / quilometragem por litro do veiculo * preco do litro de combustivel para o veículo
        gasto = BigDecimal.valueOf(distanciaViajem).divide(BigDecimal.valueOf(veiculo.getQuilometragemPorLitro()), 2, RoundingMode.HALF_UP).multiply(veiculo.getPrecoPorLitroDeCombustivel()).setScale(1, RoundingMode.HALF_EVEN);
        return gasto;
    }

    public boolean todasTripulacoesValidas() {
        boolean validade = true;
        for (Veiculo veiculo : veiculosDaMissao) {
            if (veiculo.tripulacaoValida()) {
                validade = false;
            }
        }

        return validade;
    }
}
