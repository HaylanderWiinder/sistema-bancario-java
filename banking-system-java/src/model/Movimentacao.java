package model;

import model.enums.TipoMovimentacao;

import java.time.LocalDateTime;

public class Movimentacao {

    private int id;
    private Conta conta;
    private TipoMovimentacao tipo;
    private double valor;
    private String descricao;
    private LocalDateTime dataHora;

    public Movimentacao() {
    }

    public Movimentacao(Conta conta,
                        TipoMovimentacao tipo,
                        double valor,
                        String descricao,
                        LocalDateTime dataHora) {

        this.conta = conta;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = dataHora;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {

        return "Movimentacao{" +
                "tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                '}';

    }

}