package negociacao;

import jogador.Jogador;
import clube.Clube;

import java.math.BigDecimal;

public class Negociacao {
    public boolean negociar(Clube clubeInteressado, Jogador jogadorDeInteresse) {
        if (jogadorDeInteresse.getClubeAtual() != null) {
            if (jogadorDeInteresse.possuiInteresseDeTranferencia(clubeInteressado) && clubeInteressado.getSaldoDisponivelEmCaixa().doubleValue() >= jogadorDeInteresse.valorDeCompra()) {

                clubeInteressado.atualizarSaldo(true, BigDecimal.valueOf(jogadorDeInteresse.valorDeCompra()));
                jogadorDeInteresse.getClubeAtual().atualizarSaldo(false, BigDecimal.valueOf(jogadorDeInteresse.valorDeCompra()));
                jogadorDeInteresse.tranferenciaDeClube(clubeInteressado);
                return true;

            }
        } else {
            if (jogadorDeInteresse.possuiInteresseDeTranferencia(clubeInteressado) && clubeInteressado.getSaldoDisponivelEmCaixa().doubleValue() >= jogadorDeInteresse.valorDeCompra()) {

                clubeInteressado.atualizarSaldo(true, BigDecimal.valueOf(jogadorDeInteresse.valorDeCompra()));
                jogadorDeInteresse.tranferenciaDeClube(clubeInteressado);
                return true;

            }
        }
        return false;
    }
}
