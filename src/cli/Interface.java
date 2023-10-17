package cli;

import modelo.*;
import negocio.NegocioAluno;
import negocio.NegocioCampeonato;
import negocio.NegocioEquipe;
import negocio.NegocioTecnico;
import negocio.excecoes.*;
import repositorio.RepositorioAluno;
import repositorio.RepositorioCampeonato;
import repositorio.RepositorioEquipe;
import repositorio.RepositorioTecnico;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface {

    private static RepositorioEquipe repositorioEquipe = RepositorioEquipe.getRepositorioEquipe();
    private static RepositorioCampeonato repositorioCampeonato = RepositorioCampeonato.getRepositorioCampeonato();
    private static RepositorioTecnico repositorioTecnico = RepositorioTecnico.getRepositorioTecnico();
    private static RepositorioAluno repositorioAluno = RepositorioAluno.getRepositorioAluno();

    private static NegocioEquipe negocioEquipe = new NegocioEquipe();
    private static NegocioAluno negocioAluno = new NegocioAluno();
    private static NegocioTecnico negocioTecnico = new NegocioTecnico();
    private static NegocioCampeonato negocioCampeonato = new NegocioCampeonato();

    private static ScannerAvancado scanner = new ScannerAvancado();

    public static void main(String[] args) throws NomeMuitoPequenoException, NomeDuplicadoException, NomeNullException, NomeVazioException, IdInvalidoException, IdNegativoException, CpfCaracterException, EquipeInvalidaException, CpfSomentoNumerosException, CpfIgualException, AlunoDeMaiorException, AlunoDeMenorException {

        while (true) {
            String s = """
                    Digite uma opção:
                    [0] Sair
                    [1] Equipe
                    [2] Aluno
                    [3] Técnico
                    [4] Campeonato             
                    """;
            int operando  = Integer.parseInt(scanner.pedirInt(s));

            if (operando  == 0){
                break;
            }
            s = """
					Digite uma operação:
					[0] Sair
					[1] Criar
					[2] Listar
					[3] Deletar
					""";
            int operacao = Integer.parseInt(scanner.pedirInt(s));
            if (operando == 0 || operacao == 0) {
                break;
            }

            if (operando == 1){
                if (operacao == 1){
                    cadastrarEquipe();
                } else if (operacao == 2) {
                    for (Equipe item : negocioEquipe.procurarTodos()){
                        System.out.println(item);
                    }
                } else if (operacao == 3) {
                    deletarEquipe();
                }else {
                    System.out.println("Opção inválida, tente novamente.");
                }
            } else if (operando == 2) {
                if (operacao == 1){
                    cadastrarAluno();
                } else if (operacao == 2) {
                    for (Aluno item : negocioAluno.procurarTodos()){
                        System.out.println(item);
                    }
                } else if (operacao == 3) {
                    deletarAluno();
                }else {
                    System.out.println("Opção inválida, tente novamente.");
                }
            } else if (operando == 3) {
                if (operacao == 1){
                    cadastrarTecnico();
                } else if (operacao == 2) {
                    for (Tecnico item : negocioTecnico.procurarTodos()){
                        System.out.println(item);
                    }
                } else if (operacao == 3) {
                    deletarTecnico();
                }else {
                    System.out.println("Opção inválida, tente novamente.");
                }
            } else if (operando == 4) {
                if (operacao == 1){
                    cadastrarCampeonato();
                } else if (operacao == 2) {
                    for (Campeonato item : negocioCampeonato.procurarTodos()){
                        System.out.println(item);
                    }
                } else if (operacao == 3) {
                    deletarCampeonato();
                }else {
                    System.out.println("Opção inválida, tente novamente.");
                }
            }
        }
    }

    public static void cadastrarEquipe() throws NomeMuitoPequenoException, NomeDuplicadoException, NomeNullException, NomeVazioException{
        String nomeEquipe = scanner.pedirString("Digite o nome da equipe a ser inserida: ");
        Uf ufEquipe = scanner.pedirUf();

        try {
            negocioEquipe.inserir(new Equipe(nomeEquipe,ufEquipe));
        } catch (NomeMuitoPequenoException e) {
            System.out.println("Digite um nome com 2 ou mais caracteres, tente novamente.");
        } catch (NomeDuplicadoException e) {
            System.out.println("Já existe uma equipe com este nome, tente novamente.");
        } catch (NomeNullException e) {
            System.out.println("O nome está nulo, tente novamente.");
        } catch (NomeVazioException e) {
            System.out.println("Você não digou um nome, tente novamente.");
        }

    }

    public static void deletarEquipe() throws IdNegativoException, IdInvalidoException{
        int del = Integer.parseInt(scanner.pedirInt("Digite o número do Id da Equipe que deseja deletar."));
        try {
            negocioEquipe.deletarPorId(del);
        } catch (IdInvalidoException e) {
            System.out.println("Você passou um id inválido, tente novamente.");
        } catch (IdNegativoException e) {
            System.out.println("Não existe id negativo, tente novamente.");
        }
    }

    public static void cadastrarAluno() throws NomeNullException, NomeVazioException, NomeMuitoPequenoException, CpfCaracterException, CpfIgualException, CpfSomentoNumerosException, EquipeInvalidaException, AlunoDeMaiorException, AlunoDeMenorException{
        String nomeAluno = scanner.pedirString("Digite o nome do aluno(a) a ser cadastrado(a): ");
        int idadeAluno = Integer.parseInt(scanner.pedirInt("Digite a idade do(a) aluno(a)"));
        String cpfAluno = scanner.pedirString("Digite o cpf do(a) aluno(a): ");

        ArrayList<Equipe> equipes = negocioEquipe.procurarTodos();

        for (int i = 0; i < equipes.size(); i++){
            System.out.println(String.format("[%d] ", i) + equipes.get(i));
        }

        Equipe e = scanner.pedirEquipe(negocioEquipe, "Digite um nome de uma Equipe existente para o(a) aluno(a): ");

        try {
            negocioAluno.inserir(new Aluno(nomeAluno, cpfAluno, e, idadeAluno));
        } catch (NomeMuitoPequenoException ex) {
            System.out.println("Digite um nome com 2 ou mais caracteres, tente novamente.");
        } catch (EquipeInvalidaException ex) {
            System.out.println("Está equipe não foi encontrada no banco de dados, tente novamente.");
        } catch (CpfCaracterException ex) {
            System.out.println("O cpf deve conter 11 algarismos, tente novamente.");
        } catch (CpfSomentoNumerosException ex) {
            System.out.println("Digite apenas números no cpf, tente novamente");
        } catch (CpfIgualException ex) {
            System.out.println("Já existe um cpf igual, tente novamente.");
        } catch (AlunoDeMaiorException ex) {
            System.out.println("O(A) aluno(a) excedeu a idade máxima permitada.");
        } catch (NomeNullException ex) {
            System.out.println("O nome está nulo, tente novamente.");
        } catch (NomeVazioException ex) {
            System.out.println("Você não digou um nome, tente novamente.");
        } catch (AlunoDeMenorException ex) {
            System.out.println("O(A) aluno(a) não tem a idade mínima permitada.");
        }
    }

    public static void deletarAluno() throws IdNegativoException, IdInvalidoException{
        int del = Integer.parseInt(scanner.pedirInt("Digite o número do Id do(a) Aluno(a) que deseja deletar."));
        try {
            negocioAluno.deletarPorId(del);
        } catch (IdInvalidoException e) {
            System.out.println("Você passou um id inválido, tente novamente.");
        } catch (IdNegativoException e) {
            System.out.println("Não existe id negativo, tente novamente.");
        }
    }
}
