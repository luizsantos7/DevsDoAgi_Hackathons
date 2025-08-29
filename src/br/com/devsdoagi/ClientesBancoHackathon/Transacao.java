package br.com.devsdoagi.ClientesBancoHackathon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private int id;
    private String tipo;
    private double valor;
    private LocalDateTime data;
    private int contaOrigem, contaDestino, conta;

    private static final DateTimeFormatter FORMATADOR_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Transacao(LocalDateTime data, int contaDestino, int contaOrigem, String tipo, double valor) {
        this.data = data;
        this.contaDestino = contaDestino;
        this.contaOrigem = contaOrigem;
        this.id = id+1;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Transacao(int conta, double valor, String tipo, LocalDateTime data) {
        this.id = id+1;
        this.conta = conta;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    @Override
    public String toString() {
        String dataFormatada = data.format(FORMATADOR_BR);

        if (getTipo().equalsIgnoreCase("Transferencia"))
            return "ID de Transferência: " + id + " | " + dataFormatada + " | " + tipo + " | Valor: " + valor + " | Origem: " + contaOrigem + " | Destino: " + contaDestino;

        if (getTipo().equalsIgnoreCase("Saque") || getTipo().equalsIgnoreCase("Deposito"))
            return "ID de Transferência: " + id + " | " + dataFormatada + " | " + tipo + " | Valor: " + valor;

        return "Transação Indisponível";
    }

    public int getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(int contaDestino) {
        this.contaDestino = contaDestino;
    }

    public int getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(int contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
