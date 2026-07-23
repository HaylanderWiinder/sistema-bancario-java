package repository;

import database.Conexao;
import model.Agencia;
import model.Cliente;
import model.Conta;
import model.enums.TipoConta;

import java.sql.*;

public class ContaRepository {

    public void salvar(Conta conta) {

        try (Connection conexao = Conexao.conectar()) {

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

            PreparedStatement statement = conexao.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setInt(1, conta.getCliente().getId());
            statement.setInt(2, conta.getAgencia().getId());
            statement.setString(3, conta.getNumeroConta());
            statement.setString(4, conta.getTipoConta().name());
            statement.setString(5, conta.getSenha());
            statement.setDouble(6, conta.getSaldo());

            statement.executeUpdate();

            ResultSet resultado = statement.getGeneratedKeys();

            if (resultado.next()) {

                conta.setId(resultado.getInt(1));

            }

            resultado.close();
            statement.close();

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

        try (Connection conexao = Conexao.conectar()) {

            String sql = """
                    SELECT
                        c.id,
                        c.numero_conta,
                        c.tipo_conta,
                        c.senha,
                        c.saldo,
                        a.id AS agencia_id,
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
                        resultado.getInt("agencia_id"),
                        resultado.getString("codigo"),
                        resultado.getString("nome")
                );

                Conta conta = new Conta(
                        cliente,
                        agencia,
                        TipoConta.valueOf(
                                resultado.getString("tipo_conta")
                        ),
                        resultado.getString("senha")
                );

                conta.setId(resultado.getInt("id"));
                conta.setNumeroConta(resultado.getString("numero_conta"));
                conta.setSaldo(resultado.getDouble("saldo"));

                resultado.close();
                statement.close();

                return conta;

            }

            resultado.close();
            statement.close();

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public Conta buscarPorCpf(String cpf) {

        ClienteRepository clienteRepository = new ClienteRepository();

        Cliente cliente = clienteRepository.buscarPorCpf(cpf);

        if (cliente == null) {

            return null;

        }

        return buscarPorCliente(cliente);

    }

    public Conta buscarPorNumeroConta(String numeroConta) {

        try (Connection conexao = Conexao.conectar()) {

            String sql = """
                    SELECT
                        c.id,
                        c.numero_conta,
                        c.tipo_conta,
                        c.senha,
                        c.saldo,
                        cli.id AS cliente_id,
                        cli.nome,
                        cli.cpf,
                        cli.email,
                        cli.telefone,
                        a.id AS agencia_id,
                        a.codigo,
                        a.nome AS agencia_nome
                    FROM conta c
                    INNER JOIN cliente cli
                        ON c.cliente_id = cli.id
                    INNER JOIN agencia a
                        ON c.agencia_id = a.id
                    WHERE c.numero_conta = ?
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setString(1, numeroConta);

            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {

                Cliente cliente = new Cliente(
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("email"),
                        resultado.getString("telefone")
                );

                cliente.setId(resultado.getInt("cliente_id"));

                Agencia agencia = new Agencia(
                        resultado.getInt("agencia_id"),
                        resultado.getString("codigo"),
                        resultado.getString("agencia_nome")
                );

                Conta conta = new Conta(
                        cliente,
                        agencia,
                        TipoConta.valueOf(
                                resultado.getString("tipo_conta")
                        ),
                        resultado.getString("senha")
                );

                conta.setId(resultado.getInt("id"));
                conta.setNumeroConta(resultado.getString("numero_conta"));
                conta.setSaldo(resultado.getDouble("saldo"));

                resultado.close();
                statement.close();

                return conta;

            }

            resultado.close();
            statement.close();

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public void atualizarSaldo(Conta conta) {

        try (Connection conexao = Conexao.conectar()) {

            String sql = """
                    UPDATE conta
                    SET saldo = ?
                    WHERE id = ?
                    """;

            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setDouble(1, conta.getSaldo());
            statement.setInt(2, conta.getId());

            statement.executeUpdate();

            statement.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}