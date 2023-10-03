package recursos;

public class Silicio implements Recursos {
    public static final int VALOR = 60;
    public static final int PESO = 16;

    @Override
    public int getValor() {
        return VALOR;
    }

    @Override
    public int getPeso() {
        return PESO;
    }
}
