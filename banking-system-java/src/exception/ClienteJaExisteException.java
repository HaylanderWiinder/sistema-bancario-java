package exception;

public class ClienteJaExisteException extends BancoException {

    public ClienteJaExisteException() {
        super("Já existe um cliente cadastrado com este CPF.");
    }

    public ClienteJaExisteException(String mensagem) {
        super(mensagem);
    }

}