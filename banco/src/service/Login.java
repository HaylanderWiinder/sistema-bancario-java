package service;

import model.Banco;
import model.Conta;

import java.util.Scanner;

public class Login {

    public static Conta fazerLogin(Banco banco, Scanner scanner){

        System.out.println("Digite a agencia :");
        String agencia = scanner.nextLine();

        System.out.println("digite a conta :");
        String numeroConta = scanner.nextLine();

        System.out.println("digite sua senha :");
        String senha = scanner.nextLine();

        Conta contaLogada = banco.autenticarConta(agencia, numeroConta, senha);

        while (contaLogada == null){

            System.out.println("Dados invalidos, tente novamente \n");

            System.out.println("Digite a agencia :");
            agencia = scanner.nextLine();

            System.out.println("digite a conta :");
            numeroConta = scanner.nextLine();

            System.out.println("digite sua senha :");
            senha = scanner.nextLine();


            contaLogada = banco.autenticarConta(agencia,numeroConta, senha);
        }
        System.out.println("\n");
        System.out.println("service.Login efetuado com sucesso \n");
        System.out.println("Bem vindo " +
                contaLogada.dadosDaConta() + "\n");

        return contaLogada;
    }
}
