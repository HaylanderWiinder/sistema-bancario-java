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

}