package br.com.devsdoagi.minihackaton1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Funcionario gerente = new Gerente("Roberta", 2300);// instanciei Gerente e Analista
        Funcionario analista = new Analista("Geraldo",1000);//O atributo bonus pode ser alterado dada a vontade do usuario podendo aumentar ou diminuir
        Scanner leia = new Scanner(System.in);
        Funcionario teste = null;
        while(true){
            System.out.println("Digite seu nome:");
            String nome = leia.next();

            System.out.println("Digite seu salário:");
            double salario = leia.nextDouble();

            System.out.println("Digite o seu cargo!\n\n1-Analista 2-Gerente");
            int opcao = leia.nextInt();

            if(opcao==1){
                teste = new Analista(nome,salario);
                teste.relatorio();
                break;
            }
            if(opcao==2){
                teste = new Gerente(nome,salario);
                teste.relatorio();
                break;
            }
            if(opcao !=1 && opcao !=2){
                System.out.println("Opção Inválida, erro no cadastro!\nTente novamente!");
            }
        }

        gerente.relatorio();//relatorio sobre os funcionarios contendo nome, salario e valor de bonus
        analista.relatorio();

        // Nota final: 100/100
    }
}
