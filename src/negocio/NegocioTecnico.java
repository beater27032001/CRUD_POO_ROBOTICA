package negocio;

import modelo.Aluno;
import modelo.Tecnico;
import negocio.excecoes.*;
import repositorio.RepositorioTecnico;

import java.util.ArrayList;

public class NegocioTecnico {

    private RepositorioTecnico repositorioTecnico;

    public NegocioTecnico(){
        this.repositorioTecnico = RepositorioTecnico.getRepositorioTecnico();
    }

    public ArrayList<Tecnico> procurarTodos(){
        return repositorioTecnico.procurarTodos();
    }

    public Tecnico procurarPorNome(String nome){
        return repositorioTecnico.procurarPorNome(nome);
    }

    public Tecnico procurarPorId(int id){
        return repositorioTecnico.procurarPorId(id);
    }

    public Tecnico inserir(Tecnico item) throws NomeNullException, NomeVazioException, NomeMuitoPequenoException, TecnicoDeMenorException {
        if (item.getNome() == null){
            throw new NomeNullException();
        } else if (item.getNome().isEmpty()) {
            throw new NomeVazioException();
        } else if (item.getNome().length() < 2) {
            throw new NomeMuitoPequenoException();
        }

        if (item.getIdade() < 18){
            throw new TecnicoDeMenorException();
        }

        return repositorioTecnico.inserir(item);
    }

    public boolean deletarPorId(int id) throws IdNegativoException, IdInvalidoException{
        if (id < 0){
            throw new IdNegativoException();
        }

        Tecnico t = repositorioTecnico.procurarPorId(id);

        if (t == null){
            throw new IdInvalidoException();
        }

        return repositorioTecnico.deletarPorId(id);
    }

    public void deletarTodos(){
        repositorioTecnico.deletarTodos();
    }
}
