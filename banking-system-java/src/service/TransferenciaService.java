package service;

import exception.ContaNaoEncontradaException;
import exception.SaldoInsuficienteException;
import exception.SenhaInvalidaException;
import exception.TransferenciaInvalidaException;
import exception.ValorInvalidoException;
import model.Cliente;
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

        if (contaDestino.getId() == contaOrigem.getId()) {
            throw new TransferenciaInvalidaException(
                    "Não é permitido transferir para a própria conta."
            );
        }

        System.out.println();
        System.out.println("===== DESTINATÁRIO =====");
        System.out.println(contaDestino.dadosTransferencia());

        System.out.println();

        System.out.print("Valor da transferência: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (valor <= 0) {
            throw new ValorInvalidoException();
        }

        if (contaOrigem.getSaldo() < valor) {
            throw new SaldoInsuficienteException();
        }

        System.out.println();
        System.out.print("Confirme sua senha: ");
        String senha = scanner.nextLine();

        if (!contaOrigem.getSenha().equals(senha)) {
            throw new SenhaInvalidaException();
        }

        contaOrigem.transferir(contaDestino, valor);

        contaRepository.atualizarSaldo(contaOrigem);
        contaRepository.atualizarSaldo(contaDestino);

        Movimentacao envio = new Movimentacao();

        envio.setConta(contaOrigem);
        envio.setTipo("TRANSFERÊNCIA ENVIADA");
        envio.setValor(valor);
        envio.setDescricao(
                "Transferência para "
                        + contaDestino.getCliente().getNome()
                        + " | Agência "
                        + contaDestino.getAgencia().getCodigo()
                        + " | Conta "
                        + contaDestino.getNumeroConta()
        );
        envio.setDataHora(LocalDateTime.now());

        movimentacaoRepository.salvar(envio);

        Movimentacao recebimento = new Movimentacao();

        recebimento.setConta(contaDestino);
        recebimento.setTipo("TRANSFERÊNCIA RECEBIDA");
        recebimento.setValor(valor);
        recebimento.setDescricao(
                "Transferência recebida de "
                        + contaOrigem.getCliente().getNome()
                        + " | Agência "
                        + contaOrigem.getAgencia().getCodigo()
                        + " | Conta "
                        + contaOrigem.getNumeroConta()
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