package personagens;

import classes.Arqueiro;
import classes.Lado;
import racas.Elfo;

import java.util.Map;

public class Legolas extends Personagem implements Arqueiro, Elfo {
    private final int forcaLegolas = 3;
    private final int agilidadeLegolas = 10;
    private final int inteligenciaLegolas = 6;
    private final int contituicaoInicialLegolas = 80;
    private final Lado ladoLegolas = Lado.SOCIEDADE_DO_ANEL;
    private final String fala = "They're taking the Hobbits to Isengard!";
    private final String falaElfica = "I amar prestar aen, han mathon ne nem, han mathon ne chae, a han noston ned.";

    public Legolas() {
        this.forca=forcaLegolas;
        this.agilidade=agilidadeLegolas;
        this.inteligencia=inteligenciaLegolas;
        this.constituicao=contituicaoInicialLegolas;
        this.lado = ladoLegolas;
    }

    @Override
    public void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) { atacar(mapaAcao, posicaoAtual);movimentar(mapaAcao, posicaoAtual); }

    @Override
    public String falarElfico() {
        return falaElfica;
    }

    @Override
    public String falar() {
        return fala;
    }

    @Override
    public String toString() {
        return "L";
    }
}
