package negocio;

import modelo.Aluno;
import modelo.Campeonato;
import modelo.Equipe;
import modelo.Tecnico;
import negocio.excecoes.*;
import repositorio.RepositorioAluno;
import repositorio.RepositorioCampeonato;
import repositorio.RepositorioEquipe;
import repositorio.RepositorioTecnico;

import java.util.ArrayList;

public class NegocioCampeonato {

    private RepositorioCampeonato repositorioCampeonato;
    private RepositorioEquipe repositorioEquipe;
    private RepositorioTecnico repositorioTecnico;
    private RepositorioAluno repositorioAluno;

    public NegocioCampeonato(){
        this.repositorioCampeonato = RepositorioCampeonato.getRepositorioCampeonato();
        this.repositorioEquipe = RepositorioEquipe.getRepositorioEquipe();
        this.repositorioTecnico = RepositorioTecnico.getRepositorioTecnico();
        this.repositorioAluno = RepositorioAluno.getRepositorioAluno();
    }

    public ArrayList<Campeonato> procurarTodos(){
        return repositorioCampeonato.procurarTodos();
    }

    public Campeonato procurarPorNome(String nome){
        return repositorioCampeonato.procurarPorNome(nome);
    }

    public Campeonato procurarPorId(int id){
        return repositorioCampeonato.procurarPorId(id);
    }

    public Campeonato inserir(Campeonato item) throws NomeDuplicadoException, NomeNullException, NomeVazioException, NomeMuitoPequenoException, MesmoDiaException, EquipeInvalidaException, EquipeTecnicoInvalidaException, EquipeAlunoInvalidaException {
        if (item.getNome() == null){
            throw new NomeNullException();
        } else if (item.getNome().isEmpty()) {
            throw new NomeVazioException();
        } else if (item.getNome().length() < 2) {
            throw new NomeMuitoPequenoException();
        } else if (repositorioCampeonato.procurarPorNome(item.getNome()) != null) {
            throw new NomeDuplicadoException();
        }

        //Checa se cada equipe do campeonato é válida
        System.out.println(item.getEquipes());
        for (Equipe equipe: item.getEquipes()) {
            Equipe e = repositorioEquipe.procurarPorId(equipe.getId());
            ArrayList<Tecnico> t = repositorioTecnico.procurarPorEquipe(e.getNome());
            ArrayList<Aluno> a = repositorioAluno.procurarPorEquipe(e.getNome());

            if (e == null){
                throw new EquipeInvalidaException();
            } else if (t.size() < 1 || t.size() > 2) {
                throw new EquipeTecnicoInvalidaException();
            } else if (a.size() < 2 || a.size() > 10) {
                throw new EquipeAlunoInvalidaException();
            }
        }

        for (Campeonato campeonato: repositorioCampeonato.procurarTodos()) {
            if (item.getDia().isEqual(campeonato.getDia())){
                throw new MesmoDiaException();
            }
        }

        return repositorioCampeonato.inserir(item);
    }

    public boolean deletarPorId(int id) throws IdNegativoException, IdInvalidoException{
        if (id < 0){
            throw new IdNegativoException();
        }

        Campeonato c = repositorioCampeonato.procurarPorId(id);

        if (c == null){
            throw new IdInvalidoException();
        }

        return repositorioCampeonato.deletarPorId(id);
    }

    public void deletarTodos(){
        repositorioCampeonato.deletarTodos();
    }
}
