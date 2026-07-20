package model;

public class Agencia {

    private int id;
    private String codigo;
    private String nome;
    
    public Agencia(int id, String codigo, String nome){
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
