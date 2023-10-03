package personagens;

import classes.Lado;
import classes.Mago;
import racas.Maia;

import java.util.Map;

public class Gandalf extends Personagem implements Mago, Maia {
    private final int forcaGandalf = 2;
    private final int agilidadeGandalf = 3;
    private final int inteligenciaGandalf = 10;
    private final int contituicaoInicialGandalf = 80;
    private final Lado ladoGandalf = Lado.SOCIEDADE_DO_ANEL;
    private final String falaGandalf = "A Wizard is never late, nor is he early. He arrives precisely when he means to.";



    public Gandalf() {
        this.forca=forcaGandalf;
        this.agilidade=agilidadeGandalf;
        this.inteligencia=inteligenciaGandalf;
        this.constituicao=contituicaoInicialGandalf;
        this.lado = ladoGandalf;
    }


    @Override
    public void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) { atacar(mapaAcao, posicaoAtual);movimentar(mapaAcao, posicaoAtual); }


    @Override
    public Object ressucitar() {
        if (getConstituicao() == 0) {
            return new Gandalf();
        }
        return null;
    }

    @Override
    public String falar() {
        return this.falaGandalf;
    }

    @Override
    public String toString() {
        return "G";
    }
}
