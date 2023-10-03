package personagens;

import classes.Guerreiro;
import classes.Lado;
import racas.Humano;

import java.util.Map;

public class Boromir extends Personagem implements Guerreiro, Humano {
    private final int forcaBoromir = 7;
    private final int agilidadeBoromir =6;
    private final int inteligenciaBoromir = 3;
    private final int constituicaoInicialBoromir = 40;
    private final Lado ladoBoromir = Lado.SOCIEDADE_DO_ANEL;
    private final String falaBoromir = "One does not simply walk into Mordor.";

    public Boromir() {
        this.forca = forcaBoromir;
        this.agilidade=agilidadeBoromir;
        this.inteligencia=inteligenciaBoromir;
        this.constituicao=constituicaoInicialBoromir;
        this.lado = ladoBoromir;
    }

    @Override
    public void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) { atacar(mapaAcao, posicaoAtual);movimentar(mapaAcao, posicaoAtual); }

    @Override
    public void envelhecer() {
        this.constituicao -= 2;
    }

    @Override
    public String falar() {
        return falaBoromir;
    }

    @Override
    public String toString() {
        return "B";
    }
}
