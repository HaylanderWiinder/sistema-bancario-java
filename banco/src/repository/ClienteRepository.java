package repository;

import database.Conexao;
import model.Cliente;

import java.sql.*;

public class ClienteRepository {

    public void salvar(Cliente cliente) {

        try {

            Connection conexao = Conexao.conectar();

            String sql = """
                    INSERT INTO cliente
                    (nome, cpf, email, telefone)
                    VALUES
                    (?, ?, ?, ?)
                    """;

            PreparedStatement statement = conexao.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefone());

            statement.executeUpdate();

            ResultSet resultado = statement.getGeneratedKeys();

            if (resultado.next()) {

                cliente.setId(resultado.getInt(1));

            }

            resultado.close();
            statement.close();
            conexao.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public Cliente buscarPorCpf(String cpf) {

        try {

            Connection conexao = Conexao.conectar();

            String sql = """
                    SELECT *
                    FROM cliente
                    WHERE cpf = ?
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setString(1, cpf);

            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {

                Cliente cliente = new Cliente(

                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("email"),
                        resultado.getString("telefone")

                );

                cliente.setId(resultado.getInt("id"));

                resultado.close();
                statement.close();
                conexao.close();

                return cliente;

            }

            resultado.close();
            statement.close();
            conexao.close();

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}