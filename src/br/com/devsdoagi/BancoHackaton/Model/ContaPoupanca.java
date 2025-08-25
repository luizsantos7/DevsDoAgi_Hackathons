package br.com.devsdoagi.BancoHackaton.Model;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(boolean exist, String numeroConta, double saldo, String titular) {
        super(exist, numeroConta, saldo, titular);
    }

    @Override
    public void aplicarJuros() {
        setSaldo(getSaldo()*1.01);
        System.out.println("Juros aplicado com sucesso!\n");
    }
}
