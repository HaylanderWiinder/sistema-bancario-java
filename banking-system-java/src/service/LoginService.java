package service;

import exception.ClienteNaoEncontradoException;
import exception.ContaNaoEncontradaException;
import exception.SenhaInvalidaException;
import model.Cliente;
import model.Conta;
import repository.ClienteRepository;
import repository.ContaRepository;
import util.ValidacaoUtil;

import java.util.Scanner;

public class LoginService {

    private final Scanner scanner = new Scanner(System.in);

    private final ClienteRepository clienteRepository =
            new ClienteRepository();

    private final ContaRepository contaRepository =
            new ContaRepository();

    public Conta fazerLogin() {

        while (true) {

            try {

                System.out.println();
                System.out.println("========== LOGIN ==========");
                System.out.println();

                System.out.print("CPF: ");
                String cpf = scanner.nextLine();

                System.out.print("Senha: ");
                String senha = scanner.nextLine();

                Cliente cliente = clienteRepository.buscarPorCpf(cpf);

               ValidacaoUtil.validarCliente(cliente);

                Conta conta = contaRepository.buscarPorCliente(cliente);

               ValidacaoUtil.validarConta(conta);

                ValidacaoUtil.validarSenha(conta,senha);

                System.out.println();
                System.out.println("====================================");
                System.out.println("Login realizado com sucesso!");
                System.out.println("Bem-vindo " + conta.getCliente().getNome());
                System.out.println("Conta: " + conta.getNumeroConta());
                System.out.println("====================================");
                System.out.println();

                return conta;

            } catch (ClienteNaoEncontradoException
                     | ContaNaoEncontradaException
                     | SenhaInvalidaException e) {

                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();

            }

        }

    }

}