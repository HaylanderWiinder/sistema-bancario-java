package service;

import exception.BancoException;
import model.Conta;

import java.util.Scanner;

public class MenuContaService {

    private final Scanner scanner = new Scanner(System.in);

    private final SaqueService saqueService =
            new SaqueService();

    private final DepositoService depositoService =
            new DepositoService();

    private final TransferenciaService transferenciaService =
            new TransferenciaService();

    private final ExtratoService extratoService =
            new ExtratoService();

    public void iniciar(Conta contaLogada) {

        int opcao;

        do {

            System.out.println();
            System.out.println("==================================");
            System.out.println("          MENU DA CONTA");
            System.out.println("==================================");
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Ver Saldo");
            System.out.println("5 - Extrato");
            System.out.println("6 - Logout");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {

                    case 1:

                        saqueService.sacar(contaLogada);

                        break;

                    case 2:

                        depositoService.depositar(contaLogada);

                        break;

                    case 3:

                        transferenciaService.transferir(contaLogada);

                        break;

                    case 4:

                        System.out.println();
                        System.out.printf(
                                "Saldo atual: R$ %.2f%n",
                                contaLogada.getSaldo()
                        );

                        break;

                    case 5:

                        extratoService.mostrarExtrato(contaLogada);

                        break;

                    case 6:

                        System.out.println();
                        System.out.println("Logout realizado com sucesso.");

                        break;

                    default:

                        System.out.println();
                        System.out.println("Opção inválida.");

                }

            } catch (BancoException e) {

                System.out.println();
                System.out.println("==================================");
                System.out.println("ERRO");
                System.out.println("----------------------------------");
                System.out.println(e.getMessage());
                System.out.println("==================================");

            }

        } while (opcao != 6);

    }

}