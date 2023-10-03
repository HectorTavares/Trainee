package militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EspecialistaTerrestre extends Militar implements HabilitacaoCaminhao, HabilitacaoTanque {
    private LocalDate dataValidadeLicencaPilotagemCaminhao;
    private LocalDate dataValidadeLicencaPilotagemTanque;

    public EspecialistaTerrestre(BigDecimal salario, LocalDate dataValidadeLicencaPilotagemCaminhao, LocalDate dataValidadeLicencaPilotagemTanque) {
        super(salario);
        this.dataValidadeLicencaPilotagemCaminhao = dataValidadeLicencaPilotagemCaminhao;
        this.dataValidadeLicencaPilotagemTanque = dataValidadeLicencaPilotagemTanque;
    }

    @Override
    public LocalDate getdataValidadeLicencaPilotagemCaminhao() {
        return dataValidadeLicencaPilotagemCaminhao;
    }

    @Override
    public LocalDate getdataValidadeLicencaPilotagemTanque() {
        return dataValidadeLicencaPilotagemTanque;
    }
}
