package exception;

public class BancoException extends RuntimeException {

    public BancoException(String mensagem) {
        super(mensagem);
    }

    public BancoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}