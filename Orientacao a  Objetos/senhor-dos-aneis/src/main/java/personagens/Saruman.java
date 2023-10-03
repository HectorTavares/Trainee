package personagens;

import classes.Lado;
import classes.Mago;
import racas.Maia;

import java.util.Map;

public class Saruman extends Personagem implements Mago, Maia {
    private final int forcaSaruman =2;
    private final int agilidadeSaruman = 2;
    private final int inteligenciaSaruman = 9;
    private final int contituicaoInicialSaruman = 70;
    private final Lado ladoSaruman = Lado.OUTROS;
    private final String falaSaruman = "Against the power of Mordor there can be no victory.";

    public Saruman() {
       this.forca=forcaSaruman;
       this.agilidade=agilidadeSaruman;
       this.inteligencia=inteligenciaSaruman;
       this.constituicao=contituicaoInicialSaruman;
       this.lado=ladoSaruman;
    }

    @Override
    public void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) { atacar(mapaAcao, posicaoAtual);movimentar(mapaAcao, posicaoAtual); }

    @Override
    public Object ressucitar() {
        return null;
    }

    @Override
    public String falar() {
        return falaSaruman;
    }

    @Override
    public String toString() {
        return "S";
    }
}
