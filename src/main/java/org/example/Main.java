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
                System.out.println("9 - Cadastrar Periódico");
                System.out.println("10 - Cadastrar Aluno");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.println("==== Cadastro de Autor ====");
                        System.out.print("Digite o nome do autor: ");
                        String nomeAutor = scanner.nextLine();

                        System.out.print("Digite o sobrenome do autor: ");
                        String sobrenomeAutor = scanner.nextLine();

                        CadastroAutor.cadastrarAutor(nomeAutor, sobrenomeAutor);
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
                        String tituloLivro = scanner.nextLine();

                        System.out.print("Digite o ano do livro: ");
                        int anoLivro = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite a edição do livro: ");
                        String edicaoLivro = scanner.nextLine();

                        System.out.print("Digite o número da estante: ");
                        int estanteLivro = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o número total de exemplares: ");
                        int exemplaresTotaisLivro = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o número de exemplares disponíveis: ");
                        int exemplaresDisponiveisLivro = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o código do autor (autor_id): ");
                        int autorIdLivro = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o ID da editora (editora_id): ");
                        int editoraIdLivro = Integer.parseInt(scanner.nextLine());

                        Livro livro = new Livro(tituloLivro, anoLivro, edicaoLivro, estanteLivro, exemplaresTotaisLivro, exemplaresDisponiveisLivro, autorIdLivro, editoraIdLivro);
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
                        int materialIdEmprestimo = Integer.parseInt(scanner.nextLine());

                        Date dataEmprestimo = new Date(System.currentTimeMillis());

                        Emprestimo emprestimo = new Emprestimo(pessoaNome, pessoaTelefone, pessoaCpf, dataEmprestimo, materialIdEmprestimo);
                        emprestimo.salvar(ConexaoBanco.conectar());

                        Livro livroEmprestado = new Livro();
                        livroEmprestado.atualizarExemplaresDisponiveis(ConexaoBanco.conectar(), materialIdEmprestimo);
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

                    case 9:
                        System.out.println("==== Cadastro de Periódico ====");
                        System.out.print("Digite o título do periódico: ");
                        String tituloPeriodico = scanner.nextLine();

                        System.out.print("Digite o volume do periódico: ");
                        int volumePeriodico = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o número da estante: ");
                        int estantePeriodico = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o número total de exemplares: ");
                        int exemplaresTotaisPeriodico = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o número de exemplares disponíveis: ");
                        int exemplaresDisponiveisPeriodico = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o código do autor (autor_id): ");
                        int autorIdPeriodico = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o ID da editora (editora_id): ");
                        int editoraIdPeriodico = Integer.parseInt(scanner.nextLine());

                        Periodico periodico = new Periodico(tituloPeriodico, volumePeriodico, estantePeriodico, exemplaresTotaisPeriodico, exemplaresDisponiveisPeriodico, autorIdPeriodico, editoraIdPeriodico);
                        periodico.salvar(ConexaoBanco.conectar());
                        break;

                    case 10:
                        System.out.println("==== Cadastro de Aluno ====");
                        System.out.print("Digite o nome do aluno: ");
                        String nomeAluno = scanner.nextLine();

                        System.out.print("Digite o sobrenome do aluno: ");
                        String sobrenomeAluno = scanner.nextLine();

                        System.out.print("Digite a data de nascimento do aluno (YYYY-MM-DD): ");
                        Date dataNascimentoAluno = Date.valueOf(scanner.nextLine());

                        System.out.print("Digite o número do RG: ");
                        String rgAluno = scanner.nextLine();

                        System.out.print("Digite o número de matrícula: ");
                        String matriculaAluno = scanner.nextLine();

                        Aluno aluno = new Aluno(nomeAluno, sobrenomeAluno, dataNascimentoAluno, rgAluno, matriculaAluno);
                        aluno.salvar(ConexaoBanco.conectar());
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
