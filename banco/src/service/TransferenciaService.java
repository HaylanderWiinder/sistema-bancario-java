package service;

import model.Conta;
import model.Movimentacao;
import repository.ClienteRepository;
import repository.ContaRepository;
import repository.MovimentacaoRepository;

import java.time.LocalDateTime;
import java.util.Scanner;

public class TransferenciaService {

    private final Scanner scanner = new Scanner(System.in);

    private final ClienteRepository clienteRepository =
            new ClienteRepository();

    private final ContaRepository contaRepository =
            new ContaRepository();

    private final MovimentacaoRepository movimentacaoRepository =
            new MovimentacaoRepository();

    public void transferir(Conta contaOrigem) {

        System.out.println();
        System.out.println("======= TRANSFERÊNCIA =======");

        System.out.print("CPF do destinatário: ");

        String cpf = scanner.nextLine();

        var clienteDestino = clienteRepository.buscarPorCpf(cpf);

        if (clienteDestino == null) {

            System.out.println();
            System.out.println("Cliente não encontrado.");

            return;
        }

        Conta contaDestino =
                contaRepository.buscarPorCliente(clienteDestino);

        if (contaDestino == null) {

            System.out.println();
            System.out.println("Conta do destinatário não encontrada.");

            return;
        }

        if (contaDestino.getId() == contaOrigem.getId()) {

            System.out.println();
            System.out.println("Você não pode transferir para sua própria conta.");

            return;
        }

        System.out.println();
        System.out.println("Destinatário:");
        System.out.println(contaDestino.dadosFormatados());

        System.out.println();

        System.out.print("Valor da transferência: ");

        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (!contaOrigem.transferir(contaDestino, valor)) {

            System.out.println();
            System.out.println("Saldo insuficiente ou valor inválido.");

            return;
        }

        contaRepository.atualizarSaldo(contaOrigem);

        contaRepository.atualizarSaldo(contaDestino);

        Movimentacao envio = new Movimentacao();

        envio.setConta(contaOrigem);
        envio.setTipo("TRANSFERÊNCIA ENVIADA");
        envio.setValor(valor);
        envio.setDescricao(
                "Transferência para " +
                        contaDestino.getCliente().getNome()
        );
        envio.setDataHora(LocalDateTime.now());

        movimentacaoRepository.salvar(envio);

        Movimentacao recebimento = new Movimentacao();

        recebimento.setConta(contaDestino);
        recebimento.setTipo("TRANSFERÊNCIA RECEBIDA");
        recebimento.setValor(valor);
        recebimento.setDescricao(
                "Transferência recebida de " +
                        contaOrigem.getCliente().getNome()
        );
        recebimento.setDataHora(LocalDateTime.now());

        movimentacaoRepository.salvar(recebimento);

        System.out.println();
        System.out.println("================================");
        System.out.println("Transferência realizada com sucesso!");
        System.out.println("================================");
        System.out.printf("Saldo atual: R$ %.2f%n", contaOrigem.getSaldo());

    }

}