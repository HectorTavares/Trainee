package militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoCaminhao extends Militar implements HabilitacaoCaminhao {
    private LocalDate dataValidadeLicencaPilotagemCaminhao;

    public PilotoCaminhao(BigDecimal salario, LocalDate dataValidadeLicencaPilotagemCaminhao) {
        super(salario);
        this.dataValidadeLicencaPilotagemCaminhao = dataValidadeLicencaPilotagemCaminhao;
    }

    @Override
    public LocalDate getdataValidadeLicencaPilotagemCaminhao() {
        return dataValidadeLicencaPilotagemCaminhao;
    }
}
