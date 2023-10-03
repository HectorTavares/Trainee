package jogador;

import clube.Clube;
import jogador.apetite.ApetiteFinanceiro;

import java.math.BigDecimal;

public class Lateral extends Jogador {
    private int cruzamentosCerteirosNoAno;
    private static final double COEFICIENTE_CRUZAMENTOS = 0.02;
    private static final double COEFICIENTE_IDADE = 0.7;

    public Lateral(String nome, int idade, Clube clubeAtual, ApetiteFinanceiro apetiteFinanceiro, BigDecimal preco, int reputacaoHistoria, int cruzamentosCerteirosNoAno) {
        super(nome, idade, clubeAtual, reputacaoHistoria, apetiteFinanceiro, preco );
        this.cruzamentosCerteirosNoAno = cruzamentosCerteirosNoAno;
    }


    @Override
    public double valorDeCompra() {
        double aumento = (cruzamentosCerteirosNoAno * COEFICIENTE_CRUZAMENTOS)+1;
        if (idade > 28) {
            return (super.valorDeCompra() * aumento) * COEFICIENTE_IDADE;
        }

        return super.valorDeCompra() * aumento;
    }
}
