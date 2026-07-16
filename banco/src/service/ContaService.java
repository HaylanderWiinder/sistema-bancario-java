package service;

import model.Agencia;
import model.Cliente;
import model.Conta;
import repository.ClienteRepository;
import repository.ContaRepository;

import java.util.Scanner;

public class ContaService {

    private final Scanner scanner = new Scanner(System.in);

    private final ClienteRepository clienteRepository = new ClienteRepository();

    private final ContaRepository contaRepository = new ContaRepository();

    private final AgenciaService agenciaService = new AgenciaService();

    public void abrirConta() {

        System.out.println("\n========== ABERTURA DE CONTA ==========\n");

        // ==========================
        // DADOS DO CLIENTE
        // ==========================

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente cliente = clienteRepository.buscarPorCpf(cpf);

        if (cliente == null) {

            cliente = new Cliente(
                    nome,
                    cpf,
                    email,
                    telefone
            );

            clienteRepository.salvar(cliente);

            System.out.println("\nCliente cadastrado com sucesso!");

        } else {

            System.out.println("\nCliente já cadastrado.");

        }

        // ==========================
        // ESCOLHA DA AGÊNCIA
        // ==========================

        Agencia agencia;

        do {

            agencia = agenciaService.escolherAgencia();

            if (agencia == null) {
                System.out.println("Agência inválida.\n");
            }

        } while (agencia == null);

        // ==============
        // TIPO DA CONTA
        // ==============

        String tipoConta = escolherTipoConta();

        // ==================
        // SENHA
        // ==================

        String senha = criarSenha();

        // ======================
        // CRIA A CONTA
        // =====================

        Conta conta = new Conta(
                cliente,
                agencia,
                tipoConta,
                senha
        );

        contaRepository.salvar(conta);

        System.out.println("\n=======================================");
        System.out.println("Conta criada com sucesso!");
        System.out.println("Número da conta: " + conta.getNumeroConta());
        System.out.println("=======================================\n");

    }

    private String escolherTipoConta() {

        while (true) {

            System.out.println("\n====== TIPO DA CONTA ======");

            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupança");

            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();

            scanner.nextLine();

            switch (opcao) {

                case 1:
                    return "Corrente";

                case 2:
                    return "Poupança";

                default:
                    System.out.println("Opção inválida.");

            }

        }

    }

    private String criarSenha() {

        while (true) {

            System.out.print("Crie uma senha de 6 números: ");

            String senha = scanner.nextLine();

            if (!senha.matches("\\d{6}")) {

                System.out.println("A senha deve possuir exatamente 6 números.");

                continue;
            }

            return senha;

        }

    }

}