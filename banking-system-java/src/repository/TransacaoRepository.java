package repository;

import database.Conexao;
import model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TransacaoRepository {

    public void salvarDeposito(Conta conta,
                               double valor,
                               String descricao) {

        try {

            Connection conexao = Conexao.conectar();

            String sql = """
                    INSERT INTO transacao
                    (
                        remetente_conta_id,
                        destinatario_conta_id,
                        valor,
                        tipo,
                        descricao,
                        data_hora
                    )
                    VALUES
                    (
                        ?, ?, ?, ?, ?, ?
                    )
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setNull(1, java.sql.Types.INTEGER);
            statement.setInt(2, conta.getId());
            statement.setDouble(3, valor);
            statement.setString(4, "DEPÓSITO");
            statement.setString(5, descricao);
            statement.setTimestamp(
                    6,
                    java.sql.Timestamp.valueOf(LocalDateTime.now())
            );

            statement.executeUpdate();

            statement.close();
            conexao.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public void salvarSaque(Conta conta,
                            double valor,
                            String descricao) {

        try {

            Connection conexao = Conexao.conectar();

            String sql = """
                    INSERT INTO transacao
                    (
                        remetente_conta_id,
                        destinatario_conta_id,
                        valor,
                        tipo,
                        descricao,
                        data_hora
                    )
                    VALUES
                    (
                        ?, ?, ?, ?, ?, ?
                    )
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setInt(1, conta.getId());
            statement.setNull(2, java.sql.Types.INTEGER);
            statement.setDouble(3, valor);
            statement.setString(4, "SAQUE");
            statement.setString(5, descricao);
            statement.setTimestamp(
                    6,
                    java.sql.Timestamp.valueOf(LocalDateTime.now())
            );

            statement.executeUpdate();

            statement.close();
            conexao.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public void salvarTransferencia(Conta remetente,
                                    Conta destinatario,
                                    double valor,
                                    String descricao) {

        try {

            Connection conexao = Conexao.conectar();

            String sql = """
                    INSERT INTO transacao
                    (
                        remetente_conta_id,
                        destinatario_conta_id,
                        valor,
                        tipo,
                        descricao,
                        data_hora
                    )
                    VALUES
                    (
                        ?, ?, ?, ?, ?, ?
                    )
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setInt(1, remetente.getId());
            statement.setInt(2, destinatario.getId());
            statement.setDouble(3, valor);
            statement.setString(4, "TRANSFERÊNCIA");
            statement.setString(5, descricao);
            statement.setTimestamp(
                    6,
                    java.sql.Timestamp.valueOf(LocalDateTime.now())
            );

            statement.executeUpdate();

            statement.close();
            conexao.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}