package personagens;

import classes.Guerreiro;
import classes.Lado;
import racas.Humano;

import java.util.Map;

public class Aragorn extends Personagem implements Guerreiro, Humano {

    private final int forcaAragorn = 10;
    private final int agilidadeAragorn = 7;
    private final int inteligenciaAragorn = 6;
    private final int contituicaoInicialAragorn = 60;
    private final Lado ladoAragorn = Lado.SOCIEDADE_DO_ANEL;
    private final String falaAragorn = "A day may come when the courage of men fails... but it is not THIS day.";

    public Aragorn() {
        this.forca = forcaAragorn;
        this.agilidade = agilidadeAragorn;
        this.inteligencia = inteligenciaAragorn;
        this.constituicao = contituicaoInicialAragorn;
        this.lado = ladoAragorn;
    }

    @Override
    public void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) { atacar(mapaAcao, posicaoAtual);movimentar(mapaAcao, posicaoAtual); }

    @Override
    public void envelhecer() {
        this.constituicao -= 1;
    }

    @Override
    public String falar() {
        return this.falaAragorn;
    }

    @Override
    public String toString() {
        return "A";
    }
}

