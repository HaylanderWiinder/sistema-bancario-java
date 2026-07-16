package service;

import model.Conta;

import java.util.Scanner;

public class MenuContaService {

    private final Scanner scanner = new Scanner(System.in);

    public void iniciar(Conta contaLogada) {

        int opcao;

        do {

            System.out.println();
            System.out.println("======= MENU DA CONTA =======");
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Ver Saldo");
            System.out.println("5 - Extrato");
            System.out.println("6 - Logout");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            // Aqui entraremos com o switch
            // (vamos implementar juntos)

        } while (opcao != 6);

    }

}