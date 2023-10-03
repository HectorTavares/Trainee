package militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoTanque extends Militar implements HabilitacaoTanque {
    private LocalDate dataValidadeLicencaPilotagemTanque;

    public PilotoTanque(BigDecimal salario, LocalDate dataValidadeLicencaPilotagemTanque) {
        super(salario);
        this.dataValidadeLicencaPilotagemTanque = dataValidadeLicencaPilotagemTanque;
    }

    @Override
    public LocalDate getdataValidadeLicencaPilotagemTanque() {
        return dataValidadeLicencaPilotagemTanque;
    }
}
