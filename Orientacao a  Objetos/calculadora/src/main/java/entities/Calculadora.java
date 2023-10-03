package entities;

public class Calculadora {
    public double soma(double valor1, double valor2){
        return valor1 + valor2 ;
    }
    public double subtracao(double valor1, double valor2){
        return valor1 - valor2 ;
    }
    public double multiplicacao(double valor1, double valor2){
        return valor1 * valor2 ;
    }
    public double divisao(double valor1, double valor2){
        return valor1 / valor2 ;
    }
    public double raizQuadrada(double valor1){
        return Math.sqrt(valor1);
    }
    public double exponenciacao(double valor1, double valor2){
        return Math.pow(valor1, valor2);
    }
}
