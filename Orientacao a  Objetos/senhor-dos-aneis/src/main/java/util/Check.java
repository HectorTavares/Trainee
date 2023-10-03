package util;

import personagens.Personagem;

import java.util.Map;

public class Check {
    public static boolean checkSociedade(int posicaoAtual, Map<Integer, Personagem> mapaAcao) {
        boolean podeAndar = true;
        int posicaoMaxima = 10;
        for (int i = posicaoAtual + 1; i < 10; i++) {
            if (mapaAcao.containsKey(i) && posicaoAtual < posicaoMaxima) {
                podeAndar = false;
                break;
            }
        }
        return podeAndar;
    }

    public static boolean checkOutros(int posicaoAtual,Map<Integer, Personagem> mapaAcao){
        boolean podeAndar = true;
        int posicaoMinima = 1;
        for (int i = posicaoAtual - 1; i >= 0; i--) {
            if (mapaAcao.containsKey(i) && posicaoAtual > posicaoMinima) {
                podeAndar = false;
                break;
            }
        }
        return podeAndar;
    }
}
