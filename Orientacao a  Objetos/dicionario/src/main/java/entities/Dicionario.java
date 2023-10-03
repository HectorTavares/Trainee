package entities;

import exception.PalavraNaoEncontradaException;

import java.util.HashMap;

import util.Formatador;

import java.util.Map;

public class Dicionario {

    Map<String, String> portugues;
    Map<String, String> ingles;


    public Dicionario() {
        this.portugues = new HashMap<>();
        this.ingles = new HashMap<>();
    }

    public void adicionarPalavra(String palavra, String traducao, TipoDicionario dicionario) {
        if (dicionario == TipoDicionario.PORTUGUES) {
            portugues.put(Formatador.formatar(palavra), Formatador.formatar(traducao));
            ingles.put(Formatador.formatar(traducao),Formatador.formatar(palavra));
        } else if (dicionario == TipoDicionario.INGLES) {
            ingles.put(Formatador.formatar(palavra), Formatador.formatar(traducao));
            portugues.put(Formatador.formatar(traducao),Formatador.formatar(palavra));
        }
    }

    public String traduzir(String palavra, TipoDicionario dicionarioDeBusca) {
        String traducao = null;
        try {
            if (dicionarioDeBusca == TipoDicionario.PORTUGUES) {
                traducao = portugues.get(Formatador.formatar(palavra));
                traducao = Formatador.formatar(traducao);
            } else if (dicionarioDeBusca == TipoDicionario.INGLES) {
                traducao = ingles.get(Formatador.formatar(palavra));
                traducao = Formatador.formatar(traducao);
            }

        } catch (NullPointerException e) {
            throw new PalavraNaoEncontradaException();
        }
        return traducao;
    }
}
