package repositorio;

import modelo.Equipe;

import java.util.ArrayList;

public class RepositorioEquipe {

    private ArrayList<Equipe> equipes;
    private int ultimoIdAdicionado;
    private static RepositorioEquipe singleton;

    private RepositorioEquipe(){
        equipes = new ArrayList<Equipe>();
        ultimoIdAdicionado = 0;
    }

    //Aplica o code pattern singleton
    public static RepositorioEquipe getRepositorioEquipe(){
        if (singleton == null){
            singleton = new RepositorioEquipe();
        }
        return singleton;
    }

    public Equipe inserir(Equipe item){
        ultimoIdAdicionado = ultimoIdAdicionado + 1;
        item.setId(ultimoIdAdicionado);
        equipes.add(item);

        return item;
    }

    public ArrayList<Equipe> procurarTodos(){
        return new ArrayList<>(equipes);
    }

    public Equipe procurarPorId(int id){
        Equipe item = null;

        for (Equipe equipe: equipes) {
            if (equipe.getId() == id){
                item = equipe;
                break;
            }
        }
        return item;
    }

//    public Equipe procurarPorId(ArrayList<int> ids){
//
//    }

    public Equipe procurarPorNome(String nome){
        Equipe item = null;

        for (Equipe equipe: equipes) {
            if (equipe.getNome().toLowerCase().equals(nome.toLowerCase())){
                item = equipe;
                break;
            }
        }
        return item;
    }

    public void deletarTodos(){
        equipes.clear();
    }

    public boolean deletarPorId(int id){
        Equipe item = procurarPorId(id);

        if (item == null){
            return false;
        }else {
            equipes.remove(item);
            return true;
        }
    }
}
