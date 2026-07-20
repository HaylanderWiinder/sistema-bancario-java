package service;

import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;
import model.Conta;
import model.Movimentacao;
import repository.ContaRepository;
import repository.MovimentacaoRepository;

import java.time.LocalDateTime;
import java.util.Scanner;

public class SaqueService {

    private final Scanner scanner = new Scanner(System.in);

    private final ContaRepository contaRepository =
            new ContaRepository();

    private final MovimentacaoRepository movimentacaoRepository =
            new MovimentacaoRepository();

    public void sacar(Conta conta) {

        System.out.println();
        System.out.println("======= SAQUE =======");

        System.out.print("Digite o valor do saque: ");

        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (valor <= 0) {
            throw new ValorInvalidoException();
        }

        if (conta.getSaldo() < valor) {
            throw new SaldoInsuficienteException();
        }

        conta.sacar(valor);

        contaRepository.atualizarSaldo(conta);

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setConta(conta);
        movimentacao.setTipo("SAQUE");
        movimentacao.setValor(valor);
        movimentacao.setDescricao("Saque realizado.");
        movimentacao.setDataHora(LocalDateTime.now());

        movimentacaoRepository.salvar(movimentacao);

        System.out.println();
        System.out.println("Saque realizado com sucesso!");
        System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());

    }

}