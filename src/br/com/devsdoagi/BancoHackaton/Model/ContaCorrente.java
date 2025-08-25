package br.com.devsdoagi.BancoHackaton.Model;

public class ContaCorrente extends Conta{

    public ContaCorrente(boolean exist, String numeroConta, double saldo, String titular) {
        super(exist, numeroConta, saldo, titular);
    }

    @Override
    public void aplicarJuros() {
        System.out.println("Impossivel realizar aplicação de Juros, por favor crie uma conta poupança para seguir com a operação\n");
    }
}
