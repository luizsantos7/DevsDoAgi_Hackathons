package br.com.devsdoagi.ClientesBancoHackathon;


//LUCAS DAROS
//LUIZ HENRIQUE
//LUAN SILVA

import java.util.*;

public class Main {

    // Criação de coleções para retenção de dados
    private static Set<Cliente> clientes = new HashSet<>();
    private static Map<Integer, Conta> contas = new HashMap<>();

    // Listagem de cpfs dos clientes já cadastrados
    private static List<String> cpfs = new LinkedList<>();

    // Numero inicial padrão para criação de contas
    private static int numeroConta = 1001;


    private static void cadastrarCliente(Scanner sc, List<String> cpfs) {
        System.out.println("Digite o seu nome:");
        String nome = sc.next();
        System.out.println("Digite o seu cpf:");
        String cpf = sc.next();

        if (cpfs.contains(cpf)) {
            System.out.println("Cpf ja cadastrado!");
        }else{
            Cliente cl = new Cliente(nome, cpf);
            cpfs.add(cpf);
            clientes.add(cl);
    }}

    private static void cadastrarConta(Scanner sc) {

        System.out.println("Digite o seu cpf:");
        String cpf = sc.next();

        // Percorre
        for(Cliente cl : clientes){
            if (Objects.equals(cpf, cl.getCpf())){
                Conta co = new Conta(numeroConta,cl);
                contas.put(numeroConta, co);
                System.out.println("Conta criada! \nNumero da conta: "+numeroConta);
                numeroConta++;
            }

        }
    }

    private static void deposito(Scanner sc) {
        System.out.print("Digite o número da conta: ");
        int numero = Integer.parseInt(sc.next());

        Conta conta = contas.get(numero);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }

        System.out.print("Digite o valor do depósito: ");
        double valor = Double.parseDouble(sc.next());

        try {
            conta.Depositar(valor, conta);
            System.out.println("Depósito realizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void sacar(Scanner sc) {
        System.out.print("Digite o número da conta: ");
        int numero = Integer.parseInt(sc.next());

        Conta conta = contas.get(numero);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }

        System.out.print("Digite o valor do saque: ");
        double valor = Double.parseDouble(sc.next());

        try {
            conta.sacar(valor, conta);
            System.out.println("Saque realizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void transferencia(Scanner sc) {
        System.out.print("Digite o número da conta de origem: ");
        int origem = Integer.parseInt(sc.next());
        Conta contaOrigem = contas.get(origem);

        System.out.print("Digite o número da conta de destino: ");
        int destino = Integer.parseInt(sc.next());
        Conta contaDestino = contas.get(destino);

        if (contaOrigem == null || contaDestino == null) {
            System.out.println("Conta de origem ou destino não encontrada!");
            return;
        }

        System.out.print("Digite o valor da transferência: ");
        double valor = Double.parseDouble(sc.next());

        try {
            contaOrigem.transferir(valor, contaDestino);
            System.out.println("Transferência realizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }




    public static void consultarSaldo (Scanner sc){
        boolean checkConta = false;
        System.out.println("digite o numero da conta");
        int num = sc.nextInt();

        for (Map.Entry<Integer, Conta> entry : contas.entrySet()){
            if (entry.getKey() == num){
                System.out.printf("saldo disponivel: R$ %.2f", entry.getValue().getSaldo());
                checkConta = true;
            }
            if(checkConta==true)
                break;
        }
        if (!checkConta){
            System.out.println("essa conta nao foi encontrada");
        }
    }

    private static void extrato(Scanner sc) {
        boolean checkConta = false;
        System.out.println("digite o numero da conta");
        int num = sc.nextInt();


        for (Map.Entry<Integer, Conta> entry : contas.entrySet()){
            if (entry.getKey() == num){
                entry.getValue().mostrarExtrato();
                checkConta = true;
            }
            if(checkConta==true)
                break;
        }


        if (!checkConta){
            System.out.println("essa conta nao foi encontrada");
        }
    }


    private static void listarClientes(){
        if (clientes.isEmpty()){
            System.out.println("nenhum cliente cadastrado");
        }
        for(Cliente cl : clientes){
            System.out.println(cl.toString());
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== MENU BANCO DIGITAL ===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Conta");
            System.out.println("3 - Depósito");
            System.out.println("4 - Saque");
            System.out.println("5 - Transferência");
            System.out.println("6 - Consultar Saldo");
            System.out.println("7 - Extrato");
            System.out.println("8 - Listar Clientes");
            System.out.println("9 - Sair");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarCliente(sc, cpfs);
                    break;
                case 2:
                    cadastrarConta(sc);
                    break;
                case 3:
                    deposito(sc);
                    break;
                case 4:
                    sacar(sc);
                    break;
                case 5:
                    transferencia(sc);
                    break;

                case 6:
                    consultarSaldo(sc);
                    break;
                case 7:
                    extrato(sc);
                    break;
                case 8:
                    listarClientes();
                    break;
                case 9:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }


}
