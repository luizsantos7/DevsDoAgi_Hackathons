package br.com.devsdoagi.carteiraInvestimento;

import java.util.ArrayList;
import java.util.List;

public class ClienteCI {
    private String cpf;
    private String nome;
    private String email;
    private List<InvestimentoCI> investimentos;

    public ClienteCI(String cpf, String email, List<InvestimentoCI> investimentos, String nome) {
        this.cpf = cpf;
        this.email = email;
        this.investimentos = investimentos;
        this.nome = nome;
    }
    public void adicionarInvestimento(InvestimentoCI inv) {
        investimentos.add(inv);
        System.out.println("Investimento Adicionado!");
    }

    public double getTotalInvestido() {
        double soma=0;
        for (int i = 0; i < investimentos.size() ; i++) {
            soma = soma+investimentos.get(i).getValor();
        }
        return soma;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<InvestimentoCI> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<InvestimentoCI> investimentos) {
        this.investimentos = investimentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
