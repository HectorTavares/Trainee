package entities;

public class Ninja {

    private String nome;
    private int chakra;
    private Jutsu jutsuPrincipal;

    public Ninja(String nome, Jutsu jutsuPrincipal){
        this.nome = nome;
        this.chakra=100;
        this.jutsuPrincipal = jutsuPrincipal;
    }

    public void atacar(Ninja alvo){
        alvo.receberGolpe(jutsuPrincipal.getDano());
        this.chakra=chakra- jutsuPrincipal.getCustoChakra();
    }

    public void receberGolpe(int danoRecebido){
        this.chakra=chakra-danoRecebido;
    }

    public int getChakra(){
        return chakra;
    }

    public String getNome() {
        return nome;
    }

}
