package br.com.devsdoagi.BancoHackaton.Model;

import br.com.devsdoagi.BancoHackaton.repository.OperacoesBancarias;

public abstract class Conta implements OperacoesBancarias {
    private String numeroConta, titular;
    private double saldo;
    private boolean exist;

    public Conta(boolean exist, String numeroConta, double saldo, String titular) {
        this.exist = exist;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.titular = titular;
    }

    public abstract void aplicarJuros();

    @Override
    public void sacar(double valor) {
        if (valor>getSaldo())
            System.out.println("Valor de saque desejado maior que o saldo!\n");
        if (valor<=getSaldo()){
            setSaldo(getSaldo()-valor);
            System.out.printf("Seu saque no valor de R$%.2f foi realizado com sucesso!!\nSaldo Atual: %.2f\n ",valor,getSaldo());
        }
        else
            System.out.println("Operação Inválida, tente novamente!");
    }

    @Override
    public void depositar(double valor) {
        if (valor<=0)
            System.out.println("Por favor, Digite um numero válido para depósito!\n");
        if (valor>0){
            setSaldo(getSaldo()+valor);
            System.out.printf("Seu depósito no valor de R$%.2f foi realizado com sucesso!!\nSaldo Atual: %.2f\n ",valor,getSaldo());
        }
        else
            System.out.println("Operação Inválida, tente novamente!\n");
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
