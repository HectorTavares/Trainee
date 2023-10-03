package recursos;

public class Agua implements Recursos {
    public static final int VALOR = 180;
    public static final int PESO = 10;

    @Override
    public int getValor() {
        return VALOR;
    }

    @Override
    public int getPeso() {
        return PESO;
    }
}
