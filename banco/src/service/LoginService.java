package service;

import model.Cliente;
import model.Conta;
import repository.ClienteRepository;
import repository.ContaRepository;

import java.util.Scanner;

public class LoginService {

    private final Scanner scanner = new Scanner(System.in);

    private final ClienteRepository clienteRepository = new ClienteRepository();

    private final ContaRepository contaRepository = new ContaRepository();

    public Conta fazerLogin() {

        while (true) {

            System.out.println("\n========== LOGIN ==========\n");

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            Cliente cliente = clienteRepository.buscarPorCpf(cpf);

            if (cliente == null) {

                System.out.println("\nCliente não encontrado.\n");
                continue;

            }

            Conta conta = contaRepository.buscarPorCliente(cliente);

            if (conta == null) {

                System.out.println("\nConta não encontrada.\n");
                continue;

            }

            if (!conta.getSenha().equals(senha)) {

                System.out.println("\nSenha incorreta.\n");
                continue;

            }

            System.out.println("\n====================================");
            System.out.println("Login realizado com sucesso!");
            System.out.println("Bem-vindo " + conta.getCliente().getNome());
            System.out.println("Conta: " + conta.getNumeroConta());
            System.out.println("====================================\n");

            return conta;

        }

    }

}