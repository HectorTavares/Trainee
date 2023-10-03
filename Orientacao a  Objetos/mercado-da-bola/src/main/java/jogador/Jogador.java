package jogador;

import clube.Clube;
import jogador.apetite.ApetiteFinanceiro;

import java.math.BigDecimal;

public abstract class Jogador {

    protected String nome;
    protected int idade;
    protected Clube clubeAtual;
    protected int reputacaoHistoria;
    protected ApetiteFinanceiro apetiteFinanceiro;
    protected BigDecimal preco;


    protected Jogador(String nome, int idade, Clube clubeAtual, int reputacaoHistoria, ApetiteFinanceiro apetiteFinanceiro, BigDecimal preco) {
        this.nome = nome;
        this.idade = idade;
        this.clubeAtual = clubeAtual;
        this.apetiteFinanceiro = apetiteFinanceiro;
        this.preco = preco;
        this.reputacaoHistoria = reputacaoHistoria;

    }

    public String getNomeClubeAtual(){
        if (clubeAtual!=null){
            return clubeAtual.getNome();
        }
        return "Sem Clube";
    }
    public Clube getClubeAtual() {
            return clubeAtual;
    }

    public boolean possuiInteresseDeTranferencia(Clube clube) {
        boolean interesse = false;
        if (clube.getReputacaoHistorica() >= 1) {
            interesse = true;
        }
        return interesse;
    }

    public double valorDeCompra() {
      return BigDecimal.valueOf(preco.doubleValue() * apetiteFinanceiro.getAPETITE()).doubleValue();
    }


    public void tranferenciaDeClube(Clube novoClube) {
        this.clubeAtual = novoClube;
    }

}
