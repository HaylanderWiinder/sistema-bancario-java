package service;

import model.Cliente;

import java.util.Scanner;

public class ContaService {

    public void abrirConta() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente cliente = new Cliente(
                nome,
                cpf,
                email,
                telefone
        );

        System.out.println(cliente.getNome());

    }

    private String escolherTipoConta(){
        System.out.println("----- Escolha o tipo da conta -----");
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - conta corrente");
        System.out.println("2 - conta poupança");

        int opcao = scanner.nextInt();

        switch (opcao ) {
            case 1:
                return "corrente";

            case 2:
                return "poupança";

            default:
                return "opção invalida";
        }


    }

}