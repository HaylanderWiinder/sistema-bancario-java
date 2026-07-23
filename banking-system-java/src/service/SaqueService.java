package service;

import model.Conta;
import model.enums.TipoMovimentacao;
import repository.ContaRepository;
import util.ValidacaoUtil;

import java.util.Scanner;

public class SaqueService {

    private final Scanner scanner = new Scanner(System.in);

    private final ContaRepository contaRepository =
            new ContaRepository();

    private final MovimentacaoService movimentacaoService =
            new MovimentacaoService();

    public void sacar(Conta conta) {

        System.out.println();
        System.out.println("======= SAQUE =======");

        System.out.print("Digite o valor do saque: ");

        double valor = scanner.nextDouble();
        scanner.nextLine();

        ValidacaoUtil.validarValor(valor);

        ValidacaoUtil.validarSaldo(conta, valor);

        conta.sacar(valor);

        contaRepository.atualizarSaldo(conta);

        movimentacaoService.registrar(
                conta,
                TipoMovimentacao.SAQUE,
                valor,
                "Saque realizado."
        );

        System.out.println();
        System.out.println("Saque realizado com sucesso!");
        System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());

    }

}