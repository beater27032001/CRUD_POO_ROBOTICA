package repositorio;

import modelo.Campeonato;

import java.util.ArrayList;

public class RepositorioCampeonato {

    private ArrayList<Campeonato> campeonatoes;
    private int ultimoIdAdicionado;
    private static RepositorioCampeonato singleton;


    private RepositorioCampeonato(){
        campeonatoes = new ArrayList<Campeonato>();
        ultimoIdAdicionado = 0;
    }

    //Aplica o code pattern singleton
    public static RepositorioCampeonato getRepositorioCampeonato(){
        if (singleton == null){
            singleton = new RepositorioCampeonato();
        }
        return singleton;
    }

    public Campeonato inserir(Campeonato item){
        ultimoIdAdicionado = ultimoIdAdicionado + 1;
        item.setId(ultimoIdAdicionado);
        campeonatoes.add(item);
        return item;
    }

    public ArrayList<Campeonato> procurarTodos(){
        return new ArrayList<>(campeonatoes);
    }

    public Campeonato procurarPorId(int id){
        Campeonato item = null;

        for (Campeonato campeonato: campeonatoes) {
            if (campeonato.getId() == id){
                item = campeonato;
                break;
            }
        }
        return item;
    }

    public Campeonato procurarPorNome(String nome){
        Campeonato item = null;

        for (Campeonato campeonato: campeonatoes) {
            if (campeonato.getNome().toLowerCase().equals(nome.toLowerCase())){
                item = campeonato;
                break;
            }
        }
        return item;
    }

    public void deletarTodos(){
        campeonatoes.clear();
    }

    public boolean deletarPorId(int id){
        Campeonato item = procurarPorId(id);

        if (item == null){
            return false;
        }else {
            campeonatoes.remove(item);
            return true;
        }
    }
}
