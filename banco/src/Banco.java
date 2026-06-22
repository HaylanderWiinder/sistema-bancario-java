import java.util.ArrayList;

public class Banco {

    private ArrayList<Conta> contas;

    public Banco(){
        this.contas = new ArrayList<>();
    }

    public void adicionarContas(Conta conta ){
        contas.add(conta);
    }

    public Conta buscarContaPorCpf(String cpf) {

        for (Conta conta : contas) {

            if (conta.getTitular().getCpf().equals(cpf)) {
                return conta;
            }
        }

        return null;

    }
}
