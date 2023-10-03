package mapa;

import exception.PersonagemJaEstaNoMapaException;
import exception.PersonagemNaoEncontradoNoMapaException;
import exception.PosicaoOcupadaException;
import personagens.Aragorn;
import personagens.Personagem;
import util.Conversor;

import java.util.HashMap;
import java.util.Map;

public class Mapa {
    private final Map<Integer, Personagem> mapa;

    //O mapa representa o caminho que os personagens vão percorrer,
    // é composto de 10 posições de personagens e obrigatoriamente deve possuir estes métodos:

    public Mapa() {
        mapa = new HashMap<>();


    }

    public Map<Integer, Personagem> getMapa() {
        return mapa;
    }

    public String exibir() {
        String[] mapa = {"|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|"};

        for (int i = 0, j = 1; i <= 9; i++, j += 2) {
            if (this.mapa.containsKey(i) && this.mapa.get(i)!=null) {
                    String aux = this.mapa.get(i).toString();
                    mapa[j] = aux;
            }
        }
        return Conversor.converter(mapa);
    }

    //String exibir(): retorna uma String com as 10 posições do mapa:
    //
    //Ex. 1: O mapa está vazio: | | | | | | | | | | |
    //Ex. 2: O mapa está populado com Aragorn na 1ª posição e Saruman na 10ª posição: |A| | | | | | | | |S|
    //Ex. 3: Mapa com Legolas, Gimli, Orc e Goblim: | |L| |I| | |O| | |M|
    //


    //void inserir(int posicao, personagens.Personagem personagem): adiciona o personagem naquela posição do mapa. Este método pode lançar duas Exceptions:
    //
    //PosicaoOcupadaException: se na posição passada como parâmetro já existir um personagem.
    //PersonagemJaEstaNoMapaException: se aquele mesmo personagem passada como parâmetro já existir no mapa.
    //

    public void inserir(int posicao, Personagem personagem) {
        if (mapa.containsKey(posicao) && mapa.get(posicao)!=null) {
            throw new PosicaoOcupadaException();
        } else if (mapa.containsValue(personagem) && mapa.get(posicao)!=null) {
            throw new PersonagemJaEstaNoMapaException();
        } else {
            mapa.put(posicao, personagem);
        }
    }


    //int buscarPosicao(personagens.Personagem personagem): retorna a posição daquele personagem no mapa. Deve lançar a Exceção:
    //
    //PersonagemNaoEncontradoNoMapaException: se aquele personagem não existir no mapa.

    public int buscarPosicao(Personagem personagem) {
        if (mapa.containsValue(personagem)) {
            for (int i = 0; i <= 9; i++) {
                if (mapa.get(i) != null && mapa.get(i).equals(personagem)) {
                    return i;
                }
            }
        }
        throw new PersonagemNaoEncontradoNoMapaException();
    }
    //
    //personagens.Personagem buscarCasa(int posicao): busca o personagem naquela posição, pode não existir um personagem lá.
    //
    //Importante: Garantir que um personagem morto (constituição = 0) não apareça no mapa.
}
