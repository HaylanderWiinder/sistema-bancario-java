
import java.util.ArrayList;
import java.util.Scanner;

public class Conta {

    private Cliente titular;
    private double saldo;
    private String tipoDaConta;
    private String agencia;
    private String banco;

    private  String numeroDaConta;

    private ArrayList<Transacao> historico;

    public String getAgencia() {
        return agencia;
    }

    public String getNumeroDaConta(){
        return numeroDaConta;
    }

    public Cliente getTitular(){
        return titular;
    }


    public Conta(String banco,String tipoDaConta,String agencia,String numeroDaconta, Cliente titular, double saldoInicial) {
        this.banco = banco;
        this.tipoDaConta = tipoDaConta;
        this.agencia = agencia;
        this.numeroDaConta = numeroDaconta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historico = new ArrayList<>();
    }

    private void adicionarHistorico(String tipo, double valor) {
       Transacao transacao = new Transacao(tipo,valor);
       historico.add(transacao);

    }

    public void mostrarHistorico() {

        if (historico.isEmpty()) {
            System.out.println("Nenhuma transação encontrada.\n");
            return;
        }

        for (int i = 0; i < historico.size(); i++) {
            System.out.println(historico.get(i));
        }

        System.out.println("--------------------");
        System.out.println("Saldo atual: R$ " + saldo);
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            this.adicionarHistorico("Saque", valor);
            return true;
        }
        return false;
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            this.adicionarHistorico("Depósito", valor);
            return true;
        }
        return false;
    }

    public boolean transferir(Conta destino,double valor) {
        if (destino == this){
            return false;
        }
        if (valor <= 0 || this.saldo < valor){
            return false;
        }
        this.saldo -= valor;
        destino.saldo += valor;

        this.adicionarHistorico("Transferencia realizada com sucesso para | " + destino.resumoDaConta(),  valor);
        destino.adicionarHistorico("Transferencia recebida de | "+ this.resumoDaConta(), valor);

        return true;
    }

    public String resumoDaConta(){
        return "Titular: " + titular.getNome()
                + " | Banco: " + banco
                + " | tipo da conta: " + tipoDaConta
                + " | Agência: " + agencia
                + " | Conta: " + numeroDaConta;
    }

    public String dadosDaConta(){
        return " | " + titular.getNome()
                + "| Banco: " + banco
                + "| agencia:  " + agencia
                + "| conta: " + numeroDaConta + " | ";
    }





    public double getSaldo() {
        return saldo;
    }




    public void quantidadeTransacoes() {

        if (historico.isEmpty()) {
            System.out.println("Nenhuma transação foi realizada.");
        } else {
            System.out.println("Você realizou "
                    + historico.size()
                    + " transações.");
        }
    }

    public static Conta fazerLogin(Banco banco, Scanner scanner){

        System.out.println("Digite o CPF da conta:");
        String cpfDoUsuario = scanner.nextLine();

        Conta contaLogada = banco.buscarContaPorCpf(cpfDoUsuario);

        while (contaLogada == null){

            System.out.println("CPF inválido, tente novamente:");
            cpfDoUsuario = scanner.nextLine();

            contaLogada = banco.buscarContaPorCpf(cpfDoUsuario);
        }
        System.out.println("\n");
        System.out.println("Login efetuado com sucesso \n");
        System.out.println("Bem vindo " +
                contaLogada.dadosDaConta() + "\n");

        return contaLogada;
    }

    }
