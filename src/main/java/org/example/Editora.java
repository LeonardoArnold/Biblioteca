package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Editora {
    private int id;
    private String nome;
    private boolean nacional;

    public Editora(String nome, boolean nacional) {
        this.nome = nome;
        this.nacional = nacional;
    }

    public void salvar(Connection conexao) {
        String sql = "INSERT INTO editora (nome, nacional) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, this.nome);
            stmt.setBoolean(2, this.nacional);
            stmt.executeUpdate();
            System.out.println("Editora cadastrada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar editora: " + e.getMessage());
        }
    }
}
