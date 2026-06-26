public static void main(String[] args) {

    int opcao = 0;


    Scanner scanner = new Scanner(System.in);

    Banco banco = new Banco();

    Cliente cliente1 = new Cliente("Haylander", "49075735880");
    Cliente cliente2 = new Cliente("Camila", "40028922");

    Conta conta1 = new Conta("Caixa economica","Conta corrente","0001", "1089-3",cliente1,7000);
    Conta conta2 = new Conta("Santander","Conta poupança","0002", "4002-8",cliente2,7000);

    banco.adicionarContas(conta1);
    banco.adicionarContas(conta2);


    System.out.println("Faça o login:");
    System.out.println("Digite seu CPF:");
    String cpfDigitado = scanner.nextLine();
    System.out.println("\n");

    Conta contaLogada = banco.buscarContaPorCpf(cpfDigitado);

    if (contaLogada != null) {

        System.out.println("Bem vindo " + contaLogada.dadosDaConta());
        System.out.println("\n");
    } else {
        System.out.println("CPF invalido");
        System.out.println("conta não encontrada!!!");
        return;
    }

    while (opcao != 8) {
        Menu.mostrarMenu();
        System.out.println("digite uma opção");
        opcao = scanner.nextInt();


        switch (opcao) {
            case 1:
                System.out.println("digite o valor do seu saque");
                double saque = scanner.nextDouble();
                if (contaLogada.sacar(saque)) {
                    System.out.println("Saque realizado com sucesso ");
                    System.out.println("saldo atual é de R$: " + contaLogada.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente");
                    System.out.println("\n");
                }
                break;

            case 2:
                System.out.println("digite o valor do seu deposito");
                double deposito = scanner.nextDouble();
                contaLogada.depositar(deposito);
                System.out.println("Deposito realizado com sucesso");
                System.out.println("Seu saldo atual é de R$: " + contaLogada.getSaldo());
                break;

            case 3:
                System.out.println("digite o CPF de destino");
                scanner.nextLine();
                String cpfDestino = scanner.nextLine();

                Conta contaDestino = banco.buscarContaPorCpf(cpfDestino);

                if (contaDestino == null) {
                    System.out.println("Conta não encontrada");
                    break;
                }
                System.out.println("digite o valor da transferencia");
                double valor = scanner.nextDouble();

                if (contaLogada.transferir(contaDestino, valor)) {
                    System.out.println("Transferencia realizada com sucesso");

                } else
                    System.out.println("Falha na transação!!!");


            case 4:
                System.out.println(contaLogada.getSaldo());
                break;

            case 5:
                contaLogada.mostrarExtrato();
                break;

            case 6:
                contaLogada.quantidadeTransacoes();
                break;

            case 7:
                scanner.nextLine();
                contaLogada = Login.fazerLogin(banco, scanner);
                break;

            case 8:
                System.out.println("Sistema finalizado");
        }
    }
}