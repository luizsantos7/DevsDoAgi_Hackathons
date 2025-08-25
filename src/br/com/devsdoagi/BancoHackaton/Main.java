/*
CORREÇÃO

1 - 10 pontos
2 - 10 pontos
3 - 10 pontos
4 - 20 pontos
5 - 10 pontos
6 - 10 pontos
7 - 10 pontos
8 - 20 pontos

TOTAL: 100 pontos MARAVILHOSO
*/
package br.com.devsdoagi.BancoHackaton;

import br.com.devsdoagi.BancoHackaton.Model.Cliente;
import br.com.devsdoagi.BancoHackaton.Model.Conta;
import br.com.devsdoagi.BancoHackaton.Model.ContaCorrente;
import br.com.devsdoagi.BancoHackaton.Model.ContaPoupanca;
import br.com.devsdoagi.BancoHackaton.util.Cores;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);

        String numeroConta="",titular="";
        double saldo=-1;
        int opcao = -1, opcaoIfs=0;
        Boolean clienteExist = false, existe=false;

        ContaCorrente cc = new ContaCorrente(existe,numeroConta,saldo, titular);
        ContaPoupanca cp = new ContaPoupanca(existe,numeroConta,saldo, titular);

        while (true) {
            if(clienteExist==false){
                System.out.println("Para iniciar o projeto é necessário cadastrar-se!\n");
                System.out.println("Digite seu nome: ");
                String nome = leia.nextLine();
                System.out.println("Digite seu CPF: ");
                int cpf = leia.nextInt();
                Cliente cliente = new Cliente(cpf,nome);
                System.out.println("\nCliente Criado!!");
                clienteExist=true;
            }

            if (opcao==0)
                System.exit(0);


            System.out.println(Cores.ANSI_BLACK_BACKGROUND + Cores.TEXT_CYAN_BOLD
                    + "=====================================================");
            System.out.println("            ____________________________             ");
            System.out.println("            ||       Banco AgiDevs     ||             ");
            System.out.println("                                                     ");
            System.out.println("=====================================================");
            System.out.println("                                                     ");
            System.out.println("            1 - Criar Conta                          ");
            System.out.println("            2 - Sacar                                ");
            System.out.println("            3 - Depositar                            ");
            System.out.println("            4 - Aplicar Juros Mensal                 ");
            System.out.println("            0 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("=====================================================");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     " + Cores.TEXT_WHITE);
            opcao= leia.nextInt();

            if (opcao==0)
                System.exit(0);


            switch (opcao){
                case 1:
                    System.out.println("Escolha qual conta deseja criar:\n1-Poupança 2-Corrente");
                    opcaoIfs = leia.nextInt();;

                    if (opcaoIfs==1){
                        System.out.println("Você escolheu a Conta Poupança!\n\nDigite o nome do titular da conta: ");
                        titular= leia.next();
                        cp.setTitular(titular);


                        System.out.println("Insira o Numero da Conta: ");
                        numeroConta= leia.next();;
                        cp.setNumeroConta(numeroConta);


                        System.out.println("Por fim, insira o saldo inicial!");
                        leia.skip("\\R");
                        saldo = leia.nextDouble();
                        cp.setSaldo(saldo);
                        cp.setExist(true);
                        break;
                    }
                    if (opcaoIfs==2){
                        System.out.println("Você escolheu a Conta Corrente!\n\nDigite o nome do titular da conta: ");
                        titular= leia.next();
                        cc.setTitular(titular);


                        System.out.println("Insira o Numero da Conta: ");
                        numeroConta= leia.next();;
                        cc.setNumeroConta(numeroConta);

                        System.out.println("Por fim, insira o saldo inicial!");
                        leia.skip("\\R");
                        saldo = leia.nextDouble();
                        cc.setSaldo(saldo);
                        cc.setExist(true);
                        break;
                    }
                    else
                        System.out.println("Escolha uma opção válida!");

                    break;
                case 2:
                    if (cc.isExist()==true || cp.isExist()==true){
                        System.out.println("Deseja o valor de saque");
                        double saque = leia.nextDouble();

                        if (cc.isExist()==true)
                            cc.sacar(saque);

                        if (cp.isExist()==true)
                            cp.sacar(saque);
                    }
                    if (cc.isExist()==false && cp.isExist() == false){
                        System.out.println("Crie sua conta primeiro!");
                    }
                    break;
                case 3:
                    if (cc.isExist()==true || cp.isExist()==true){
                        System.out.println("Deseja o valor de depósito");
                        double deposito = leia.nextDouble();

                        if (cc.isExist()==true)
                            cc.depositar(deposito);

                        if (cp.isExist()==true)
                            cp.depositar(deposito);
                    }
                    if (cc.isExist()==false && cp.isExist() == false){
                        System.out.println("Crie sua conta primeiro!");
                    }
                    break;
                case 4:
                    if (cc.isExist()==true || cp.isExist()==true){
                        if (cp.isExist()==true)
                            cp.aplicarJuros();

                        if (cc.isExist()==true)
                            cc.aplicarJuros();
                    }
                    if (cc.isExist()==false && cp.isExist() == false){
                        System.out.println("Crie sua conta primeiro!");
                    }
                    break;
                default:
                    System.out.println("Insira uma opção válida!");
                    break;
            }
        }
    }

}
