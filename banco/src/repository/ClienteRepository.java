package repository;

import database.Conexao;
import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Conexao;

public class ClienteRepository {

    public void salvar(Cliente cliente){
        try {
            Connection conexao = Conexao.conectar();

            String sql = "INSERT INTO cliente(nome, cpf,email,telefone) VALUES(?,?,?,?)";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1,cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefone());

            ps.executeUpdate();

            ps.close();
            conexao.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
