package jogador;

import clube.Clube;
import jogador.apetite.ApetiteFinanceiro;

import java.math.BigDecimal;

public class Zagueiro extends Jogador {
    private static final double COEFICIENTEIDADE = 0.8;

    public Zagueiro(String nome, int idade, Clube clubeAtual, int reputacaoHistoria, ApetiteFinanceiro apetiteFinanceiro, BigDecimal preco) {
        super(nome, idade, clubeAtual, reputacaoHistoria, apetiteFinanceiro, preco );
    }


    @Override
    public double valorDeCompra() {
        if (idade > 30) {
            return super.valorDeCompra() * COEFICIENTEIDADE;
        }
        return super.valorDeCompra();
    }
}
