package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Livro {
    private String titulo;
    private int ano;
    private String edicao;
    private int estante;
    private int exemplaresTotais;
    private int exemplaresDisponiveis;
    private int autorId;
    private int editoraId;

    public Livro(String titulo, int ano, String edicao, int estante, int exemplaresTotais, int exemplaresDisponiveis, int autorId, int editoraId) {
        this.titulo = titulo;
        this.ano = ano;
        this.edicao = edicao;
        this.estante = estante;
        this.exemplaresTotais = exemplaresTotais;
        this.exemplaresDisponiveis = exemplaresDisponiveis;
        this.autorId = autorId;
        this.editoraId = editoraId;
    }

    public Livro() {
    }

    public void salvar(Connection conexao) {
        String sql = "INSERT INTO livros (titulo, ano, edicao, estante, exemplares_totais, exemplares_disponiveis, autor_id, editora_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, this.titulo);
            stmt.setInt(2, this.ano);
            stmt.setString(3, this.edicao);
            stmt.setInt(4, this.estante);
            stmt.setInt(5, this.exemplaresTotais);
            stmt.setInt(6, this.exemplaresDisponiveis);
            stmt.setInt(7, this.autorId);
            stmt.setInt(8, this.editoraId);

            stmt.executeUpdate();
            System.out.println("Livro cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public void atualizarExemplaresDisponiveis(Connection conexao, int materialId) {
        String sql = "UPDATE livros SET exemplares_disponiveis = exemplares_disponiveis - 1 WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, materialId);
            stmt.executeUpdate();
            System.out.println("Quantidade de exemplares atualizada!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar exemplares: " + e.getMessage());
        }
    }

    public void incrementarExemplaresDisponiveis(Connection conexao, int materialId) {
        String sql = "UPDATE livros SET exemplares_disponiveis = exemplares_disponiveis + 1 WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, materialId);
            stmt.executeUpdate();
            System.out.println("Quantidade de exemplares incrementada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao incrementar exemplares: " + e.getMessage());
        }
    }
}
