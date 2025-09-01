package br.com.devsdoagi.carteiraInvestimento;

import java.util.HashMap;
import java.util.Map;

public class SistemaInvestimentos {
    // Atributos
    private Map<String, ClienteCI> clientes;  // chave = CPF

    // Construtor
    public SistemaInvestimentos() {
        clientes = new HashMap<>();
    }

    // Métodos sugeridos
    public void cadastrarCliente(ClienteCI cliente) {
        try {
            if (!clientes.containsKey(cliente.getCpf())) {
                clientes.put(cliente.getCpf(), cliente);
                System.out.println("Cliente criado!");
            } else {
                throw new IllegalArgumentException("CPF já existe!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public ClienteCI buscarCliente(String cpf) {
        for (var clientela : clientes.entrySet()) {
            if (clientela.getKey().equals(cpf)) {
                return clientela.getValue();
            }
        }
        return null;
    }

    public void adicionarInvestimento(String cpf, InvestimentoCI inv) {
        try {
            if (inv == null) {
                throw new IllegalArgumentException("Investimento não pode ser nulo!");
            }

            double valor = inv.getValor(); // pode lançar se for mal construído
            if (valor <= 0) {
                throw new IllegalArgumentException("Valor de investimento inválido. Deve ser positivo!");
            }

            ClienteCI cliente = buscarCliente(cpf);
            if (cliente == null) {
                throw new IllegalArgumentException("Cliente não existente!");
            }

            cliente.adicionarInvestimento(inv);

        } catch (NumberFormatException e) {
            System.out.println("Erro: Valor do investimento informado não é um número válido!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Erro: O investimento ou cliente está nulo!");
        }
    }

    public void listarInvestimentos(String cpf) {
        ClienteCI cliente = buscarCliente(cpf);
        try {
            if (cliente != null) {
                for (int i = 0; i < cliente.getInvestimentos().size(); i++) {
                    System.out.printf("\nInvestimento %d | Tipo do Investimento: %s | Valor investido: %.2f",
                            i + 1,
                            cliente.getInvestimentos().get(i).getTipo(),
                            cliente.getInvestimentos().get(i).getValor());
                }
                System.out.println("\n---------------------------");
            } else {
                throw new IllegalArgumentException("Cliente Não existe!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void relatorioGeral(String cpf) {
        ClienteCI cliente = buscarCliente(cpf);
        if (buscarCliente(cpf) != null) {
            System.out.printf("\nNome: %s\nCPF: %s\nTotal Investido: %.2f\n-------Investimentos-------"
                    , cliente.getNome(), cliente.getCpf(), cliente.getTotalInvestido());
            listarInvestimentos(cpf);
        }
    }
}
