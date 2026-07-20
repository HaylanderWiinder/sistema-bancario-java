package exception;

public class SenhaInvalidaException extends BancoException {

    public SenhaInvalidaException() {
        super("Senha inválida.");
    }

    public SenhaInvalidaException(String mensagem) {
        super(mensagem);
    }

}