package clube;

import java.math.BigDecimal;

public class Clube {
    private String nome;
    private int reputacaoHistorica;
    private BigDecimal saldoDisponivelEmCaixa;

    public Clube(String nome, int reputacaoHistorica, BigDecimal saldoDisponivelEmCaixa){
        this.nome = nome;
        this.reputacaoHistorica = reputacaoHistorica;
        this.saldoDisponivelEmCaixa = saldoDisponivelEmCaixa;

    }
    public String getNome(){
        return nome;
    }

    public int getReputacaoHistorica() {
        return reputacaoHistorica;
    }

    public BigDecimal getSaldoDisponivelEmCaixa() {
        return saldoDisponivelEmCaixa;
    }

    public void atualizarSaldo(boolean comprador,BigDecimal preco){
        if (comprador){
            this.saldoDisponivelEmCaixa=saldoDisponivelEmCaixa.subtract(preco);
        }
        this.saldoDisponivelEmCaixa =saldoDisponivelEmCaixa.add(preco);
    }

}
