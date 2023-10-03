package jogador.apetite;

public class Indiferente implements ApetiteFinanceiro {
    private static final double APETITE = 1;

    @Override
    public double getAPETITE() {
        return APETITE;
    }
}

