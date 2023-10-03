package util;

public class Conversor {
    public static String converter(String[] array){
        String formatado="";

        for(int c=0; c<array.length; c++) {
            formatado += array[c];
        }
        return formatado;
    }
}
