package recursos;

public class Ouro implements Recursos {
    public static final int VALOR = 120;
    public static final int PESO = 25;

    @Override
    public int getValor() {
        return VALOR;
    }

    @Override
    public int getPeso() {
        return PESO;
    }
}

