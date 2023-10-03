package entities;

import recursos.Recursos;


import java.util.List;

public class Planeta {
    private int posicao;
    private List<Recursos> recursos;

    public Planeta(int posicao, List<Recursos> recursos) {
        this.posicao = posicao;
        this.recursos = recursos;
    }

    public List<Recursos> getRecursos() {
        return recursos;
    }

    public int getPosicao() {
        return posicao;
    }

    public int valorTotal() {
        int soma = 0;
        for (Recursos recurso : recursos) {
            soma += recurso.getValor();
        }
        return soma;
    }

    public int valorPorPeso() {
        int soma = 0;
        for (Recursos recurso : recursos) {
            soma += recurso.getValor() / recurso.getPeso();
        }
        return soma;
    }
}
