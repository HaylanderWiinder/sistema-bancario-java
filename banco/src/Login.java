import java.util.Scanner;

public class Login {

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
