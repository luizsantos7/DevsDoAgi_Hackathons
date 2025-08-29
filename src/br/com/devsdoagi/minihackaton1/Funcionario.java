package br.com.devsdoagi.minihackaton1;

public class Funcionario {
    private String nome;
    private double salario;

    public Funcionario(String nome, double salario) {  //classe construtora
        this.nome = nome;
        this.salario = salario;
    }

    public void relatorio(){
        System.out.println("\nNome: "+this.nome+"\nSalario: R$"+this.salario);
    }//método de relatorio que foi criado para mostrar os dados, nas subclasses ele foi reescrito com override

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
