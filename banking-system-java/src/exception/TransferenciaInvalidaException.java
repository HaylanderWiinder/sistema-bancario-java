package exception;

public class TransferenciaInvalidaException extends BancoException {

    public TransferenciaInvalidaException() {
        super("Não foi possível realizar a transferência.");
    }

    public TransferenciaInvalidaException(String mensagem) {
        super(mensagem);
    }

}