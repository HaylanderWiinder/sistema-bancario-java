package exception;

public class SaldoInsuficienteException extends BancoException {

    public SaldoInsuficienteException() {
        super("Saldo insuficiente para realizar esta operação.");
    }

    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }

}