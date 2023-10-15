package negocio;

import modelo.Campeonato;
import modelo.Equipe;
import modelo.Tecnico;
import negocio.excecoes.*;
import repositorio.RepositorioCampeonato;
import repositorio.RepositorioTecnico;

import java.util.ArrayList;

public class NegocioCampeonato {

    private RepositorioCampeonato repositorioCampeonato;

    public NegocioCampeonato(){
        this.repositorioCampeonato = RepositorioCampeonato.getRepositorioCampeonato();
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

    public Campeonato inserir(Campeonato item) throws NomeDuplicadoException, NomeNullException, NomeVazioException, NomeMuitoPequenoException, MesmoDiaException {
        if (item.getNome() == null){
            throw new NomeNullException();
        } else if (item.getNome().isEmpty()) {
            throw new NomeVazioException();
        } else if (item.getNome().length() < 2) {
            throw new NomeMuitoPequenoException();
        } else if (repositorioCampeonato.procurarPorNome(item.getNome()) != null) {
            throw new NomeDuplicadoException();
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
