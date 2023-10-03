package veiculos;

import militares.HabilitacaoCaminhao;
import militares.Militar;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Caminhao extends Veiculo {
    private HabilitacaoCaminhao piloto;


    public Caminhao(HabilitacaoCaminhao piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDeCombustivel) {
        super(tripulacao, quilometragemPorLitro, precoPorLitroDeCombustivel);
        this.piloto = piloto;
    }

    public HabilitacaoCaminhao getPiloto() {
        return piloto;
    }


    @Override
    public boolean tripulacaoValida() {
        boolean validade = false;
        int diferenca = piloto.getdataValidadeLicencaPilotagemCaminhao().compareTo(LocalDate.now());
        if (diferenca > 0 && super.tripulacao.size() >= 5 && super.tripulacao.size() <= 30) {
            validade = true;
        }
        return validade;
    }

    @Override
    public BigDecimal getSalarioPiloto(){
        return piloto.getSalario();
    }
}
