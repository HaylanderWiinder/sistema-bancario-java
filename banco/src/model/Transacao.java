package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private String tipo;
    private double valor;

    private LocalDateTime dataHora;
    private Conta remetente;
    private Conta destinatario;

    public Transacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public Transacao(String tipo, double valor, Conta remetente, Conta destinatario){
        this.tipo = tipo;
        this.valor = valor;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.dataHora = LocalDateTime.now();

    }

    @Override
    public String toString() {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String texto = tipo +
                "\nValor: R$ " + String.format("%.2f", valor) +
                "\nData : " + dataHora.format(formato);

        if (remetente != null) {
            texto += "\n\nRemetente";
            texto += "\n------------------------------";
            texto += "\n" + remetente.dadosFormatados();
        }

        if (destinatario != null) {
            texto += "\n\nDestinatário";
            texto += "\n------------------------------";
            texto += "\n" + destinatario.dadosFormatados();
        }

        return texto;
    }
}
