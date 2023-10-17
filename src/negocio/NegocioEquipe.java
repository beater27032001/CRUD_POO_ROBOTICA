package negocio;

import modelo.Aluno;
import modelo.Equipe;
import modelo.Tecnico;
import negocio.excecoes.*;
import repositorio.RepositorioAluno;
import repositorio.RepositorioEquipe;
import repositorio.RepositorioTecnico;

import java.util.ArrayList;

public class NegocioEquipe {

    private RepositorioEquipe repositorioEquipe;
    private RepositorioAluno repositorioAluno;
    private RepositorioTecnico repositorioTecnico;

    public NegocioEquipe() {
        this.repositorioEquipe = RepositorioEquipe.getRepositorioEquipe();
        this.repositorioAluno = RepositorioAluno.getRepositorioAluno();
        this.repositorioTecnico = RepositorioTecnico.getRepositorioTecnico();
    }

    public void validate(Equipe equipe){
        int qtdAluno = repositorioAluno.procurarPorEquipe(equipe.getNome()).size();
        int qtdTecnico = repositorioTecnico.procurarPorEquipe(equipe.getNome()).size();
        if (qtdAluno >= 2 && qtdAluno <= 10 && qtdTecnico >= 1 && qtdTecnico <= 2){
            equipe.setValid(true);
        }
    }

    public ArrayList<Equipe> procurarTodos(){
        return repositorioEquipe.procurarTodos();
    }

    public Equipe procurarPorNome(String nome){
        return repositorioEquipe.procurarPorNome(nome);
    }

    public Equipe procurarPorId(int id){
        return repositorioEquipe.procurarPorId(id);
    }

    public Equipe inserir(Equipe item) throws NomeDuplicadoException, NomeNullException, NomeVazioException, NomeMuitoPequenoException{
        if (item.getNome() == null){
            throw new NomeNullException();
        } else if (item.getNome().isEmpty()) {
            throw new NomeVazioException();
        } else if (item.getNome().length() < 2) {
            throw new NomeMuitoPequenoException();
        } else if (repositorioEquipe.procurarPorNome(item.getNome()) != null) {
            throw new NomeDuplicadoException();
        }

        return repositorioEquipe.inserir(item);
    }

    public boolean deletarPorId(int id) throws IdNegativoException, IdInvalidoException {
        if (id < 0){
            throw new IdNegativoException();
        }

        Equipe e = repositorioEquipe.procurarPorId(id);

        if (e == null){
            throw new IdInvalidoException();
        }

        ArrayList<Aluno> alunos = repositorioAluno.procurarPorEquipe(e.getNome());
        ArrayList<Tecnico> tecnicos = repositorioTecnico.procurarPorEquipe(e.getNome());

        for (Tecnico tecnico: tecnicos) {
            repositorioTecnico.deletarPorId(tecnico.getId());
        }

        for (Aluno aluno: alunos) {
            repositorioAluno.deletarPorId(aluno.getId());
        }

        return repositorioEquipe.deletarPorId(id);
    }

    public void deletarTodos(){
        repositorioEquipe.deletarTodos();
    }



}
