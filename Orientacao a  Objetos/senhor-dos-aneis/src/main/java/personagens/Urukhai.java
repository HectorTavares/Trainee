package personagens;

import classes.Guerreiro;
import classes.Lado;
import racas.Humano;
import racas.Monstro;

import java.util.Map;

public class Urukhai extends Personagem implements Guerreiro, Monstro, Humano {

    private final int forcaUrukhai =8;
    private final int agilidadeUrukhai = 6;
    private final int inteligenciaUrukhai = 3;
    private final int contituicaoInicialUrukhai = 45;
    private final Lado ladoUrukhai = Lado.OUTROS;
    private final String grunhidoUrukhai = "Uuurrrrrr";
    private final String falaUrukhai = "Looks like meat's back on the menu boys!";

    public Urukhai() {
        this.forca=forcaUrukhai;
        this.agilidade=agilidadeUrukhai;
        this.inteligencia=inteligenciaUrukhai;
        this.constituicao=contituicaoInicialUrukhai;
        this.lado=ladoUrukhai;
    }

    @Override
    public void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) { atacar(mapaAcao, posicaoAtual);movimentar(mapaAcao, posicaoAtual); }

    @Override
    public void envelhecer() {
        this.constituicao -= 2;
    }

    @Override
    public String falar() {
        return falaUrukhai;
    }

    @Override
    public String grunir() {
        return grunhidoUrukhai;
    }

    public String toString() {
        return "U";
    }
}
