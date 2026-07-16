package model;

import java.util.ArrayList;

public class Conta {

    private Cliente cliente;
    private Agencia agencia;
    private String numeroConta;
    private String senha;
    private String tipoConta;
    private double saldo;

    private ArrayList<Transacao> transacoes;

    public Conta(Cliente cliente,
                 Agencia agencia,
                 String tipoConta,
                 String senha) {

        this.cliente = cliente;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
        this.senha = senha;

        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
    }


    // GETTERS E SETTERS
    // ==========================

    public Cliente getCliente() {
        return cliente;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    // TRANSAÇÕES
    // ==========================

    private void adicionarTransacao(String tipo,
                                    double valor,
                                    Conta remetente,
                                    Conta destinatario) {

        Transacao transacao =
                new Transacao(tipo, valor, remetente, destinatario);

        transacoes.add(transacao);
    }

    public void adicionarTransacao(String tipo, double valor) {

        Transacao transacao = new Transacao(tipo, valor);

        transacoes.add(transacao);
    }


    // EXTRATO
    // ==========================

    public void mostrarExtrato() {

        System.out.println("==============================");
        System.out.println("      SISTEMA BANCÁRIO");
        System.out.println("==============================");

        System.out.println("Titular: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());

        System.out.println(
                "Agência: "
                        + agencia.getCodigo()
                        + " - "
                        + agencia.getNome()
        );

        System.out.println("Conta: " + numeroConta);

        System.out.println("Tipo: " + tipoConta);

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

        System.out.println("Quantidade de transações: "
                + transacoes.size());

        System.out.printf("Saldo atual: R$ %.2f%n", saldo);

        System.out.println("==============================");
    }


    // OPERAÇÕES
    // ==========================

    public boolean transferir(Conta destino, double valor) {

        if (destino == this) {
            return false;
        }

        if (valor <= 0 || saldo < valor) {
            return false;
        }

        saldo -= valor;
        destino.saldo += valor;

        adicionarTransacao(
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

    // ==========================
    // INFORMAÇÕES
    // ==========================

    public String resumoDaConta() {

        return "Titular: " + cliente.getNome()
                + " | Tipo: " + tipoConta
                + " | Agência: " + agencia.getCodigo()
                + " | Conta: " + numeroConta;
    }

    public String dadosDaConta() {

        return "Titular: " + cliente.getNome()
                + " | Agência: " + agencia.getCodigo()
                + " | Conta: " + numeroConta;
    }

    public void quantidadeTransacoes() {

        if (transacoes.isEmpty()) {

            System.out.println("Nenhuma transação foi realizada.");

        } else {

            System.out.println(
                    "Você realizou "
                            + transacoes.size()
                            + " transações."
            );

        }
    }

    public String dadosFormatados() {

        return "Titular: " + cliente.getNome()
                + "\nAgência: " + agencia.getCodigo()
                + " - " + agencia.getNome()
                + "\nConta: " + numeroConta
                + "\nTipo: " + tipoConta
                + String.format("\nSaldo: R$ %.2f", saldo);
    }

    @Override
    public String toString() {

        return "Titular: " + cliente.getNome()
                + " | Agência: " + agencia.getCodigo()
                + " | Conta: " + numeroConta;
    }

}