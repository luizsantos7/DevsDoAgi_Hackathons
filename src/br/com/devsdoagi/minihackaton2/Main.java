package br.com.devsdoagi.minihackaton2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criando a lista de investimentos
        Investimento[] investimentos = new Investimento[4];
        investimentos[0] = new RendaFixa(1000.0);
        investimentos[1] = new RendaVariavel(2000.0);
        investimentos[2] = new RendaFixa(500.0);
        investimentos[3] = new RendaVariavel(1500.0);

        // Percorrendo e exibindo rendimentos
        for (Investimento inv : investimentos) {
            double rendimento = inv.calcularRendimento();
            System.out.printf("Investimento inicial: %.2f | Valor após rendimento: %.2f \n",inv.getValorInicial(),rendimento);
        }
    }
}

// 100
