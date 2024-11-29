package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroAutor {
    public static void cadastrarAutor(String nome, String sobrenome) {
        Connection conexao = ConexaoBanco.conectar();

        if (conexao != null) {
            String sql = "INSERT INTO autores (nome, sobrenome) VALUES (?, ?)";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, sobrenome);
                stmt.executeUpdate();
                System.out.println("Autor cadastrado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao cadastrar autor: " + e.getMessage());
            } finally {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Não foi possível conectar ao banco para cadastrar o autor.");
        }
    }
}
