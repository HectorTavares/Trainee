package veiculos;

import militares.HabilitacaoAviao;
import militares.Militar;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Aviao extends Veiculo {
    private HabilitacaoAviao piloto;


    public Aviao(HabilitacaoAviao piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDeCombustivel) {
        super(tripulacao, quilometragemPorLitro, precoPorLitroDeCombustivel);
        this.piloto = piloto;
    }

    public HabilitacaoAviao getPiloto() {
        return piloto;
    }

    @Override
    public boolean tripulacaoValida() {
        boolean validade = false;
        int diferenca = piloto.getDataValidadeLicencaPilotagemAviao().compareTo(LocalDate.now());
        if (diferenca > 0 && super.tripulacao.size() <= 1) {
            validade = true;
        }
        return validade;
    }

    @Override
    public BigDecimal getSalarioPiloto(){
        return piloto.getSalario();
    }

}
