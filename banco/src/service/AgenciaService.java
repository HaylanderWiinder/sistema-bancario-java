package service;

import model.Agencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void listarAgencias() {

        System.out.println("\n===== AGÊNCIAS DISPONÍVEIS =====");

        for (Agencia agencia : agencias) {

            System.out.println(
                    agencia.getId() +
                            " - " +
                            agencia.getCodigo() +
                            " - " +
                            agencia.getNome()
            );
        }

    }

    public Agencia buscarPorId(int id) {
        for (Agencia agencia : agencias) {
            if (agencia.getId() == id) {
                return agencia;
            }
        }
        return null;
    }

    public Agencia escolherAgencia(){
        listarAgencias();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma uma agencia");
        int opcao = scanner.nextInt();

        return buscarPorId(opcao);
    }
}
