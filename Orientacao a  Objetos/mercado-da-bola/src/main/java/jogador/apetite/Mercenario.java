package jogador.apetite;

public class Mercenario implements ApetiteFinanceiro {
    private static final double APETITE = 1.80;

    @Override
    public double getAPETITE() {
        return APETITE;
    }

}
