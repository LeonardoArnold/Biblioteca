package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Autor {

    private int codigo;
    private String nome;
    private String sobrenome;

    // Construtor
    public Autor(int codigo, String nome, String sobrenome) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    // Método estático para cadastrar autor
    public static void cadastrarAutor(int codigo, String nome, String sobrenome) {
        Connection conexao = null;

        try {
            // Estabelecer a conexão com o banco
            conexao = ConexaoBanco.conectar();

            // Criar o autor e salvar no banco
            Autor autor = new Autor(codigo, nome, sobrenome);
            autor.salvar(conexao);

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar autor: " + e.getMessage());
        } finally {
            if (conexao != null) {
                try {
                    conexao.close(); // Fechar a conexão
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }

    // Método para salvar autor no banco de dados
    public void salvar(Connection conexao) {
        String sql = "INSERT INTO autores (codigo, nome, sobrenome) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, this.codigo);
            stmt.setString(2, this.nome);
            stmt.setString(3, this.sobrenome);

            stmt.executeUpdate();
            System.out.println("Autor cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar autor: " + e.getMessage());
        }
    }
}
