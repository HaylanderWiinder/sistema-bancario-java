
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private String tipo;
    private double valor;
    private LocalDateTime dataHora;

    public Transacao(String tipo, double valor){
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();

    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return tipo +
                " | R$ " + valor +
                " | " + dataHora.format(formato);
    }
}
