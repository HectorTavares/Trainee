package jogador;

import clube.Clube;
import jogador.apetite.ApetiteFinanceiro;

import java.math.BigDecimal;

public class MeioCampo extends Jogador {

    private static final double COEFICIENTE_IDADE = 0.7;

    public MeioCampo(String nome, int idade, Clube clubeAtual, int reputacaoHistoria, ApetiteFinanceiro apetiteFinanceiro, BigDecimal preco) {
        super(nome, idade, clubeAtual, reputacaoHistoria, apetiteFinanceiro, preco);
    }

    @Override
    public double valorDeCompra() {
        if (idade > 30) {
            return super.valorDeCompra() * COEFICIENTE_IDADE;
        }
        return super.valorDeCompra();
    }

    @Override
    public boolean possuiInteresseDeTranferencia(Clube clube) {
        boolean interesse = false;
        if (clube.getReputacaoHistorica() - 2 <= reputacaoHistoria) {
            interesse = true;
        }
        return interesse;
    }
}
