package exception;

public class BancoException extends RuntimeException {

    public BancoException(String mensagem) {
        super(mensagem);
    }

}