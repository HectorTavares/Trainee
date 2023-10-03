package jogador;

import clube.Clube;
import jogador.apetite.ApetiteFinanceiro;

import java.math.BigDecimal;

public class Atacante extends Jogador {

    private int golsFeitosNoAno;
    private static final double COEFICIENTEIDADE = 0.75;

    public Atacante(String nome, int idade, Clube clubeAtual, int reputacaoHistoria, ApetiteFinanceiro apetiteFinanceiro, BigDecimal preco, int golsFeitosNoAno) {
        super(nome, idade, clubeAtual, reputacaoHistoria, apetiteFinanceiro, preco);
        this.golsFeitosNoAno = golsFeitosNoAno;
    }


    @Override
    public double valorDeCompra() {
        double valorPadrao = super.valorDeCompra();
        double coeficienteGols = (golsFeitosNoAno*0.01)+1;
        if (idade > 30) {
            return (valorPadrao * coeficienteGols) * (COEFICIENTEIDADE);
        }
        return valorPadrao * coeficienteGols;
    }

    @Override
    public boolean possuiInteresseDeTranferencia(Clube clube) {
        boolean interesse = false;
        if (reputacaoHistoria < clube.getReputacaoHistorica()) {
            interesse = true;
        }
        return interesse;
    }
}
