package service;

import exception.ValorInvalidoException;
import model.Conta;
import model.enums.TipoMovimentacao;
import repository.ContaRepository;
import util.ValidacaoUtil;

import java.util.Scanner;

public class DepositoService {

    private final Scanner scanner = new Scanner(System.in);

    private final ContaRepository contaRepository =
            new ContaRepository();

    private final MovimentacaoService movimentacaoService =
            new MovimentacaoService();

    public void depositar(Conta conta) {

        System.out.println();
        System.out.println("======= DEPÓSITO =======");

        System.out.print("Digite o valor do depósito: ");

        double valor = scanner.nextDouble();
        scanner.nextLine();

        ValidacaoUtil.validarValor(valor);

        conta.depositar(valor);

        contaRepository.atualizarSaldo(conta);

        movimentacaoService.registrar(
                conta,
                TipoMovimentacao.DEPOSITO,
                valor,
                "Depósito realizado na conta."
        );

        System.out.println();
        System.out.println("Depósito realizado com sucesso!");
        System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());

    }

}