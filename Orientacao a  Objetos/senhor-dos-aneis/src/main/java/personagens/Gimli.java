package personagens;

import classes.Guerreiro;
import classes.Lado;
import racas.Anao;

import java.util.Map;

public class Gimli extends Personagem implements Guerreiro, Anao {

    private final int forcaGimli = 9;
    private final int agilidadeGimli = 2;
    private final int inteligenciaGimli = 4;
    private final int contituicaoInicialGimli= 60;
    private final Lado ladoGimli = Lado.SOCIEDADE_DO_ANEL;
    private final String fala = "Let them come. There is one Dwarf yet in Moria who still draws breath.";
    private final String falaBebado = "What did I say? He can't hold his liquor!";
    private int vezesBebidas = 0;


    public Gimli() {
        this.forca=forcaGimli;
        this.agilidade=agilidadeGimli;
        this.inteligencia=inteligenciaGimli;
        this.constituicao=contituicaoInicialGimli;
        this.lado=ladoGimli;
    }

    @Override
    public void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) { atacar(mapaAcao, posicaoAtual);movimentar(mapaAcao, posicaoAtual); }

    @Override
    public void beber() {
        this.vezesBebidas += 1;
    }

    @Override
    public String falar() {
        if (vezesBebidas >= 3) {
            return falaBebado;
        }
        return fala;
    }

    @Override
    public String toString() {
        return "I";
    }
}
