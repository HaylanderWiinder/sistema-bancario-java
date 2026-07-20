package exception;

public class ValorInvalidoException extends BancoException {

    public ValorInvalidoException() {
        super("Valor inválido. Informe um valor maior que zero.");
    }

    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }

}