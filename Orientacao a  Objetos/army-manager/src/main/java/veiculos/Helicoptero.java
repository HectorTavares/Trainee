package veiculos;


import militares.HabilitacaoHelicoptero;
import militares.Militar;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Helicoptero extends Veiculo {
    private HabilitacaoHelicoptero piloto;


    public Helicoptero(HabilitacaoHelicoptero piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDeCombustivel) {
        super(tripulacao, quilometragemPorLitro, precoPorLitroDeCombustivel);
        this.piloto = piloto;
    }

    public HabilitacaoHelicoptero getPiloto() {
        return piloto;
    }


    @Override
    public boolean tripulacaoValida() {
        boolean validade = false;
        int diferenca = piloto.getdataValidadeLicencaPilotagemHelicoptero().compareTo(LocalDate.now());
        if (diferenca > 0 && super.tripulacao.size() <= 10) {
            validade = true;
        }
        return validade;
    }
    @Override
    public BigDecimal getSalarioPiloto(){
        return piloto.getSalario();
    }
}
