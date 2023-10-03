package exception;

public class PalavraNaoEncontradaException extends RuntimeException {
    public PalavraNaoEncontradaException() {
        super("Palavra nao encontrada no dicionario! ");
    }
}
