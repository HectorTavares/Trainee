package recursos;

public class Oxigenio implements Recursos {
    public static final int VALOR = 300;
    public static final int PESO = 2;

    @Override
    public int getValor() {
        return VALOR;
    }

    @Override
    public int getPeso() {
        return PESO;
    }
}
