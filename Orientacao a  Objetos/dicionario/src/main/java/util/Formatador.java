package util;

public class Formatador {
    private Formatador(){
    }
    public static String formatar(String palavra){
        return palavra.substring(0,1).toUpperCase() + palavra.substring(1).toLowerCase();
    }
}
