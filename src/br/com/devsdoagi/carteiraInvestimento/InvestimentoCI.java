package br.com.devsdoagi.carteiraInvestimento;

public class InvestimentoCI {
    // Atributos
    private String tipo;   // Ex.: Tesouro Selic, CDB, Ações
    private double valor;

    // Construtor
    public InvestimentoCI(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
