package personagens;

import classes.Arqueiro;
import classes.Lado;
import racas.Monstro;

import java.util.Map;

public class Goblim extends Personagem implements Arqueiro, Monstro {

    private final int forcaGoblim = 3;
    private final int agilidadeGoblim = 6;
    private final int inteligenciaGoblim = 1;
    private final int contituicaoInicialGoblim = 20;
    private final Lado ladoGoblim = Lado.OUTROS;
    private final String grunhidoGoblin = "Iiisshhhh";

    public Goblim() {
      this.forca=forcaGoblim;
      this.agilidade=agilidadeGoblim;
      this.inteligencia=inteligenciaGoblim;
      this.constituicao=contituicaoInicialGoblim;
      this.lado=ladoGoblim;
    }

    @Override
    public void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) { atacar(mapaAcao, posicaoAtual);movimentar(mapaAcao, posicaoAtual); }

    @Override
    public String grunir() {
        return grunhidoGoblin;
    }

    @Override
    public String toString() {
        return "M";
    }
}
