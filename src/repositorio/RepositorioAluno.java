package repositorio;

import modelo.Aluno;
import modelo.Equipe;
import modelo.Tecnico;

import java.util.ArrayList;

public class RepositorioAluno {

    private ArrayList<Aluno> alunos;
    private int ultimoIdAdicionado;
    private static RepositorioAluno singleton;

    private RepositorioAluno(){
        alunos = new ArrayList<Aluno>();
        ultimoIdAdicionado = 0;
    }

    public static RepositorioAluno getRepositorioAluno(){
        if (singleton == null){
            singleton = new RepositorioAluno();
        }
        return singleton;
    }

    public Aluno inserir(Aluno item){
        ultimoIdAdicionado = ultimoIdAdicionado + 1;
        //item.setId(alunos.size());
        item.setId(ultimoIdAdicionado);
        alunos.add(item);

        return item;
    }

    public ArrayList<Aluno> procurarTodos(){
        return new ArrayList<>(alunos);
    }

    public Aluno procurarPorId(int id){
        Aluno item = null;

        for (Aluno aluno: alunos) {
            if (aluno.getId() == id){
                item = aluno;
                break;
            }
        }
        return item;
    }

    public Aluno procurarPorNome(String nome){
        Aluno item = null;

        for (Aluno aluno: alunos) {
            if (aluno.getNome().toLowerCase().equals(nome.toLowerCase())){
                item = aluno;
                break;
            }
        }
        return item;
    }

    public ArrayList<Aluno> procurarPorEquipe(String nomeEquipe){
        ArrayList<Aluno> alunosFiltrados = new ArrayList<>();

        for (Aluno aluno: alunosFiltrados) {
            if (aluno.getEquipe().getNome().equals(nomeEquipe)){
                alunosFiltrados.add(aluno);
            }
        }
        return alunosFiltrados;
    }

    public void deletarTodos(){
        alunos.clear();
    }

    public boolean deletarPorId(int id){
        Aluno item = procurarPorId(id);

        if (item == null){
            return false;
        }else {
            alunos.remove(item);
            return true;
        }
    }

}
