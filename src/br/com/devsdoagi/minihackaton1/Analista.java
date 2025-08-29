package br.com.devsdoagi.minihackaton1;

public class Analista extends Funcionario{

    public Analista(String nome, double salario) {
        super(nome, salario);
        setSalario(getSalario()+1000);//adiciono o bonus diretamente no construtor, assim ele sera contabilizado assim q instanciar um objeto
    }

    @Override
    public void relatorio() {
        super.relatorio();//este super pega o codigo do override e o mantém, assim eu n sobreescrevo tudo
        System.out.println("Bônus: R$1000");
    }
}
