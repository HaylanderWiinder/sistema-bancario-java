package exception;

public class ClienteNaoEncontradoException extends BancoException {

    public ClienteNaoEncontradoException() {
        super("Cliente não encontrado.");
    }

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}