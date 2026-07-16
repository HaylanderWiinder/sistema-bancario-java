import model.Conta;
import service.ContaService;
import service.LoginService;
import service.MenuContaService;

void main() {

    Scanner scanner = new Scanner(System.in);

    ContaService contaService = new ContaService();
    LoginService loginService = new LoginService();
    MenuContaService menuContaService = new MenuContaService();

    int opcao;

    do {

        IO.println();
        IO.println("=================================");
        IO.println("        SISTEMA BANCÁRIO");
        IO.println("=================================");
        IO.println("1 - Entrar");
        IO.println("2 - Abrir Conta");
        IO.println("0 - Sair");
        IO.print("Escolha: ");

        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {

            case 1:

                Conta contaLogada = loginService.fazerLogin();

                menuContaService.iniciar(contaLogada);

                break;

            case 2:

                contaService.abrirConta();

                break;

            case 0:

                IO.println("Sistema encerrado.");

                break;

            default:

                IO.println("Opção inválida.");

        }

    } while (opcao != 0);

}