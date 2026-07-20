package exception;

public class ContaNaoEncontradaException extends BancoException {

    public ContaNaoEncontradaException() {
        super("Conta não encontrada.");
    }

    public ContaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}