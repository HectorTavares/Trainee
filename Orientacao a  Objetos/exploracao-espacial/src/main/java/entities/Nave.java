package entities;

import recursos.Recursos;

import java.util.*;

public class Nave {
    private int combustivel;
    private int posicao = 0;
    private static final int GASTO_COMBUSTIVEL = 3;

    public Nave(int combustivel) {
        this.combustivel = combustivel;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public void setQuantidadeDeCombustivel(int combustivel){
        this.combustivel = combustivel;
    }


    public int getQuantidadeDeCombustivel() {
        return combustivel;
    }

    public List<Recursos> explorar(Planeta planetaAlvo) {
        boolean explorado = false;
        for (int i = 0;i< planetaAlvo.getPosicao()*2;i++){
            if(posicao< planetaAlvo.getPosicao() && !explorado && combustivel>=3){
                setQuantidadeDeCombustivel(getQuantidadeDeCombustivel()-GASTO_COMBUSTIVEL);
                setPosicao(getPosicao()+1);
                if (posicao== planetaAlvo.getPosicao()){
                    explorado=true;
                }
            }else if(explorado){
                setQuantidadeDeCombustivel(getQuantidadeDeCombustivel()-GASTO_COMBUSTIVEL);
                setPosicao(getPosicao()-1);
                if(posicao == 0 && getQuantidadeDeCombustivel()>=0){
                    return planetaAlvo.getRecursos();
                }

            }
        }
        return new ArrayList<>();
    }
}
