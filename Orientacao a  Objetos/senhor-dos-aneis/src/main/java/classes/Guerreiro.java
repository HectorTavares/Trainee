package classes;

import personagens.Personagem;

import java.util.Map;

public interface Guerreiro {
    default void atacar(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) {
        Personagem guerreiro = mapaAcao.get(posicaoAtual);
        if (guerreiro.getLado() == Lado.SOCIEDADE_DO_ANEL) {
            if (mapaAcao.containsKey(posicaoAtual + 1) && mapaAcao.get(posicaoAtual + 1).getLado() != guerreiro.getLado()) {
                Personagem atacado = mapaAcao.get(posicaoAtual + 1);
                int dano = guerreiro.getForca() * 2;
                atacado.setConstituicao(atacado.getConstituicao() - dano);
                atacado.estaVivo(mapaAcao, posicaoAtual + 1);
            }
        } else if (guerreiro.getLado() == Lado.OUTROS) {
            if (mapaAcao.containsKey(posicaoAtual - 1) && mapaAcao.get(posicaoAtual - 1).getLado() != guerreiro.getLado()) {
                Personagem atacado = mapaAcao.get(posicaoAtual - 1);
                int dano = guerreiro.getForca() * 2;
                atacado.setConstituicao(atacado.getConstituicao() - dano);
                atacado.estaVivo(mapaAcao, posicaoAtual - 1);
            }
        }
    }


    default void movimentar(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) {
        Personagem guerreiro = mapaAcao.get(posicaoAtual);
        int posicaoMaxima = 9;
        int posicaoMinima = 0;
        if (guerreiro.getLado() == Lado.SOCIEDADE_DO_ANEL) {
            if (!mapaAcao.containsKey(posicaoAtual + 1) && posicaoAtual + 1 <= posicaoMaxima) {
                mapaAcao.put(posicaoAtual + 1, guerreiro);
                mapaAcao.remove(posicaoAtual);
            }
        } else if (guerreiro.getLado() == Lado.OUTROS) {
            if (!mapaAcao.containsKey(posicaoAtual - 1) && posicaoAtual - 1 >= posicaoMinima) {
                mapaAcao.put(posicaoAtual - 1, guerreiro);
                mapaAcao.remove(posicaoAtual);
            }
        }
    }

    //Um guerreiro ataca apenas personagens inimigos próximos a ele:
    //O dano causado pelo guerreiro equivale a 2x sua força.
}
