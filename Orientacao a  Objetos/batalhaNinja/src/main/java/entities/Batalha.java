package entities;

public class Batalha {
    public Ninja lutar(Ninja primeiroNinja, Ninja segundoNinja){
        if (primeiroNinja.getNome().equals("Itachi")){
            return primeiroNinja;
        }
        if(segundoNinja.getNome().equals("Itachi")){
            return segundoNinja;
        }
        primeiroNinja.atacar(segundoNinja);
        segundoNinja.atacar(primeiroNinja);
        primeiroNinja.atacar(segundoNinja);
        segundoNinja.atacar(primeiroNinja);
        primeiroNinja.atacar(segundoNinja);
        segundoNinja.atacar(primeiroNinja);

        if (primeiroNinja.getChakra()>=segundoNinja.getChakra()){
            return primeiroNinja;
        }else{
            return segundoNinja;
        }
    }
}
