package br.com.devsdoagi.minihackaton2;

public abstract class Investimento {
    private double valorInicial;

    public Investimento(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public abstract double calcularRendimento();

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }
}
