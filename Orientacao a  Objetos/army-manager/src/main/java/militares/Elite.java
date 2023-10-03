package militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Elite extends Militar implements HabilitacaoAviao, HabilitacaoCaminhao, HabilitacaoTanque, HabilitacaoHelicoptero {
    private LocalDate dataValidadeLicencaPilotagemAviao;
    private LocalDate dataValidadeLicencaPilotagemHelicoptero;
    private LocalDate dataValidadeLicencaPilotagemCaminhao;
    private LocalDate dataValidadeLicencaPilotagemTanque;

    public Elite(BigDecimal salario, LocalDate dataValidadeLicencaPilotagemAviao, LocalDate dataValidadeLicencaPilotagemHelicoptero, LocalDate dataValidadeLicencaPilotagemCaminhao, LocalDate dataValidadeLicencaPilotagemTanque) {
        super(salario);
        this.dataValidadeLicencaPilotagemAviao = dataValidadeLicencaPilotagemAviao;
        this.dataValidadeLicencaPilotagemHelicoptero = dataValidadeLicencaPilotagemHelicoptero;
        this.dataValidadeLicencaPilotagemCaminhao = dataValidadeLicencaPilotagemCaminhao;
        this.dataValidadeLicencaPilotagemTanque = dataValidadeLicencaPilotagemTanque;
    }

    @Override
    public LocalDate getDataValidadeLicencaPilotagemAviao() {
        return dataValidadeLicencaPilotagemAviao;
    }

    @Override
    public LocalDate getdataValidadeLicencaPilotagemCaminhao() {
        return dataValidadeLicencaPilotagemCaminhao;
    }

    @Override
    public LocalDate getdataValidadeLicencaPilotagemTanque() {
        return dataValidadeLicencaPilotagemTanque;
    }

    @Override
    public LocalDate getdataValidadeLicencaPilotagemHelicoptero() {
        return dataValidadeLicencaPilotagemHelicoptero;
    }
}
