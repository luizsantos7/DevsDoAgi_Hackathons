package br.com.devsdoagi.carteiraInvestimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Luiz Henrique
        //Alefe
        //Israel

        SistemaInvestimentos sistema= new SistemaInvestimentos();

        Scanner sc= new Scanner(System.in);
        int opcao;
        List<InvestimentoCI> investimentos = new ArrayList<>();
        String cpf;
        String nome;
        String email;
        double valor;

        do {
            System.out.println("\nMENU");
            System.out.println("1-Cadastrar Cliente");
            System.out.println("2-Adicionar Investimento");
            System.out.println("3-Lista Investimento");
            System.out.println("4-Relatorio Geral");
            System.out.println("0-Sair");
            System.out.println("Digite a Opção desejada: ");
            opcao=sc.nextInt();


            switch (opcao) {
                case 1:
                    System.out.println("Digite seu nome: ");
                    nome= sc.next();
                    System.out.println("Digite seu CPF: ");
                    cpf=sc.next();
                    System.out.println("Digite seu E-mail: ");
                    email=sc.next();
                    ClienteCI cliente= new ClienteCI(cpf, email, investimentos, nome);
                    sistema.cadastrarCliente(cliente);
                    break;
                case 2:
                    System.out.println("Digite o CPF do cliente: ");
                    cpf = sc.next();

                    if (sistema.buscarCliente(cpf) != null) {
                        System.out.println("Digite o tipo de Investimento: ");
                        String tipo = sc.next();

                        System.out.println("Digite o valor investido: ");
                        String entradaValor = sc.next(); // lê como texto

                        try {
                            valor = Float.parseFloat(entradaValor); // tenta converter
                            if (valor <= 0) {
                                System.out.println("Valor de investimento inválido. Deve ser positivo!");
                            } else {
                                InvestimentoCI investimento = new InvestimentoCI(tipo, valor);
                                sistema.adicionarInvestimento(cpf, investimento);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Digite apenas números para o valor do investimento.");
                        }

                    } else {
                        System.out.println("Não existe um cliente vinculado a este CPF!");
                    }
                    break;

                case 3:
                    System.out.println("Digite o CPF do cliente: ");
                    cpf=sc.next();
                    sistema.listarInvestimentos(cpf);
                    break;
                case 4:
                    try{
                        System.out.println("Digite o CPF do cliente: ");
                        cpf=sc.next();

                        ClienteCI clienteEncontrado = sistema.buscarCliente(cpf);
                        if(clienteEncontrado == null){
                            throw new IllegalArgumentException("Não existe um cliente vinculado a este CPF!");
                        }else{
                            sistema.relatorioGeral(cpf);
                            break;
                        }
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        } while (opcao!=0);
        System.out.println("Saindo..\nObrigado por usar o programa!");
    }
}