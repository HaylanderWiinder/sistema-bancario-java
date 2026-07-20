package app;

import model.Conta;
import service.ContaService;
import service.LoginService;
import service.MenuContaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ContaService contaService = new ContaService();
        LoginService loginService = new LoginService();
        MenuContaService menuContaService = new MenuContaService();

        int opcao;

        do {

            System.out.println();
            System.out.println("=================================");
            System.out.println("        SISTEMA BANCÁRIO");
            System.out.println("=================================");
            System.out.println("1 - Entrar");
            System.out.println("2 - Abrir Conta");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    Conta contaLogada = loginService.fazerLogin();

                    menuContaService.iniciar(contaLogada);

                    break;

                case 2:

                    contaService.abrirConta();

                    break;

                case 0:

                    System.out.println();
                    System.out.println("Sistema encerrado.");
                    break;

                default:

                    System.out.println();
                    System.out.println("Opção inválida.");

            }

        } while (opcao != 0);

        scanner.close();

    }

}