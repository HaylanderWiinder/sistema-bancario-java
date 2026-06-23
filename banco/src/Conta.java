
import java.util.ArrayList;

public class Conta {

    private Cliente titular;
    private double saldo;
    private ArrayList<Transacao> historico;

    public Cliente getTitular(){
        return titular;
    }


    public Conta(Cliente titular, double saldoInicial) {
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
        if (valor <= 0 || this.saldo < valor){
            return false;
        }
        this.saldo -= valor;
        destino.saldo += valor;

        this.adicionarHistorico("Transferencia realizada com sucesso" + destino.getTitular().getNome(),  valor);
        destino.adicionarHistorico("Transferencia recebida"+ this.getTitular().getNome(), valor);

        return true;
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


}