package veiculos;

import militares.HabilitacaoTanque;
import militares.Militar;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Tanque extends Veiculo {
    private HabilitacaoTanque piloto;


    public Tanque(HabilitacaoTanque piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDeCombustivel) {
        super(tripulacao, quilometragemPorLitro, precoPorLitroDeCombustivel);
        this.piloto = piloto;
    }

    public HabilitacaoTanque getPiloto() {
        return piloto;
    }


    @Override
    public boolean tripulacaoValida() {
        boolean validade = false;
        int diferenca = piloto.getdataValidadeLicencaPilotagemTanque().compareTo(LocalDate.now());
        if (diferenca > 0 && super.tripulacao.size() == 3) {
            validade = true;
        }
        return validade;
    }

    @Override
    public BigDecimal getSalarioPiloto(){
        return piloto.getSalario();
    }
}
