package militares;

import java.math.BigDecimal;

public class Militar implements Habilitacao{
    protected BigDecimal salario;

    public Militar(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return salario;
    }

}
