package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Aluno {
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String rg;
    private String matricula;

    public Aluno(String nome, String sobrenome, Date dataNascimento, String rg, String matricula) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.rg = rg;
        this.matricula = matricula;
    }

    public void salvar(Connection conexao) {
        String sql = "INSERT INTO alunos (nome, sobrenome, data_nascimento, rg, matricula) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, this.nome);
            stmt.setString(2, this.sobrenome);
            stmt.setDate(3, this.dataNascimento);
            stmt.setString(4, this.rg);
            stmt.setString(5, this.matricula);

            stmt.executeUpdate();
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }
}
