package util;

import exception.*;
import model.Cliente;
import model.Conta;

import java.util.Scanner;

public final class ValidacaoUtil {

    public static void validarValor(double valor){
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }

    }
    public  static void validarSaldo(Conta conta, double valor){

        if (conta.getSaldo() < valor) {
            throw new SaldoInsuficienteException();
        }
    }

    public static void validarSenha(Conta conta, String senhaDigitada){

        if (!PasswordEncoder.verificarSenha(
                senhaDigitada,
                conta.getSenha())) {

            throw new SenhaInvalidaException();

        }


    }

    public static void validarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new ClienteNaoEncontradoException();
        }

    }

    public static void validarConta(Conta conta){
        if (conta == null) {
            throw new ContaNaoEncontradaException();
        }
    }

    public static void validarTransferencia(
            Conta contaOrigem,
            Conta contaDestino) {

        if (contaOrigem.getId() == contaDestino.getId()) {
            throw new TransferenciaInvalidaException(
                    "Não é permitido transferir para a própria conta."
            );
        }

    }
}
