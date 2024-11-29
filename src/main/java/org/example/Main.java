package org.example;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("==== Sistema de Biblioteca ====");
                System.out.println("1 - Cadastrar Autor");
                System.out.println("2 - Cadastrar Editora");
                System.out.println("3 - Cadastrar Livro");
                System.out.println("4 - Registrar Empréstimo");
                System.out.println("5 - Registrar Devolução");
                System.out.println("6 - Listar Empréstimos Ativos");
                System.out.println("7 - Listar Devoluções");
                System.out.println("8 - Cancelar Empréstimo");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.println("==== Cadastro de Autor ====");
                        System.out.print("Digite o nome do autor: ");
                        String nomeAutor = scanner.nextLine();

                        System.out.print("Digite o sobrenome do autor: ");
                        String sobrenome = scanner.nextLine();

                        CadastroAutor.cadastrarAutor(nomeAutor, sobrenome);
                        break;

                    case 2:
                        System.out.println("==== Cadastro de Editora ====");
                        System.out.print("Digite o nome da editora: ");
                        String nomeEditora = scanner.nextLine();

                        System.out.print("A editora é nacional? (true/false): ");
                        boolean nacional = Boolean.parseBoolean(scanner.nextLine());

                        Editora editora = new Editora(nomeEditora, nacional);
                        editora.salvar(ConexaoBanco.conectar());
                        break;

                    case 3:
                        System.out.println("==== Cadastro de Livro ====");
                        System.out.print("Digite o título do livro: ");
                        String titulo = scanner.nextLine();

                        System.out.print("Digite o ano do livro: ");
                        int ano = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite a edição do livro: ");
                        String edicao = scanner.nextLine();

                        System.out.print("Digite o número da estante: ");
                        int estante = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o número total de exemplares: ");
                        int exemplaresTotais = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o número de exemplares disponíveis: ");
                        int exemplaresDisponiveis = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o código do autor (autor_id): ");
                        int autorId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o ID da editora (editora_id): ");
                        int editoraId = Integer.parseInt(scanner.nextLine());

                        Livro livro = new Livro(titulo, ano, edicao, estante, exemplaresTotais, exemplaresDisponiveis, autorId, editoraId);
                        livro.salvar(ConexaoBanco.conectar());
                        break;

                    case 4:
                        System.out.println("==== Registro de Empréstimo ====");
                        System.out.print("Digite o nome da pessoa: ");
                        String pessoaNome = scanner.nextLine();

                        System.out.print("Digite o telefone da pessoa: ");
                        String pessoaTelefone = scanner.nextLine();

                        System.out.print("Digite o CPF da pessoa: ");
                        String pessoaCpf = scanner.nextLine();

                        System.out.print("Digite o ID do material (livro) a ser emprestado: ");
                        int materialId = Integer.parseInt(scanner.nextLine());

                        Date dataEmprestimo = new Date(System.currentTimeMillis());

                        Emprestimo emprestimo = new Emprestimo(pessoaNome, pessoaTelefone, pessoaCpf, dataEmprestimo, materialId);
                        emprestimo.salvar(ConexaoBanco.conectar());

                        Livro livroEmprestado = new Livro();
                        livroEmprestado.atualizarExemplaresDisponiveis(ConexaoBanco.conectar(), materialId);
                        break;

                    case 5:
                        System.out.println("==== Registro de Devolução ====");
                        System.out.print("Digite o CPF da pessoa: ");
                        String cpfDevolucao = scanner.nextLine();

                        System.out.print("Digite o ID do material (livro) a ser devolvido: ");
                        int idMaterialDevolucao = Integer.parseInt(scanner.nextLine());

                        Emprestimo devolucao = new Emprestimo();
                        devolucao.registrarDevolucao(ConexaoBanco.conectar(), idMaterialDevolucao, cpfDevolucao);

                        Livro livroDevolvido = new Livro();
                        livroDevolvido.incrementarExemplaresDisponiveis(ConexaoBanco.conectar(), idMaterialDevolucao);
                        break;

                    case 6:
                        System.out.println("==== Listar Empréstimos Ativos ====");
                        Emprestimo listarAtivos = new Emprestimo();
                        listarAtivos.listarEmprestimosAtivos(ConexaoBanco.conectar());
                        break;

                    case 7:
                        System.out.println("==== Listar Devoluções ====");
                        Emprestimo listarDevolucoes = new Emprestimo();
                        listarDevolucoes.listarDevolucoes(ConexaoBanco.conectar());
                        break;

                    case 8:
                        System.out.println("==== Cancelar Empréstimo ====");
                        System.out.print("Digite o ID do empréstimo a ser cancelado: ");
                        int emprestimoId = Integer.parseInt(scanner.nextLine());

                        Emprestimo cancelar = new Emprestimo();
                        cancelar.cancelarEmprestimo(ConexaoBanco.conectar(), emprestimoId);
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema...");
                        return;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: entrada inválida. Certifique-se de digitar números onde necessário.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
