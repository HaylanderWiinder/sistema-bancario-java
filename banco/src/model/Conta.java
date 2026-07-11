package model;

import model.Cliente;
import model.Transacao;

import java.util.ArrayList;

public class Conta {

    private Cliente cliente;
    private Agencia agencia;
    private String numeroConta;
    private String senha;
    private String tipoConta;
    private double saldo;
    private static int sequenciaConta = 1000;



    private ArrayList<Transacao> transacoes;

    public String getSenha() {
        return senha;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public String getNumeroDaConta() {
        return numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }


    public Conta(Cliente cliente, Agencia agencia, String tipoConta,String senha) {


        this.cliente = cliente;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
        this.senha = senha;

        this.numeroConta = gerarNumeroConta();

        this.saldo = 0.0;

        this.transacoes = new ArrayList<>();
    }

    private String gerarNumeroConta(){
        sequenciaConta++;
        int digito = sequenciaConta % 10;
        return sequenciaConta + "-" + digito;
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
        System.out.println("-------- Sistema bancario --------");
        System.out.println("==============================");

        System.out.println("Titular: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());

        System.out.println(
                "Agência: "
                        + agencia.getCodigo()
                        + " - "
                        + agencia.getNome()
        );
        System.out.println("Conta: "
                + numeroConta);

        System.out.println("Tipo: "
                + tipoConta);

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
        return "Titular: " + cliente.getNome()
                + " | tipo da conta: " + tipoConta
                + " | Agência: " + agencia
                + " | Conta: " + numeroConta;
    }

    public String dadosDaConta() {
        return " | " + cliente.getNome()
                + "| agencia:  " + agencia
                + "| conta: " + numeroConta + " | ";
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

    public String dadosFormatados() {
        return "Titular : " + cliente.getNome() +
                "\nAgência : " + agencia +
                "\nConta   : " + numeroConta;
    }

    @Override
    public String toString() {
        return "Titular: " + cliente.getNome()
                + " | Agência: " + agencia
                + " | Conta: " + numeroConta;
    }
}
