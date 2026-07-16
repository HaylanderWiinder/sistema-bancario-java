package repository;

import database.Conexao;
import model.Agencia;
import model.Cliente;
import model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaRepository {

    public void salvar(Conta conta) {

        try {

            Connection conexao = Conexao.conectar();

            String numeroConta = gerarNumeroConta(conexao);

            conta.setNumeroConta(numeroConta);

            String sql = """
                    INSERT INTO conta
                    (
                        cliente_id,
                        agencia_id,
                        numero_conta,
                        tipo_conta,
                        senha,
                        saldo
                    )
                    VALUES
                    (
                        ?, ?, ?, ?, ?, ?
                    )
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setInt(1, conta.getCliente().getId());
            statement.setInt(2, conta.getAgencia().getId());
            statement.setString(3, conta.getNumeroConta());
            statement.setString(4, conta.getTipoConta());
            statement.setString(5, conta.getSenha());
            statement.setDouble(6, conta.getSaldo());

            statement.executeUpdate();

            statement.close();
            conexao.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    private String gerarNumeroConta(Connection conexao) {

        String sqlBuscar = """
                SELECT ultimo_numero
                FROM sequencia_conta
                WHERE id = 1
                """;

        String sqlAtualizar = """
                UPDATE sequencia_conta
                SET ultimo_numero = ?
                WHERE id = 1
                """;

        try {

            PreparedStatement buscar = conexao.prepareStatement(sqlBuscar);

            ResultSet resultado = buscar.executeQuery();

            if (resultado.next()) {

                int ultimoNumero = resultado.getInt("ultimo_numero");

                int novoNumero = ultimoNumero + 1;

                PreparedStatement atualizar = conexao.prepareStatement(sqlAtualizar);

                atualizar.setInt(1, novoNumero);

                atualizar.executeUpdate();

                atualizar.close();
                buscar.close();

                int digito = novoNumero % 10;

                return novoNumero + "-" + digito;

            }

            throw new RuntimeException("Sequência da conta não encontrada.");

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public Conta buscarPorCliente(Cliente cliente) {

        try {

            Connection conexao = Conexao.conectar();

            String sql = """
                    SELECT
                        c.numero_conta,
                        c.tipo_conta,
                        c.senha,
                        c.saldo,
                        a.id,
                        a.codigo,
                        a.nome
                    FROM conta c
                    INNER JOIN agencia a
                        ON c.agencia_id = a.id
                    WHERE c.cliente_id = ?
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setInt(1, cliente.getId());

            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {

                Agencia agencia = new Agencia(
                        resultado.getInt("id"),
                        resultado.getString("codigo"),
                        resultado.getString("nome")
                );

                Conta conta = new Conta(
                        cliente,
                        agencia,
                        resultado.getString("tipo_conta"),
                        resultado.getString("senha")
                );

                conta.setNumeroConta(resultado.getString("numero_conta"));
                conta.setSaldo(resultado.getDouble("saldo"));

                resultado.close();
                statement.close();
                conexao.close();

                return conta;

            }

            resultado.close();
            statement.close();
            conexao.close();

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public void atualizarSaldo(Conta conta) {

        try {

            Connection conexao = Conexao.conectar();

            String sql = """
                    UPDATE conta
                    SET saldo = ?
                    WHERE numero_conta = ?
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setDouble(1, conta.getSaldo());
            statement.setString(2, conta.getNumeroConta());

            statement.executeUpdate();

            statement.close();
            conexao.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}