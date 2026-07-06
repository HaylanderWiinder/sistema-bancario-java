package service;

import model.Agencia;

import java.util.ArrayList;
import java.util.List;

public class AgenciaService {

    private List<Agencia> agencias = new ArrayList<>();

    public AgenciaService() {
        carregarAgencias();
    }

    private void carregarAgencias() {

        agencias.add(new Agencia(1, "0001", "Centro"));
        agencias.add(new Agencia(2, "0002", "Norte"));
        agencias.add(new Agencia(3, "0003", "Sul"));
        agencias.add(new Agencia(4, "0004", "Leste"));
        agencias.add(new Agencia(5, "0005", "Oeste"));

    }

}