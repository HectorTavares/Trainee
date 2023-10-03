package militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoAviao extends Militar implements HabilitacaoAviao {
    private LocalDate dataValidadeLicencaPilotagemAviao;

    public PilotoAviao(BigDecimal salario, LocalDate dataValidadeLicencaPilotagemAviao) {
        super(salario);
        this.dataValidadeLicencaPilotagemAviao = dataValidadeLicencaPilotagemAviao;
    }

    @Override
    public LocalDate getDataValidadeLicencaPilotagemAviao() {
        return dataValidadeLicencaPilotagemAviao;
    }
}
