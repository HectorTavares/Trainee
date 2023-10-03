package classes;

import personagens.Personagem;

import java.util.Map;

public interface Arqueiro {
    default void atacar(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) {
        Personagem arqueiro = mapaAcao.get(posicaoAtual);
        if (arqueiro.getLado() == Lado.SOCIEDADE_DO_ANEL) {
            for (int i = 3; i > 0; i--) {
                if (mapaAcao.get(posicaoAtual + i) != null && mapaAcao.get(posicaoAtual + i).getLado() != arqueiro.getLado()) {
                    Personagem atacado = mapaAcao.get(posicaoAtual + i);
                    int dano = arqueiro.getAgilidade() * i;
                    atacado.setConstituicao(atacado.getConstituicao() - dano);
                    atacado.estaVivo(mapaAcao, posicaoAtual + i);
                    break;
                }
            }
        } else if (arqueiro.getLado() == Lado.OUTROS) {
            for (int i = 3; i > 0; i--) {
                if (mapaAcao.get(posicaoAtual - i) != null && mapaAcao.get(posicaoAtual - i).getLado() != arqueiro.getLado()) {
                    Personagem atacado = mapaAcao.get(posicaoAtual - i);
                    int dano = arqueiro.getAgilidade() * i;
                    atacado.setConstituicao(atacado.getConstituicao() - dano);
                    atacado.estaVivo(mapaAcao, posicaoAtual - i);
                    break;
                }
            }
        }
    }

    default void movimentar(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual) {
        Personagem arqueiro = mapaAcao.get(posicaoAtual);
        int posicaoMaxima = 9;
        int posicaoMinima = 0;
        if (arqueiro.getLado() == Lado.SOCIEDADE_DO_ANEL) {
            if (!mapaAcao.containsKey(posicaoAtual + 1) && posicaoAtual + 1 <= posicaoMaxima) {
                mapaAcao.put(posicaoAtual + 1, arqueiro);
                mapaAcao.remove(posicaoAtual);
                if (!mapaAcao.containsKey(posicaoAtual + 2) && posicaoAtual + 2 <= posicaoMaxima) {
                    mapaAcao.put(posicaoAtual + 2, arqueiro);
                    mapaAcao.remove(posicaoAtual + 1);
                }
            }
        } else if (arqueiro.getLado() == Lado.OUTROS) {
            if (!mapaAcao.containsKey(posicaoAtual - 1) && posicaoAtual - 1 >= posicaoMinima) {
                mapaAcao.put(posicaoAtual - 1, arqueiro);
                mapaAcao.remove(posicaoAtual);
                if (!mapaAcao.containsKey(posicaoAtual - 2) && posicaoAtual - 2 >= posicaoMinima) {
                    mapaAcao.put(posicaoAtual - 2, arqueiro);
                    mapaAcao.remove(posicaoAtual - 1);
                }
            }
        }
    }
}

