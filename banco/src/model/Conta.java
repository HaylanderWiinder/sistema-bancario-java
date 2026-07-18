package model;

public class Conta {

    private int id;
    private Cliente cliente;
    private Agencia agencia;
    private String numeroConta;
    private String senha;
    private String tipoConta;
    private double saldo;

    public Conta(Cliente cliente,
                 Agencia agencia,
                 String tipoConta,
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

    public String getTipoConta() {
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

    public boolean sacar(double valor) {

        if (valor <= 0) {
            return false;
        }

        if (saldo < valor) {
            return false;
        }

        saldo -= valor;

        return true;
    }

    public boolean depositar(double valor) {

        if (valor <= 0) {
            return false;
        }

        saldo += valor;

        return true;
    }

    public boolean transferir(Conta destino, double valor) {

        if (destino == null) {
            return false;
        }

        if (destino == this) {
            return false;
        }

        if (valor <= 0) {
            return false;
        }

        if (saldo < valor) {
            return false;
        }

        this.saldo -= valor;
        destino.saldo += valor;

        return true;
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