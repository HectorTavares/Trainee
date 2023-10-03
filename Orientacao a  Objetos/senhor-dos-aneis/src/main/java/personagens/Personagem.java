package personagens;

import classes.Lado;

import java.util.Map;

public abstract class Personagem {

    protected  int forca;
    protected  int agilidade;
    protected  int inteligencia;
    protected  int constituicao;
    protected Lado lado;
    protected boolean jaJogou;


    //construtor
    protected Personagem(){

    }
    //construtor

    //getters


    public boolean isJaJogou() {
        return jaJogou;
    }

    public int getForca() {
        return forca;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public Lado getLado() {
        return lado;
    }

    //getters

    //setters



    public void setJaJogou(boolean jaJogou) {
        this.jaJogou = jaJogou;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public void setLado(classes.Lado lado) {
        this.lado = lado;
    }

    //setters

    public void estaVivo(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual){
        Personagem personagem = mapaAcao.get(posicaoAtual);
        if (personagem.getConstituicao()<=0){
            mapaAcao.remove(posicaoAtual);
        }
    }

    public abstract void acao(Map<Integer, Personagem> mapaAcao, Integer posicaoAtual);

}
