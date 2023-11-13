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
import java.util.Arrays;

public class InterfaceTeste {

    private static RepositorioEquipe repositorioEquipe = RepositorioEquipe.getRepositorioEquipe();
    private static RepositorioCampeonato repositorioCampeonato = RepositorioCampeonato.getRepositorioCampeonato();
    private static RepositorioTecnico repositorioTecnico = RepositorioTecnico.getRepositorioTecnico();
    private static RepositorioAluno repositorioAluno = RepositorioAluno.getRepositorioAluno();

    private static NegocioEquipe negocioEquipe = new NegocioEquipe();
    private static NegocioAluno negocioAluno = new NegocioAluno();
    private static NegocioTecnico negocioTecnico = new NegocioTecnico();
    private static NegocioCampeonato negocioCampeonato = new NegocioCampeonato();

    private static ScannerAvancado scanner = new ScannerAvancado();

    private static ArrayList<Equipe> equipesE = new ArrayList<>(
            Arrays.asList(
                    new Equipe("teste1", Uf.PA),
                    new Equipe("teste2", Uf.PA)
            )
    );

    private static ArrayList<Aluno> alunosA = new ArrayList<>(
            Arrays.asList(
                    new Aluno("jose", "11111111111", null ,12),
                    new Aluno("joao", "22222222222", null ,12)
            )
    );

    private static ArrayList<Tecnico> tecnicosT = new ArrayList<>(
            Arrays.asList(
                new Tecnico("bruno", "33333333333", null ,33),
                new Tecnico("victor", "44444444444", null ,33)
            )
    );

    private static ArrayList<Integer> indice = new ArrayList<>(
            Arrays.asList(
                    0, 0 ,0
            )
    );

    public static void main(String[] args) throws NomeMuitoPequenoException, NomeDuplicadoException, NomeNullException, NomeVazioException, IdInvalidoException, IdNegativoException, CpfCaracterException, EquipeInvalidaException, CpfSomentoNumerosException, CpfIgualException, AlunoDeMaiorException, AlunoDeMenorException, TecnicoDeMenorException {

        while (true) {
            String s = """
                    Digite uma opção:
                    [0] Sair
                    [1] Equipe
                    [2] Aluno
                    [3] Técnico
                    [4] Campeonato\n           
                    """;
            int operando  = scanner.pedirInt(s);

            if (operando  == 0){
                break;
            }
            s = """
					Digite uma operação:
					[0] Sair
					[1] Criar
					[2] Listar
					[3] Deletar\n
					""";
            int operacao = scanner.pedirInt(s);
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
                    System.out.println("Opção inválida, tente novamente.\n");
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
                    System.out.println("Opção inválida, tente novamente.\n");
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
                    System.out.println("Opção inválida, tente novamente.\n");
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
                    System.out.println("Opção inválida, tente novamente.\n");
                }
            }
        }
    }

    public static void cadastrarEquipe() throws NomeMuitoPequenoException, NomeDuplicadoException, NomeNullException, NomeVazioException{
//        String nomeEquipe = scanner.pedirString("Digite o nome da equipe a ser inserida: ");
//        Uf ufEquipe = scanner.pedirUf();

        try {
//            negocioEquipe.inserir(new Equipe(nomeEquipe,ufEquipe));
            negocioEquipe.inserir(equipesE.get(indice.get(0)));
            indice.set(0, indice.get(0) + 1);
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
//        String nomeAluno = scanner.pedirString("Digite o nome do(a) aluno(a) a ser cadastrado(a): ");
//        int idadeAluno = scanner.pedirInt("Digite a idade do(a) aluno(a): ");
//        String cpfAluno = scanner.pedirString("Digite o cpf do(a) aluno(a): ");

        ArrayList<Equipe> equipes = negocioEquipe.procurarTodos();

        for (int i = 0; i < equipes.size(); i++){
            System.out.println(String.format("[%d] ", i) + equipes.get(i));
        }

//        Equipe e = scanner.pedirEquipe(negocioEquipe, "Digite um nome de uma Equipe existente para o(a) aluno(a): ");
        Equipe e = negocioEquipe.procurarPorNome(equipesE.get(indice.get(1)).getNome());
        Aluno newAluno = alunosA.get(indice.get(1));
        newAluno.setEquipe(e);
        alunosA.set(indice.get(1), newAluno);

        try {
//          negocioAluno.inserir(new Aluno(nomeAluno, cpfAluno, e, idadeAluno));
            negocioAluno.inserir(newAluno);
            indice.set(1, indice.get(1) + 1);
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
//        String nomeTecnico = scanner.pedirString("Digite o nome do(a) técnico(a) a ser cadastrado(a): ");
//        int idadeTecnico = scanner.pedirInt("Digite a idade do(a) técnico(a): ");
//        String cpfTecnico = scanner.pedirString("Digite o cpf do(a) técnico(a): ");

        ArrayList<Equipe> equipes = negocioEquipe.procurarTodos();

        for (int i = 0; i < equipes.size(); i++){
            System.out.println(String.format("[%d] ", i) + equipes.get(i));
        }

//        Equipe e = scanner.pedirEquipe(negocioEquipe, "Digite um nome de uma Equipe existente para o(a) técnico(a): ");
        Equipe e = negocioEquipe.procurarPorNome(equipesE.get(indice.get(1)).getNome());
        Tecnico newTecnico = tecnicosT.get(indice.get(1));
        newTecnico.setEquipe(e);
        tecnicosT.set(indice.get(2), newTecnico);

        try {
//          negocioTecnico.inserir(new Tecnico(nomeTecnico, cpfTecnico, e, idadeTecnico));
            negocioTecnico.inserir(newTecnico);
            indice.set(2, indice.get(2) + 1);
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
        String nomeCampeonato = scanner.pedirString("Digite o nome do campeonato a ser cadastrado(a): ");
        LocalDateTime dataCampeonato = scanner.pedirData("DiDigite a data do campeonato (Dia-Mês-Ano): ");
        int qtdEquipesCastradas = scanner.pedirInt("Existem " + negocioEquipe.procurarTodos().size() + " quantas equipes você quer cadastrar?");

        ArrayList<Equipe> es = new ArrayList<>();
        for (int i = 0; i < qtdEquipesCastradas; i++){
            Equipe e = scanner.pedirEquipe(negocioEquipe, "Digite um nome de uma Equipe existente para o(a) técnico(a): ");
            es.add(e);
            System.out.println(e);
            System.out.println(es);
        }

        try {
            negocioCampeonato.inserir(new Campeonato(nomeCampeonato, es, dataCampeonato));
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

