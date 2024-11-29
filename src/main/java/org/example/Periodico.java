package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Periodico {
    private int issn;
    private String titulo;
    private int volume;
    private int estante;
    private int exemplaresTotais;
    private int exemplaresDisponiveis;
    private int autorId;
    private int editoraId;

    public Periodico(String titulo, int volume, int estante, int exemplaresTotais, int exemplaresDisponiveis, int autorId, int editoraId) {
        this.titulo = titulo;
        this.volume = volume;
        this.estante = estante;
        this.exemplaresTotais = exemplaresTotais;
        this.exemplaresDisponiveis = exemplaresDisponiveis;
        this.autorId = autorId;
        this.editoraId = editoraId;
    }

    public void salvar(Connection conexao) {
        String sql = "INSERT INTO periodicos (titulo, volume, estante, exemplares_totais, exemplares_disponiveis, autor_id, editora_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, this.titulo);
            stmt.setInt(2, this.volume);
            stmt.setInt(3, this.estante);
            stmt.setInt(4, this.exemplaresTotais);
            stmt.setInt(5, this.exemplaresDisponiveis);
            stmt.setInt(6, this.autorId);
            stmt.setInt(7, this.editoraId);

            stmt.executeUpdate();
            System.out.println("Periódico cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar periódico: " + e.getMessage());
        }
    }
}
