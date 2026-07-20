package service;

import exception.ContaNaoEncontradaException;
import model.Conta;
import model.Movimentacao;
import repository.MovimentacaoRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtratoService {

    private final MovimentacaoRepository movimentacaoRepository =
            new MovimentacaoRepository();

    public void mostrarExtrato(Conta conta) {

        if (conta == null) {
            throw new ContaNaoEncontradaException();
        }

        List<Movimentacao> movimentacoes =
                movimentacaoRepository.listarPorConta(conta);

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println();
        System.out.println("==============================================");
        System.out.println("              EXTRATO BANCÁRIO");
        System.out.println("==============================================");

        System.out.println("Titular : " + conta.getCliente().getNome());
        System.out.println("CPF     : " + conta.getCliente().getCpf());
        System.out.println("Agência : " + conta.getAgencia().getCodigo());
        System.out.println("Conta   : " + conta.getNumeroConta());

        System.out.println("----------------------------------------------");

        if (movimentacoes.isEmpty()) {

            System.out.println("Nenhuma movimentação encontrada.");

        } else {

            for (Movimentacao movimentacao : movimentacoes) {

                System.out.println("Tipo      : " + movimentacao.getTipo());
                System.out.printf("Valor     : R$ %.2f%n", movimentacao.getValor());
                System.out.println("Descrição : " + movimentacao.getDescricao());
                System.out.println("Data      : "
                        + movimentacao.getDataHora().format(formato));

                System.out.println("----------------------------------------------");

            }

        }

        System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());

        System.out.println("==============================================");

    }

}