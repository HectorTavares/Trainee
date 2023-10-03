package militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EspecialistaDoAr extends Militar implements HabilitacaoAviao, HabilitacaoHelicoptero {
    private LocalDate dataValidadeLicencaPilotagemAviao;
    private LocalDate dataValidadeLicencaPilotagemHelicoptero;

    public EspecialistaDoAr(BigDecimal salario, LocalDate dataValidadeLicencaPilotagemAviao, LocalDate dataValidadeLicencaPilotagemHelicoptero) {
        super(salario);
        this.dataValidadeLicencaPilotagemAviao = dataValidadeLicencaPilotagemAviao;
        this.dataValidadeLicencaPilotagemHelicoptero = dataValidadeLicencaPilotagemHelicoptero;
    }

    @Override
    public LocalDate getDataValidadeLicencaPilotagemAviao() {
        return dataValidadeLicencaPilotagemAviao;
    }

    @Override
    public LocalDate getdataValidadeLicencaPilotagemHelicoptero() {
        return dataValidadeLicencaPilotagemHelicoptero;
    }
}
