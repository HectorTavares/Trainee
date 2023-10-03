package classes;

import personagens.Personagem;
import util.Check;

import java.util.Map;

public interface Mago {
    //O ataque de um mago equivale a sua inteligência, quando atacar causa dano a todos os personagens inimigos
    default void atacar(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) {
        Personagem mago = mapaAcao.get(posicaoAtual);
        if (mago.getLado() == Lado.SOCIEDADE_DO_ANEL) {
            for (int i = posicaoAtual + 1; i <= 10; i++) {
                if (mapaAcao.get(posicaoAtual + i) != null && mapaAcao.get(posicaoAtual + i).getLado() != mago.getLado()) {
                    Personagem atacado = mapaAcao.get(posicaoAtual + i);
                    int dano = mago.getInteligencia();
                    atacado.setConstituicao(atacado.getConstituicao() - dano);
                    atacado.estaVivo(mapaAcao, posicaoAtual + i);
                }
            }
        } else if (mago.getLado() == Lado.OUTROS) {
            for (int i = posicaoAtual - 1; i >= 1; i--) {
                if (mapaAcao.get(posicaoAtual + i) != null && mapaAcao.get(posicaoAtual + i).getLado() != mago.getLado()) {
                    Personagem atacado = mapaAcao.get(posicaoAtual - i);
                    int dano = mago.getInteligencia();
                    atacado.setConstituicao(atacado.getConstituicao() - dano);
                    atacado.estaVivo(mapaAcao, posicaoAtual - i);
                }
            }
        }
    }


    default void movimentar(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) {
        Personagem mago = mapaAcao.get(posicaoAtual);

        if (mago.getLado() == Lado.SOCIEDADE_DO_ANEL) {
            boolean podeAndar = Check.checkSociedade(posicaoAtual, mapaAcao);
            if (podeAndar) {
                mapaAcao.put(posicaoAtual + 1, mago);
                mapaAcao.remove(posicaoAtual);
            }
        } else if (mago.getLado() == Lado.OUTROS) {
            boolean podeAndar = Check.checkOutros(posicaoAtual, mapaAcao);
            if (podeAndar) {
                mapaAcao.put(posicaoAtual - 1, mago);
                mapaAcao.remove(posicaoAtual);
            }
        }
    }


    //**pensar melhor a forma que vou modelar isso.**
}
