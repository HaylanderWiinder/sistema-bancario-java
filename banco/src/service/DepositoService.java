package service;

import model.Conta;
import model.Movimentacao;
import repository.ContaRepository;
import repository.MovimentacaoRepository;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DepositoService {

    private final Scanner scanner = new Scanner(System.in);

    private final ContaRepository contaRepository =
            new ContaRepository();

    private final MovimentacaoRepository movimentacaoRepository =
            new MovimentacaoRepository();

    public void depositar(Conta conta) {

        System.out.println();
        System.out.println("======= DEPÓSITO =======");

        System.out.print("Digite o valor do depósito: ");

        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (!conta.depositar(valor)) {

            System.out.println();
            System.out.println("Valor inválido.");

            return;
        }

        contaRepository.atualizarSaldo(conta);

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setConta(conta);
        movimentacao.setTipo("DEPÓSITO");
        movimentacao.setValor(valor);
        movimentacao.setDescricao("Depósito realizado na conta.");
        movimentacao.setDataHora(LocalDateTime.now());

        movimentacaoRepository.salvar(movimentacao);

        System.out.println();
        System.out.println("Depósito realizado com sucesso!");
        System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());

    }

}