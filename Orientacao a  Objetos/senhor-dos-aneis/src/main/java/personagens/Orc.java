package personagens;

import classes.Guerreiro;
import classes.Lado;
import racas.Monstro;

import java.util.Map;

public class Orc extends Personagem implements Guerreiro, Monstro {

    private final int forcaOrc =7;
    private final int agilidadeOrc = 4;
    private final int inteligenciaOrc = 1;
    private final int contituicaoInicialOrc = 30;
    private final Lado ladoOrc = Lado.OUTROS;
    private final String grunhidoOrc = "Arrrggghhh";

    public Orc() {
        this.forca=forcaOrc;
        this.agilidade=agilidadeOrc;
        this.inteligencia=inteligenciaOrc;
        this.constituicao=contituicaoInicialOrc;
        this.lado= ladoOrc;
    }

    @Override
    public void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) { atacar(mapaAcao, posicaoAtual);movimentar(mapaAcao, posicaoAtual); }

    @Override
    public String grunir() {
        return grunhidoOrc;
    }

    @Override
    public String toString() {
        return "O";
    }
}
