package entities;

public class Jutsu {
    private int custoChakra;//0 a 5
    private int dano; // 0 a 10

    public Jutsu(int custoChakra, int dano){
        this.custoChakra = custoChakra;
        this.dano = dano;
    }
    public int getDano() {
        return dano;
    }
    public int getCustoChakra() {
        return custoChakra;
    }
}
