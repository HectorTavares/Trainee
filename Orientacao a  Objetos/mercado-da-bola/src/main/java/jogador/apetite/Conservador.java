package jogador.apetite;

public class Conservador implements ApetiteFinanceiro {
    private static final double APETITE = 1.40;

    @Override
    public double getAPETITE() {
        return APETITE;
    }
}
