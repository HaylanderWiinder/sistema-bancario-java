import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int opcao = 0;

        Scanner scanner = new Scanner(System.in);


        while (opcao != 3) {
            Validacoes.mostrarMenu();
            System.out.println("digite uma opção");
            opcao = scanner.nextInt();

            switch (opcao) {


                case 1:
                    Validacoes.iniciarCadastro(scanner);
                    break;

                case 2:
                    Validacoes.mostrarRequisitos();
                    break;

                case 3:
                    System.out.println("agradecemos seu contato");
                    System.out.println("volte sempre");
                    break;

            }

        }

    }
}