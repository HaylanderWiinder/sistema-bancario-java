package repository;

import database.Conexao;
import model.Conta;
import model.Movimentacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoRepository {

    public void salvar(Movimentacao movimentacao) {

        try {

            Connection conexao = Conexao.conectar();

            String sql = """
                    INSERT INTO movimentacao
                    (
                        conta_id,
                        tipo,
                        valor,
                        descricao,
                        data_hora
                    )
                    VALUES
                    (
                        ?, ?, ?, ?, ?
                    )
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setInt(1, movimentacao.getConta().getId());
            statement.setString(2, movimentacao.getTipo());
            statement.setDouble(3, movimentacao.getValor());
            statement.setString(4, movimentacao.getDescricao());
            statement.setTimestamp(
                    5,
                    Timestamp.valueOf(movimentacao.getDataHora())
            );

            statement.executeUpdate();

            statement.close();
            conexao.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public List<Movimentacao> listarPorConta(Conta conta) {

        List<Movimentacao> movimentacoes = new ArrayList<>();

        try {

            Connection conexao = Conexao.conectar();

            String sql = """
                    SELECT
                        id,
                        tipo,
                        valor,
                        descricao,
                        data_hora
                    FROM movimentacao
                    WHERE conta_id = ?
                    ORDER BY data_hora DESC
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setInt(1, conta.getId());

            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {

                Movimentacao movimentacao = new Movimentacao();

                movimentacao.setId(
                        resultado.getInt("id")
                );

                movimentacao.setConta(conta);

                movimentacao.setTipo(
                        resultado.getString("tipo")
                );

                movimentacao.setValor(
                        resultado.getDouble("valor")
                );

                movimentacao.setDescricao(
                        resultado.getString("descricao")
                );

                movimentacao.setDataHora(
                        resultado.getTimestamp("data_hora")
                                .toLocalDateTime()
                );

                movimentacoes.add(movimentacao);

            }

            resultado.close();
            statement.close();
            conexao.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return movimentacoes;

    }

}