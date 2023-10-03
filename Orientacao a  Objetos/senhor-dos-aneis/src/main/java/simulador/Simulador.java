package simulador;

import classes.Lado;
import exception.SauronDominaOMundoException;
import personagens.*;
import mapa.Mapa;
import personagens.Aragorn;

import java.util.Collection;
import java.util.List;

public class Simulador {
    private final Mapa mapa;

    public Simulador(Mapa mapa) {
        this.mapa = mapa;
    }
    //void simular(): deve percorrer o mapa da esquerda para direita, realizando o turno de cada personagem. No seu turno um personagem ataca e depois se movimenta (ambos respeitando
    //as regras de sua classe). Após todos os personagens realizarem seus turnos, o ciclo reinicia até que uma das condições de vitória seja atingida:
    //
    //Vitória para a Sociedade do Anel: se algum membro da Sociedade chegar até a última posição do mapa (posição 9).
    //Vitória para Sauron: se não existir nenhum membro da Sociedade do Anel no mapa. Neste caso deve ser lançada uma Exception SauronDominaOMundoException com a descrição
    //"A humanidade sofre perante a tirania de Sauron.".
    //Importante: Comportamentos de raças não precisam ser utilizados durante a simulação

    public void simular() {
        boolean deveSimular = true;
        System.out.println(mapa.exibir());

        while (deveSimular) {
            boolean aliancaLuta = false;
            for (int i = 0; i <= 9; i++) {
                if (mapa.getMapa().containsKey(i) && !mapa.getMapa().get(i).isJaJogou()) {
                    Personagem personagemDoRound = mapa.getMapa().get(i);
                    personagemDoRound.acao(mapa.getMapa(), i);
                    personagemDoRound.setJaJogou(true);
                    System.out.println(mapa.exibir());
                }
                for (int j = i; j <= 9; j++) {
                    if (mapa.getMapa().get(j) != null && mapa.getMapa().get(j).getLado() == Lado.SOCIEDADE_DO_ANEL) {
                        aliancaLuta = true;
                    }
                }
                if (mapa.getMapa().get(9) != null && mapa.getMapa().get(9).getLado() == Lado.SOCIEDADE_DO_ANEL) {
                    deveSimular = false;
                    break;
                }
            }
            for (int j = 0; j <= 9; j++) {
                if (mapa.getMapa().containsKey(j)) {
                    mapa.getMapa().get(j).setJaJogou(false);
                }
            }
            if (!aliancaLuta) {
                throw new SauronDominaOMundoException();
            }
        }
    }
}