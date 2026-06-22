import java.util.Scanner;

public class Validacoes {




    public static void mostrarMenu(){
        System.out.println("""
                1 - cadastrar
                2 - ver requisitos
                3 - finalizar
                """);
    }

    public static boolean verificarIdade(int idade ){
        return idade >=21;


    }

    public static boolean validarCnh(String temCnh){

        return temCnh.equalsIgnoreCase("s");
    }

    public static boolean validarSaldo(double saldo){

        return saldo >= 1500;
    }

    public static void mostrarRequisitos(){
        System.out.println("""
                1 - ter no minimo 21 anos 
                2 - possuir CNH
                3 - Ter um calçao de no minimo R$ 1.500
                """);
    }

    public static void iniciarCadastro(Scanner scanner){
        System.out.println("digite sua idade");
        int idade = scanner.nextInt();


        if (!verificarIdade(idade)){
            System.out.println("idade minima de 21 anos não atingida");
            return;
        }
        System.out.println("idade validade com sucesson\n");


        System.out.println("voce possui CNH ? ");
        String temCnh = scanner.next();

        if (!validarCnh(temCnh)){
            System.out.println("Cadastro negado!!!");
            System.out.println("É necessario possuir CNH");
            return;
        }
            System.out.println("CNH validade com sucesso\n");


        System.out.println("Digite o valor so seu deposito");
        double saldo = scanner.nextDouble();



        if (validarSaldo(saldo)){
            System.out.println("Parabens todos os pré requisitos foram aprovados");
            System.out.println("Voce esta apto para alugar o carro conosco");
        }else {
            System.out.println("Saldo insuficiete");
            System.out.println("Calção minimo de R$ 1.500");
        }

    }


}
