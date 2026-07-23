package model;

import model.enums.TipoConta;

public class Conta {

    private int id;
    private Cliente cliente;
    private Agencia agencia;
    private String numeroConta;
    private String senha;
    private TipoConta tipoConta;
    private double saldo;

    public Conta(Cliente cliente,
                 Agencia agencia,
                 TipoConta tipoConta,
                 String senha) {

        this.cliente = cliente;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
        this.senha = senha;
        this.saldo = 0.0;
    }


    // GETTERS E SETTERS
    // =========================


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // ====================
    // OPERAÇÕES
    // ==========

    public void sacar(double valor) {

        saldo -= valor;

    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void transferir(Conta destino, double valor) {

        this.saldo -= valor;
        destino.saldo += valor;

    }

    // ===========
    // INFORMAÇÕES
    // ===================

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

    public String dadosTransferencia() {

        return "Titular: " + cliente.getNome()
                + "\nCPF: " + cliente.getCpf()
                + "\nAgência: " + agencia.getCodigo()
                + " - " + agencia.getNome()
                + "\nConta: " + numeroConta
                + "\nTipo: " + tipoConta;

    }

    public String dadosFormatados() {

        return "Titular: " + cliente.getNome()
                + "\nCPF: " + cliente.getCpf()
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