package service;

import exception.ContaNaoEncontradaException;
import exception.TransferenciaInvalidaException;
import model.Cliente;
import model.Conta;
import model.enums.TipoMovimentacao;
import repository.ClienteRepository;
import repository.ContaRepository;
import util.ValidacaoUtil;

import java.util.Scanner;

public class TransferenciaService {

    private final Scanner scanner = new Scanner(System.in);

    private final ClienteRepository clienteRepository =
            new ClienteRepository();

    private final ContaRepository contaRepository =
            new ContaRepository();

    private final MovimentacaoService movimentacaoService =
            new MovimentacaoService();

    public void transferir(Conta contaOrigem) {

        System.out.println();
        System.out.println("======= TRANSFERÊNCIA =======");

        System.out.print("CPF do destinatário: ");
        String cpf = scanner.nextLine();

        Cliente clienteDestino = clienteRepository.buscarPorCpf(cpf);

        if (clienteDestino == null) {
            throw new ContaNaoEncontradaException(
                    "Nenhuma conta foi encontrada para o CPF informado."
            );
        }

        Conta contaDestino = contaRepository.buscarPorCliente(clienteDestino);

        if (contaDestino == null) {
            throw new ContaNaoEncontradaException(
                    "O cliente informado não possui conta cadastrada."
            );
        }

        ValidacaoUtil.validarTransferencia(contaOrigem,contaDestino);

        System.out.println();
        System.out.println("===== DESTINATÁRIO =====");
        System.out.println(contaDestino.dadosTransferencia());

        System.out.println();

        System.out.print("Valor da transferência: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        ValidacaoUtil.validarValor(valor);

        ValidacaoUtil.validarSaldo(contaOrigem,valor);

        System.out.println();
        System.out.print("Confirme sua senha: ");
        String senha = scanner.nextLine();

        ValidacaoUtil.validarSenha(contaOrigem,senha);

        contaOrigem.transferir(contaDestino, valor);

        contaRepository.atualizarSaldo(contaOrigem);
        contaRepository.atualizarSaldo(contaDestino);

        String descricaoEnvio =
                "Transferência para "
                        + contaDestino.getCliente().getNome()
                        + " | Agência "
                        + contaDestino.getAgencia().getCodigo()
                        + " | Conta "
                        + contaDestino.getNumeroConta();

        movimentacaoService.registrar(
                contaOrigem,
                TipoMovimentacao.TRANSFERENCIA_ENVIADA,
                valor,
                descricaoEnvio
        );

        String descricaoRecebimento =
                "Transferência recebida de "
                        + contaOrigem.getCliente().getNome()
                        + " | Agência "
                        + contaOrigem.getAgencia().getCodigo()
                        + " | Conta "
                        + contaOrigem.getNumeroConta();

        movimentacaoService.registrar(
                contaDestino,
                TipoMovimentacao.TRANSFERENCIA_RECEBIDA,
                valor,
                descricaoRecebimento
        );

        System.out.println();
        System.out.println("================================");
        System.out.println("Transferência realizada com sucesso!");
        System.out.println("================================");
        System.out.printf("Saldo atual: R$ %.2f%n", contaOrigem.getSaldo());

    }

}