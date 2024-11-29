package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Emprestimo {
    private String pessoaNome;
    private String pessoaTelefone;
    private String pessoaCpf;
    private Date dataEmprestimo;
    private int materialId;
    private boolean devolvido;

    public Emprestimo(String pessoaNome, String pessoaTelefone, String pessoaCpf, Date dataEmprestimo, int materialId) {
        this.pessoaNome = pessoaNome;
        this.pessoaTelefone = pessoaTelefone;
        this.pessoaCpf = pessoaCpf;
        this.dataEmprestimo = dataEmprestimo;
        this.materialId = materialId;
        this.devolvido = false;
    }

    public Emprestimo() {
    }

    public void salvar(Connection conexao) {
        String sql = "INSERT INTO emprestimos (pessoa_nome, pessoa_telefone, pessoa_cpf, data_emprestimo, material_id, devolvido) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, this.pessoaNome);
            stmt.setString(2, this.pessoaTelefone);
            stmt.setString(3, this.pessoaCpf);
            stmt.setDate(4, this.dataEmprestimo);
            stmt.setInt(5, this.materialId);
            stmt.setBoolean(6, this.devolvido);

            stmt.executeUpdate();
            System.out.println("Empréstimo registrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
        }
    }

    public void registrarDevolucao(Connection conexao, int materialId, String pessoaCpf) {
        String sql = "UPDATE emprestimos SET devolvido = TRUE WHERE material_id = ? AND pessoa_cpf = ? AND devolvido = FALSE";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, materialId);
            stmt.setString(2, pessoaCpf);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Devolução registrada com sucesso!");
            } else {
                System.out.println("Nenhum empréstimo ativo encontrado para esse CPF e material.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar devolução: " + e.getMessage());
        }
    }

    public void listarEmprestimosAtivos(Connection conexao) {
        String sql = "SELECT e.id, e.pessoa_nome, e.pessoa_cpf, e.data_emprestimo, l.titulo " +
                "FROM emprestimos e " +
                "JOIN livros l ON e.material_id = l.id " +
                "WHERE e.devolvido = FALSE";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("==== Empréstimos Ativos ====");
            while (rs.next()) {
                System.out.println("ID Empréstimo: " + rs.getInt("id"));
                System.out.println("Nome Pessoa: " + rs.getString("pessoa_nome"));
                System.out.println("CPF Pessoa: " + rs.getString("pessoa_cpf"));
                System.out.println("Data Empréstimo: " + rs.getDate("data_emprestimo"));
                System.out.println("Livro: " + rs.getString("titulo"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos ativos: " + e.getMessage());
        }
    }

    public void listarDevolucoes(Connection conexao) {
        String sql = "SELECT e.id, e.pessoa_nome, e.pessoa_cpf, e.data_emprestimo, l.titulo " +
                "FROM emprestimos e " +
                "JOIN livros l ON e.material_id = l.id " +
                "WHERE e.devolvido = TRUE";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("==== Empréstimos Devolvidos ====");
            while (rs.next()) {
                System.out.println("ID Empréstimo: " + rs.getInt("id"));
                System.out.println("Nome Pessoa: " + rs.getString("pessoa_nome"));
                System.out.println("CPF Pessoa: " + rs.getString("pessoa_cpf"));
                System.out.println("Data Empréstimo: " + rs.getDate("data_emprestimo"));
                System.out.println("Livro: " + rs.getString("titulo"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar devoluções: " + e.getMessage());
        }
    }

    public void cancelarEmprestimo(Connection conexao, int emprestimoId) {
        String sql = "DELETE FROM emprestimos WHERE id = ? AND devolvido = FALSE";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, emprestimoId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Empréstimo cancelado com sucesso!");
            } else {
                System.out.println("Não foi encontrado um empréstimo ativo com esse ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cancelar empréstimo: " + e.getMessage());
        }
    }
}
