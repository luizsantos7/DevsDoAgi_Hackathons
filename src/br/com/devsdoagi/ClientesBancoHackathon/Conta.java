package br.com.devsdoagi.ClientesBancoHackathon;
import br.com.devsdoagi.ClientesBancoHackathon.Cliente;
import br.com.devsdoagi.ClientesBancoHackathon.Transacao;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Conta {
    private int numero;
    private Cliente titular;
    private double saldo;
    private ArrayList<Transacao> extratos;
    private LocalDateTime ldt;

    public Conta(int numero, Cliente titular){
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0;
        this.extratos = new ArrayList<>();
    }

    public void sacar(double valor, Conta conta) throws IllegalArgumentException{
        if (valor > conta.getSaldo()){
            throw new IllegalArgumentException("Saldo insuficiente!");
        }
        else {
            conta.saldo -= valor;
            conta.extratos.add(new Transacao(getNumero(),valor,"Saque", LocalDateTime.now()));
        }

    }

    public void Depositar(double valor, Conta conta) throws IllegalArgumentException{
        if (valor < 0){
            throw new IllegalArgumentException("Não é possivel depositar este valor, coloque um numero válido!");
        }
        else {
            conta.setSaldo(getSaldo()+valor);
            conta.extratos.add(new Transacao(getNumero(),valor,"Deposito", LocalDateTime.now()));
        }

    }

    public void transferir(double valor, Conta destino){
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido para transferência!");
        }
        if (valor > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente!");
        }
        this.saldo -= valor;
        destino.saldo += valor;

        // registra transações nas duas contas
        extratos.add(new Transacao(LocalDateTime.now(),destino.getNumero(),getNumero(),"Transferencia", valor));
        destino.extratos.add(new Transacao(LocalDateTime.now(),destino.getNumero(),getNumero(),"Transferencia", valor));
    }

    public void mostrarExtrato(){
        if (this.extratos.isEmpty()){
            System.out.println("Lista de transações vazia");
        }
        else {
            for (Transacao extrato : this.extratos){
                System.out.println(extrato);
            }
        }
    }


    // Getters e Setters

    public void setExtratos(ArrayList<Transacao> extratos) {
        this.extratos = extratos;
    }

    public int getNumero(){
        return this.numero;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Transacao> getExtratos() {
        return extratos;
    }

    public Cliente getTitular() {
        return titular;
    }


}
