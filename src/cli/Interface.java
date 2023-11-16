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

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    public static void main(String[] args) throws NomeMuitoPequenoException, NomeDuplicadoException, NomeNullException, NomeVazioException, IdInvalidoException, IdNegativoException, CpfCaracterException, EquipeInvalidaException, CpfSomentoNumerosException, CpfIgualException, AlunoDeMaiorException, AlunoDeMenorException, TecnicoDeMenorException {
        System.out.println("Bem-vindo ao sistema de cadastro para robótica.");
        System.out.println("Para cadastrar os componentes siga as regras padrão da FLL.");
        while (true) {

            String s = """
                    \nDigite uma opção:
                    [0] Sair
                    [1] Equipe
                    [2] Aluno
                    [3] Técnico
                    [4] Campeonato          
                    """;
            int operando  = scanner.pedirInt(s);
            if (operando  == 0){
                break;
            }

//            s = """
//					\nDigite uma operação:
//					[0] Sair
//					[1] Criar
//					[2] Listar
//					[3] Deletar
//					""";
//            int operacao = scanner.pedirInt(s);
//            if (operando == 0 || operacao == 0) {
//                break;
//            }

            if (operando == 1){
                s = """
					\nDigite uma operação:
					[0] Sair
					[1] Criar
					[2] Listar
					[3] Deletar
					""";
                int operacao = scanner.pedirInt(s);
                if (operando == 0 || operacao == 0) {
                    break;
                }

                if (operacao == 1){
                    cadastrarEquipe();
                } else if (operacao == 2) {
                    for (Equipe item : negocioEquipe.procurarTodos()){
                        System.out.println(item);
                    }
                } else if (operacao == 3) {
                    deletarEquipe();
                }else {
                    System.out.println("Opção inválida, tente novamente.\n");
                }
            } else if (operando == 2) {
                s = """
					\nDigite uma operação:
					[0] Sair
					[1] Criar
					[2] Listar
					[3] Deletar
					""";
                int operacao = scanner.pedirInt(s);
                if (operando == 0 || operacao == 0) {
                    break;
                }

                if (operacao == 1){
                    cadastrarAluno();
                } else if (operacao == 2) {
                    for (Aluno item : negocioAluno.procurarTodos()){
                        System.out.println(item);
                    }
                } else if (operacao == 3) {
                    deletarAluno();
                }else {
                    System.out.println("Opção inválida, tente novamente.\n");
                }
            } else if (operando == 3) {
                s = """
					\nDigite uma operação:
					[0] Sair
					[1] Criar
					[2] Listar
					[3] Deletar
					""";
                int operacao = scanner.pedirInt(s);
                if (operando == 0 || operacao == 0) {
                    break;
                }

                if (operacao == 1){
                    cadastrarTecnico();
                } else if (operacao == 2) {
                    for (Tecnico item : negocioTecnico.procurarTodos()){
                        System.out.println(item);
                    }
                } else if (operacao == 3) {
                    deletarTecnico();
                }else {
                    System.out.println("Opção inválida, tente novamente.\n");
                }
            } else if (operando == 4) {
                s = """
					\nDigite uma operação:
					[0] Sair
					[1] Criar
					[2] Listar
					[3] Deletar
					""";
                int operacao = scanner.pedirInt(s);
                if (operando == 0 || operacao == 0) {
                    break;
                }

                if (operacao == 1){
                   cadastrarCampeonato();
                } else if (operacao == 2) {
                    for (Campeonato item : negocioCampeonato.procurarTodos()){
                        System.out.println(item);
                    }
                } else if (operacao == 3) {
                    deletarCampeonato();
                }else {
                    System.out.println("Opção inválida, tente novamente.\n");
                }
            } else {
                System.out.println("Opção inválida, tente novamente.\n");
            }
        }
    }

    public static void cadastrarEquipe() throws NomeMuitoPequenoException, NomeDuplicadoException, NomeNullException, NomeVazioException{
        String nomeEquipe = scanner.pedirString("Digite o nome da equipe a ser inserida: ");
        Uf ufEquipe = scanner.pedirUf();

        try {
            negocioEquipe.inserir(new Equipe(nomeEquipe,ufEquipe));
            System.out.println("Cadastro feito com sucesso.");
        } catch (NomeMuitoPequenoException e) {
            System.out.println("Digite um nome com 2 ou mais caracteres, tente novamente.\n");
        } catch (NomeDuplicadoException e) {
            System.out.println("Já existe uma equipe com este nome, tente novamente.\n");
        } catch (NomeNullException e) {
            System.out.println("O nome está nulo, tente novamente.\n");
        } catch (NomeVazioException e) {
            System.out.println("Você não digou um nome, tente novamente.\n");
        }

    }

    public static void deletarEquipe() throws IdNegativoException, IdInvalidoException{
        int del = scanner.pedirInt("Digite o número do Id da Equipe que deseja deletar.\n");
        try {
            negocioEquipe.deletarPorId(del);
        } catch (IdInvalidoException e) {
            System.out.println("Você passou um id inválido, tente novamente.\n");
        } catch (IdNegativoException e) {
            System.out.println("Não existe id negativo, tente novamente.\n");
        }
    }

    public static void cadastrarAluno() throws NomeNullException, NomeVazioException, NomeMuitoPequenoException, CpfCaracterException, CpfIgualException, CpfSomentoNumerosException, EquipeInvalidaException, AlunoDeMaiorException, AlunoDeMenorException{
        ArrayList<Equipe> equipes = negocioEquipe.procurarTodos();


        if(equipes.size() < 1){
            System.out.println("Não existe nenhuma equipe cadastrada");
        }else {
            String nomeAluno = scanner.pedirString("Digite o nome do(a) aluno(a) a ser cadastrado(a): ");
            int idadeAluno = scanner.pedirInt("Digite a idade do(a) aluno(a): ");
            String cpfAluno = scanner.pedirString("Digite o cpf do(a) aluno(a) (Somente números): ");

            for (int i = 0; i < equipes.size(); i++){
                System.out.println(String.format("[%d] ", i) + equipes.get(i));
            }

            Equipe e = scanner.pedirEquipe(negocioEquipe, "Digite um nome de uma Equipe existente para o(a) aluno(a): ");

            try {
                negocioAluno.inserir(new Aluno(nomeAluno, cpfAluno, e, idadeAluno));
                System.out.println("Cadastro feito com sucesso.");
            } catch (NomeMuitoPequenoException ex) {
                System.out.println("Digite um nome com 2 ou mais caracteres, tente novamente.\n");
            } catch (EquipeInvalidaException ex) {
                System.out.println("Está equipe não foi encontrada no banco de dados, tente novamente.\n");
            } catch (CpfCaracterException ex) {
                System.out.println("O cpf deve conter 11 algarismos, tente novamente.\n");
            } catch (CpfSomentoNumerosException ex) {
                System.out.println("Digite apenas números no cpf, tente novamente.\n");
            } catch (CpfIgualException ex) {
                System.out.println("Já existe um cpf igual, tente novamente.\n");
            } catch (AlunoDeMaiorException ex) {
                System.out.println("O(A) aluno(a) excedeu a idade máxima permitada.\n");
            } catch (NomeNullException ex) {
                System.out.println("O nome está nulo, tente novamente.\n");
            } catch (NomeVazioException ex) {
                System.out.println("Você não digou um nome, tente novamente.\n");
            } catch (AlunoDeMenorException ex) {
                System.out.println("O(A) aluno(a) não tem a idade mínima permitada.\n");
            }
        }

    }

    public static void deletarAluno() throws IdNegativoException, IdInvalidoException{
        int del = scanner.pedirInt("Digite o número do Id do(a) Aluno(a) que deseja deletar.");
        try {
            negocioAluno.deletarPorId(del);
        } catch (IdInvalidoException e) {
            System.out.println("Você passou um id inválido, tente novamente.\n");
        } catch (IdNegativoException e) {
            System.out.println("Não existe id negativo, tente novamente.\n");
        }
    }

    private static void cadastrarTecnico() throws NomeMuitoPequenoException, EquipeInvalidaException, CpfCaracterException, CpfIgualException, CpfSomentoNumerosException, NomeNullException, NomeVazioException, TecnicoDeMenorException{
        ArrayList<Equipe> equipes = negocioEquipe.procurarTodos();


        if (equipes.size() < 1){
            System.out.println("Não existe nenhuma equipe cadastrada, cadastre uma equipe antes.");
        } else {
            String nomeTecnico = scanner.pedirString("Digite o nome do(a) técnico(a) a ser cadastrado(a): ");
            int idadeTecnico = scanner.pedirInt("Digite a idade do(a) técnico(a): ");
            String cpfTecnico = scanner.pedirString("Digite o cpf do(a) técnico(a) (Somente números): ");


            for (int i = 0; i < equipes.size(); i++){
                System.out.println(String.format("[%d] ", i) + equipes.get(i));
            }

            Equipe e = scanner.pedirEquipe(negocioEquipe, "Digite um nome de uma Equipe existente para o(a) técnico(a): ");

            try {
                negocioTecnico.inserir(new Tecnico(nomeTecnico, cpfTecnico, e, idadeTecnico));
                System.out.println("Cadastro feito com sucesso.");
            } catch (NomeMuitoPequenoException ex) {
                System.out.println("Digite um nome com 2 ou mais caracteres, tente novamente.\n");
            } catch (EquipeInvalidaException ex) {
                System.out.println("Está equipe não foi encontrada no banco de dados, tente novamente.\n");
            } catch (CpfCaracterException ex) {
                System.out.println("O cpf deve conter 11 algarismos, tente novamente.\n");
            } catch (CpfSomentoNumerosException ex) {
                System.out.println("Digite apenas números no cpf, tente novamente.\n");
            } catch (CpfIgualException ex) {
                System.out.println("Já existe um cpf igual, tente novamente.\n");
            } catch (NomeNullException ex) {
                System.out.println("O nome está nulo, tente novamente.\n");
            } catch (NomeVazioException ex) {
                System.out.println("Você não digou um nome, tente novamente.\n");
            } catch (TecnicoDeMenorException ex){
                System.out.println("Você ainda não atingiu a maior idade para virar técnico(a), tente novamente.\n");
            }
        }
    }

    private static void deletarTecnico() throws IdNegativoException, IdInvalidoException{
        int del = scanner.pedirInt("Digite o número do Id do(a) Técnico(a) que deseja deletar.");
        try {
            negocioAluno.deletarPorId(del);
        } catch (IdInvalidoException e) {
            System.out.println("Você passou um id inválido, tente novamente.\n");
        } catch (IdNegativoException e) {
            System.out.println("Não existe id negativo, tente novamente.\n");
        }
    }

    private static void cadastrarCampeonato(){
        ArrayList<Equipe> equipes = negocioEquipe.procurarTodos();

        if (equipes.size() < 1){
            System.out.println("Não existe nenhuma equipe cadastrada, cadastre uma equipe antes.");
        } else{
            String nomeCampeonato = scanner.pedirString("Digite o nome do campeonato a ser cadastrado(a): ");
            LocalDateTime dataCampeonato = scanner.pedirData("DiDigite a data do campeonato (yyyy-MM-dd): ");
            int qtdEquipesCastradas = scanner.pedirInt("Existem " + negocioEquipe.procurarTodos().size() + " equipe, quantas equipes você quer cadastrar?");

            ArrayList<Equipe> es = new ArrayList<>();
            for (int i = 0; i < qtdEquipesCastradas; i++){
                Equipe e = scanner.pedirEquipe(negocioEquipe, "Digite um nome de uma Equipe existente: ");
                es.add(e);
            }

            try {
                negocioCampeonato.inserir(new Campeonato(nomeCampeonato, es, dataCampeonato));
                System.out.println("Cadastro feito com sucesso.");
            } catch (NomeMuitoPequenoException e) {
                System.out.println("Digite um nome com 2 ou mais caracteres, tente novamente.\n");
            } catch (EquipeInvalidaException e) {
                System.out.println("Não existe nenhuma quipe cadastrada, tente novamente.\n");
            } catch (MesmoDiaException e) {
                System.out.println("Já existe um campeonato nesse dia, tente novamente.\n");
            } catch (NomeDuplicadoException e) {
                System.out.println("Já existe uma equipe com este nome, tente novamente.\n");
            } catch (EquipeAlunoInvalidaException e) {
                System.out.println("A equipe não atendeu os requisitos mínimos de alunos, tente novamente.\n");
            } catch (NomeNullException e) {
                System.out.println("O nome está nulo, tente novamente.\n");
            } catch (EquipeTecnicoInvalidaException e) {
                System.out.println("A equipe não atendeu os requisitos mínimos de técnicos, tente novamente.\n");
            } catch (NomeVazioException e) {
                System.out.println("Você não digou um nome, tente novamente.\n");
            }
        }
    }

    private static void deletarCampeonato(){
        int del = scanner.pedirInt("Digite o número do Id do Campeonato que deseja deletar.\n");
        try {
            negocioCampeonato.deletarPorId(del);
        } catch (IdInvalidoException e) {
            System.out.println("Você passou um id inválido, tente novamente.\n");
        } catch (IdNegativoException e) {
            System.out.println("Não existe id negativo, tente novamente.\n");
        }
    }

}

