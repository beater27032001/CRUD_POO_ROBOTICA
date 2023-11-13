package negocio;

import modelo.Aluno;
import modelo.Equipe;
import negocio.excecoes.*;
import repositorio.RepositorioAluno;
import repositorio.RepositorioEquipe;

import java.util.ArrayList;

public class NegocioAluno {

    private RepositorioAluno repositorioAluno;
    private NegocioEquipe negocioEquipe;

    public NegocioAluno(){
        this.repositorioAluno = RepositorioAluno.getRepositorioAluno();
    }

    public ArrayList<Aluno> procurarTodos(){
        return repositorioAluno.procurarTodos();
    }

    public Aluno procurarPorNome(String nome){
        return repositorioAluno.procurarPorNome(nome);
    }

    public Aluno procurarPorId(int id){
        return repositorioAluno.procurarPorId(id);
    }

    public Aluno inserir(Aluno item) throws NomeNullException, NomeVazioException, NomeMuitoPequenoException, AlunoDeMaiorException, AlunoDeMenorException, EquipeInvalidaException, CpfSomentoNumerosException, CpfCaracterException, CpfIgualException {
        if (item.getNome() == null){
            throw new NomeNullException();
        } else if (item.getNome().isEmpty()) {
            throw new NomeVazioException();
        } else if (item.getNome().length() < 2) {
            throw new NomeMuitoPequenoException();
        }

        if (item.getIdade() < 9){
            throw new AlunoDeMenorException();
        }else if (item.getIdade() > 15){
            throw new AlunoDeMaiorException();
        }

        if (item.getCpf().indexOf(".") != -1 || item.getCpf().indexOf("-") != -1) {
            throw new CpfSomentoNumerosException();
        } else if (item.getCpf().length() != 11) {
            throw new CpfCaracterException();
        }

        if (repositorioAluno.procurarPorCpf(item.getCpf()) != null) {
            throw new CpfIgualException();
        }


        int qtdAlunos = repositorioAluno.procurarPorEquipe(item.getEquipe().getNome()).size();

        if (qtdAlunos > 10){
            throw new EquipeInvalidaException();
        }


//        negocioEquipe.validate(item.getEquipe());

        return repositorioAluno.inserir(item);
    }

    public boolean deletarPorId(int id) throws IdNegativoException, IdInvalidoException{
        if (id < 0){
            throw new IdNegativoException();
        }

        Aluno a = repositorioAluno.procurarPorId(id);

        if (a == null){
            throw new IdInvalidoException();
        }

        return repositorioAluno.deletarPorId(id);
    }

    public void deletarTodos(){
        repositorioAluno.deletarTodos();
    }
}
