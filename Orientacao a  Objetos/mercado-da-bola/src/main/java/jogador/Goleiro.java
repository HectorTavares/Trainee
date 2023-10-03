package jogador;

import clube.Clube;
import jogador.apetite.ApetiteFinanceiro;

import java.math.BigDecimal;

public class Goleiro extends Jogador {
    private int penaltisDefendidosNoAno;
    private static final double COEFICIENTE_PENALTIS = 0.04;

    public Goleiro(String nome, int idade, Clube clubeAtual, int reputacaoHistoria, ApetiteFinanceiro apetiteFinanceiro, BigDecimal preco, int penaltisDefendidosNoAno) {
        super(nome, idade, clubeAtual, reputacaoHistoria, apetiteFinanceiro, preco);
        this.penaltisDefendidosNoAno = penaltisDefendidosNoAno;
    }

    @Override
    public double valorDeCompra() {
        double aumento = ((penaltisDefendidosNoAno * COEFICIENTE_PENALTIS) + 1);
        return super.valorDeCompra() * aumento;
    }
}
