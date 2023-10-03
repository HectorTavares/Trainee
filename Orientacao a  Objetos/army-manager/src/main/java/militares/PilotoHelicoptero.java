package militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoHelicoptero extends Militar implements HabilitacaoHelicoptero {
    private LocalDate dataValidadeLicencaPilotagemHelicoptero;

    public PilotoHelicoptero(BigDecimal salario, LocalDate dataValidadeLicencaPilotagemHelicoptero) {
        super(salario);
        this.dataValidadeLicencaPilotagemHelicoptero = dataValidadeLicencaPilotagemHelicoptero;
    }

    @Override
    public LocalDate getdataValidadeLicencaPilotagemHelicoptero() {
        return dataValidadeLicencaPilotagemHelicoptero;
    }
}
