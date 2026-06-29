import java.util.ArrayList;

public class Banco {

    private ArrayList<Conta> contas;

    public Banco(){
        this.contas = new ArrayList<>();
    }

    public void adicionarContas(Conta conta ){
        contas.add(conta);
    }

    public Conta autenticarConta(String agencia, String numeroConta, String senha){

        for (Conta conta: contas){
            if (conta.getAgencia().equals(agencia)
            && conta.getNumeroDaConta().equals(numeroConta)
            && conta.getSenha().equals(senha)){

                return conta;
            }

        }
        return null;

    }

    public Conta buscarConta(String agencia, String numeroConta) {

        for (Conta conta : contas) {

            if (conta.getAgencia().equals(agencia)
                    && conta.getNumeroDaConta().equals(numeroConta)) {

                return conta;
            }
        }

        return null;
    }

}
