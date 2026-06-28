
import java.util.ArrayList;
import java.util.Scanner;

public class Conta {

    private Cliente titular;
    private double saldo;
    private String tipoDaConta;
    private String agencia;
    private String banco;

    private String numeroDaConta;

    private ArrayList<Transacao> transacoes;


    public String getAgencia() {
        return agencia;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public Cliente getTitular() {
        return titular;
    }


    public Conta(String banco, String tipoDaConta, String agencia, String numeroDaconta, Cliente titular, double saldoInicial) {
        this.banco = banco;
        this.tipoDaConta = tipoDaConta;
        this.agencia = agencia;
        this.numeroDaConta = numeroDaconta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.transacoes = new ArrayList<>();
    }

    private void adicionarTransacao(String tipo, double valor, Conta remetente,Conta destinatario) {
        Transacao transacao = new Transacao(tipo, valor, remetente, destinatario);
        transacoes.add(transacao);

    }

    public void adicionarTransacao(String tipo, double valor){
        Transacao transacao = new Transacao(tipo, valor);
        transacoes.add(transacao);
    }

    public void mostrarExtrato() {

        System.out.println("==============================");
        System.out.println("        " + banco.toUpperCase());
        System.out.println("==============================");

        System.out.println("Titular: " + titular.getNome());
        System.out.println("CPF: " + titular.getCpf());

        System.out.println("Agência: " + agencia);
        System.out.println("Conta: " + numeroDaConta);
        System.out.println("Tipo: " + tipoDaConta);

        System.out.println("------------------------------");
        System.out.println("MOVIMENTAÇÕES");
        System.out.println("------------------------------");

        if (transacoes.isEmpty()) {

            System.out.println("Nenhuma movimentação encontrada.");

        } else {

            for (Transacao transacao : transacoes) {
                System.out.println(transacao);
            }

        }

        System.out.println("------------------------------");
        System.out.println("Quantidade de transações: " + transacoes.size());

        System.out.printf("Saldo atual: R$ %.2f%n", saldo);

        System.out.println("==============================");
    }

    public boolean transferir(Conta destino, double valor) {
        if (destino == this) {
            return false;
        }
        if (valor <= 0 || this.saldo < valor) {
            return false;
        }
        this.saldo -= valor;
        destino.saldo += valor;

        this.adicionarTransacao(
                "Transferência enviada",
                valor,
                this,
                destino

        );

        destino.adicionarTransacao(
                "Transferência recebida",
                valor,
                this,
                destino
        );
        return true;
    }

    public boolean sacar(double valor) {

        if (valor <= 0) {
            return false;
        }

        if (saldo < valor) {
            return false;
        }

        saldo -= valor;

        adicionarTransacao("Saque", valor);

        return true;
    }

    public boolean depositar(double valor) {

        if (valor <= 0) {
            return false;
        }

        saldo += valor;

        adicionarTransacao("Depósito", valor);

        return true;
    }

    public String resumoDaConta() {
        return "Titular: " + titular.getNome()
                + " | Banco: " + banco
                + " | tipo da conta: " + tipoDaConta
                + " | Agência: " + agencia
                + " | Conta: " + numeroDaConta;
    }

    public String dadosDaConta() {
        return " | " + titular.getNome()
                + "| Banco: " + banco
                + "| agencia:  " + agencia
                + "| conta: " + numeroDaConta + " | ";
    }


    public double getSaldo() {
        return saldo;
    }


    public void quantidadeTransacoes() {

        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação foi realizada.");
        } else {
            System.out.println("Você realizou "
                    + transacoes.size()
                    + " transações.");
        }
    }
}
