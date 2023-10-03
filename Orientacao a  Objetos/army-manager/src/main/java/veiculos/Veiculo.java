package veiculos;

import militares.Militar;

import java.math.BigDecimal;
import java.util.List;

public abstract class Veiculo {

    protected List<Militar> tripulacao;
    protected double quilometragemPorLitro;
    protected BigDecimal precoPorLitroDeCombustivel;



    protected Veiculo(List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDeCombustivel) {
        this.tripulacao = tripulacao;
        this.quilometragemPorLitro = quilometragemPorLitro;
        this.precoPorLitroDeCombustivel = precoPorLitroDeCombustivel;
    }

    public List<Militar> getTripulacao() {
        return tripulacao;
    }


    public double getQuilometragemPorLitro() {
        return quilometragemPorLitro;
    }

    public BigDecimal getPrecoPorLitroDeCombustivel() {
        return precoPorLitroDeCombustivel;
    }

    public boolean tripulacaoValida() {
        return true;
    }

    public BigDecimal getSalarioPiloto(){
        return BigDecimal.valueOf(0);
    }



}
