package br.com.devsdoagi.hackaton1;

import java.util.Scanner;

public class Hackathon {

    //Luiz Henrique dos Santos
    //Andrew Britto Alves Rodrigues
    //Matheus Vencio
    //Vinicius Bavaresco
    //Davi Miguel

    public static void main(String[] args) {
        int idade = 0, idadeAposentadoria = 0, opcao = 0, expectativaVida = 0;
        boolean criado = false;
        double contribuicaoMensal = 0;
        double[] taxasAnuais = {0.005, 0.007, 0.009};
        double[][] matrizCenario = null;
        double[] rendaMensal = null;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n===== MENU SIMULADOR DE PREVIDÊNCIA =====");
            System.out.println("1 - Inserir dados");
            System.out.println("2 - Calcular saldo anual (vetor)");
            System.out.println("3 - Gerar matriz de cenários");
            System.out.println("4 - Calcular renda mensal na aposentadoria");
            System.out.println("5 - Exibir todos os resultados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            System.out.println("\n");

            switch (opcao) {
                case 1:
                    System.out.println("===== MENU DE INSERÇÃO DE DADOS =====");
                    System.out.print("Insira sua idade: ");
                    idade = sc.nextInt();

                    while (true) {
                        System.out.print("\nInsira a idade de aposentadoria: ");
                        idadeAposentadoria = sc.nextInt();
                        if (idadeAposentadoria > idade) {
                            break;
                        }
                        System.out.println("Valor inválido! A idade de aposentadoria deve ser maior que sua idade atual.");
                    }

                    System.out.print("\nInforme sua contribuição mensal: ");
                    contribuicaoMensal = sc.nextDouble();

                    while (true) {
                        System.out.print("\nInforme sua expectativa de vida: ");
                        expectativaVida = sc.nextInt();
                        if (expectativaVida > idadeAposentadoria && expectativaVida <= 110) {
                            break;
                        } else {
                            System.out.println("Valor inválido! Deve ser menor ou igual a 110 e maior que a idade de aposentadoria.");
                        }
                    }
                    criado = false;
                    break;

                case 2:
                    if (idadeAposentadoria > idade) {
                        double[] saldoacum = calculoSaldoAcumulado(idade, idadeAposentadoria, contribuicaoMensal, taxasAnuais[0]);
                        System.out.println("Saldo Anual com a primeira taxa de juros:");
                        for (int i = 0; i < saldoacum.length; i++) {
                            System.out.printf("Ano %d: R$ %.2f \n", (i + 1), saldoacum[i]);
                        }
                    } else {
                        System.out.println("Insira os dados primeiro (Opção 1).");
                    }
                    break;

                case 3:
                    if (idadeAposentadoria > idade) {
                        matrizCenario = matrizCenarios(idade, idadeAposentadoria, contribuicaoMensal, taxasAnuais);
                        criado = true;
                        System.out.println("Matriz de cenários gerada com sucesso!");
                    } else {
                        System.out.println("Insira os dados primeiro (Opção 1).");
                    }
                    break;

                case 4:
                    if (criado) {
                        rendaMensal = calcularRendaMensal(matrizCenario, taxasAnuais, expectativaVida, idadeAposentadoria);
                        System.out.println("Renda Mensal na Aposentadoria (por cenário):");
                        for (int i = 0; i < rendaMensal.length; i++) {
                            System.out.printf("Cenário %d (taxa %.2f%% a.a): R$ %.2f\n", (i + 1), taxasAnuais[i] * 100, rendaMensal[i]);
                        }
                    } else {
                        System.out.println("Para acessar esta opção, gere a matriz de cenários primeiro (Opção 3).");
                    }
                    break;

                case 5:
                    if (matrizCenario != null && rendaMensal != null) {
                        imprimirResultados(idade, idadeAposentadoria, expectativaVida, contribuicaoMensal, taxasAnuais, matrizCenario, rendaMensal);
                    } else {
                        System.out.println("Gere a matriz (Opção 3) e calcule a renda mensal (Opção 4) para exibir os resultados completos.");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }

    public static double[] calculoSaldoAcumulado(int idadeAtual, int idadeDesejada, double contribuicaoMensal, double taxaAnual) {
        double[] saldoAnual = new double[idadeDesejada - idadeAtual];
        double taxaMensal = Math.pow(1 + taxaAnual, 1.0 / 12.0) - 1;

        if (taxaMensal <= 0) {
            System.out.println("Erro na Simulação: Taxa de juros inválida.");
            return new double[0];
        }

        for (int i = 0; i < saldoAnual.length; i++) {
            int meses = (i + 1) * 12;
            saldoAnual[i] = contribuicaoMensal * (Math.pow(1 + taxaMensal, meses) - 1) / taxaMensal;
        }
        return saldoAnual;
    }

    public static double[][] matrizCenarios(int idade, int idadeAposentadoria, double contribuicaoMensal, double[] taxasAnuais) {
        double[][] saldoAculadoCenarios = new double[taxasAnuais.length][idadeAposentadoria - idade];

        for (int i = 0; i < taxasAnuais.length; i++) {
            double taxaMensal = Math.pow(1 + taxasAnuais[i], 1.0 / 12.0) - 1;
            for (int j = 0; j < idadeAposentadoria - idade; j++) {
                int meses = (j + 1) * 12;
                saldoAculadoCenarios[i][j] = contribuicaoMensal * (Math.pow(1 + taxaMensal, meses) - 1) / taxaMensal;
            }
        }
        return saldoAculadoCenarios;
    }

    public static double[] calcularRendaMensal(double[][] matrizCenarios, double[] taxasAnuais, int expectativaDeVida, int idadeAposentadoria) {
        int numCenarios = matrizCenarios.length;
        double[] rendasMensais = new double[numCenarios];

        for (int i = 0; i < numCenarios; i++) {
            double saldoFinal = matrizCenarios[i][matrizCenarios[i].length - 1];
            double taxaAnual = taxasAnuais[i];
            double taxaMensal = Math.pow(1 + taxaAnual, 1.0 / 12.0) - 1;

            int numMeses = (expectativaDeVida - idadeAposentadoria) * 12;

            if (numMeses <= 0) {
                rendasMensais[i] = 0;
                continue;
            }

            double denominador = 1 - Math.pow(1 + taxaMensal, -numMeses);

            if (denominador == 0) {
                rendasMensais[i] = 0;
            } else {
                rendasMensais[i] = saldoFinal * (taxaMensal / denominador);
            }
        }
        return rendasMensais;
    }

    public static void imprimirResultados(int idade, int idadeAposentadoria, int expectativaVida,
                                          double contribuicaoMensal, double[] taxasAnuais,
                                          double[][] matrizCenarios, double[] rendaMensal) {

        System.out.println("\n===== RESULTADOS DA SIMULAÇÃO =====");
        System.out.println("------------------------------------");

        System.out.println("DADOS DO PARTICIPANTE:");
        System.out.printf("Idade atual: %d anos\n", idade);
        System.out.printf("Idade de aposentadoria: %d anos\n", idadeAposentadoria);
        System.out.printf("Expectativa de vida: %d anos\n", expectativaVida);
        System.out.printf("Contribuição mensal: R$ %.2f\n", contribuicaoMensal);
        System.out.println("------------------------------------");

        if (matrizCenarios != null) {
            System.out.println("CENÁRIOS DE SALDO ANUAL ACUMULADO:");
            for (int i = 0; i < matrizCenarios.length; i++) {
                System.out.printf("--- Cenário %d (taxa de %.2f%% ao ano) ---\n", (i + 1), taxasAnuais[i] * 100);
                for (int j = 0; j < matrizCenarios[i].length; j++) {
                    System.out.printf("Ano %d: R$ %.2f\n", (j + 1), matrizCenarios[i][j]);
                }
                System.out.println("------------------------------------");
            }
        }

        if (rendaMensal != null) {
            System.out.println("RENDA MENSAL NA APOSENTADORIA:");
            for (int i = 0; i < rendaMensal.length; i++) {
                System.out.printf("Cenário %d: R$ %.2f\n", (i + 1), rendaMensal[i]);
            }
            System.out.println("------------------------------------");
        } else {
            System.out.println("Nenhum resultado de renda mensal disponível. Gere a matriz e calcule a renda primeiro.");
        }
    }
}