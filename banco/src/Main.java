public static void main(String[] args) {

    int opcao = 0;

    Scanner scanner =  new Scanner(System.in);
    Cliente cliente1 = new Cliente("Haylander", "49075735880");
    Cliente cliente2 = new Cliente("Camila", "40028922");


    Conta conta1 = new Conta(cliente1, 5000);



    System.out.println("Faça o login:");
    System.out.println("Digite seu CPF:");
    String cpfDigitado = scanner.nextLine();
    System.out.println("\n");

    if (conta1.getTitular().getCpf().equals(cpfDigitado)){
        System.out.println("CPF encontrado");
        System.out.println("Bem vindo " + conta1.getTitular().getNome());
        System.out.println("\n");
    } else {
        System.out.println("CPF invalido");
        System.out.println("conta não encontrada!!!");
        return;
    }

    while (opcao !=7){
        Menu.mostrarMenu();
        System.out.println("digite uma opção");
        opcao = scanner.nextInt();

        switch (opcao){
            case 1:
                System.out.println("digite o valor do seu saque");
                double saque = scanner.nextDouble();
                if (conta1.sacar(saque)){
                    System.out.println("Saque realizado com sucesso ");
                    System.out.println("saldo atual é de R$: " + conta1.getSaldo());
                }else{
                    System.out.println("Saldo insuficiente");
                    System.out.println("\n");
                }break;

            case 2:
                System.out.println("digite o valor do seu deposito");
                double deposito = scanner.nextDouble();
                conta1.depositar(deposito);
                System.out.println("Deposito realizado com sucesso");
                System.out.println("Seu saldo atual é de R$: " + conta1.getSaldo());
                break;

            case 3:
                System.out.println("digite o valor da transferencia");
                double transferir = scanner.nextDouble();
                if (conta1.transferencia(transferir)){
                    System.out.println("Tranferencia realizada com sucesso");
                    System.out.println("Seu saldo atual é de R$: " + conta1.getSaldo());
                }else{
                    System.out.println("transferencia negada");
                    System.out.println("Saldo insuficiente");
                }
                break;

            case 4:
                System.out.println(conta1.getSaldo());
                break;

            case 5:
                conta1.mostrarHistorico();
                break;

            case 6:
                conta1.quantidadeTransacoes();
                break;

            case 7:
                System.out.println("Sistema finalizado com sucesso");
                break;
        }
    }
}