package repository;
import database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ContaRepository {
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

            PreparedStatement statementBuscar = conexao.prepareStatement(sqlBuscar);

            ResultSet resultado = statementBuscar.executeQuery();

            if (resultado.next()) {

                int ultimoNumero = resultado.getInt("ultimo_numero");

                int novoNumero = ultimoNumero + 1;

                PreparedStatement statementAtualizar = conexao.prepareStatement(sqlAtualizar);

                statementAtualizar.setInt(1, novoNumero);

                statementAtualizar.executeUpdate();

                int digito = novoNumero % 10;

                return novoNumero + "-" + digito;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gerar número da conta.", e);
        }

        throw new RuntimeException("Sequência da conta não encontrada.");
    }
}
