package recursos;

public class Ferro implements Recursos {
    public static final int VALOR = 30;
    public static final int PESO = 32;

    @Override
    public int getValor() {
        return VALOR;
    }

    @Override
    public int getPeso() {
        return PESO;
    }
}
