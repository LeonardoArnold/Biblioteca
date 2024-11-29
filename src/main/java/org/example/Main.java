package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("==== Sistema de Biblioteca ====");
            System.out.println("1 - Cadastrar Autor");
            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.println("==== Cadastro de Autor ====");
                    System.out.print("Digite o nome do autor: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o sobrenome do autor: ");
                    String sobrenome = scanner.nextLine();
                    CadastroAutor.cadastrarAutor(nome, sobrenome);
                }
                default -> System.out.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: entrada inválida. Certifique-se de digitar números onde necessário.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        } finally {
            scanner.close(); // Garantir que o Scanner será fechado
        }
    }
}
