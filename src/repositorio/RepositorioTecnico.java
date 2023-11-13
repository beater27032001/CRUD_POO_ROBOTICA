package repositorio;

import modelo.Aluno;
import modelo.Tecnico;

import java.util.ArrayList;

public class RepositorioTecnico {

    private ArrayList<Tecnico> tecnicos;
    private int ultimoIdAdicionado;
    private static RepositorioTecnico singleton;

    private RepositorioTecnico(){
        tecnicos = new ArrayList<Tecnico>();
        ultimoIdAdicionado = 0;
    }

    //Aplica o code pattern singleton
    public static RepositorioTecnico getRepositorioTecnico(){
        if (singleton == null){
            singleton = new RepositorioTecnico();
        }
        return singleton;
    }

    public Tecnico inserir(Tecnico item){
        ultimoIdAdicionado = ultimoIdAdicionado + 1;
        item.setId(ultimoIdAdicionado);
        tecnicos.add(item);

        return item;
    }

    public ArrayList<Tecnico> procurarTodos(){
        return new ArrayList<>(tecnicos);
    }

    public Tecnico procurarPorId(int id){
        Tecnico item = null;

        for (Tecnico tecnico: tecnicos) {
            if (tecnico.getId() == id){
                item = tecnico;
                break;
            }
        }
        return item;
    }

    public Tecnico procurarPorNome(String nome){
        Tecnico item = null;

        for (Tecnico tecnico: tecnicos) {
            if (tecnico.getNome().toLowerCase().equals(nome.toLowerCase())){
                item = tecnico;
                break;
            }
        }
        return item;
    }

    public ArrayList<Tecnico> procurarPorEquipe(String nomeEquipe){
        ArrayList<Tecnico> tecnicoFiltrados = new ArrayList<>();

        for (Tecnico tecnico: this.tecnicos) {
            if (tecnico.getEquipe().getNome().toLowerCase().equals(nomeEquipe.toLowerCase())){
                tecnicoFiltrados.add(tecnico);
            }
        }
        return tecnicoFiltrados;
    }

    public Tecnico procurarPorCpf(String cpf){
        Tecnico cpfTecnico = null;
        for (Tecnico tecnico: tecnicos){
            if (tecnico.getCpf().equals(cpf)){
                cpfTecnico = tecnico;
                break;
            }
        }
        return cpfTecnico;
    }

    public void deletarTodos(){
        tecnicos.clear();
    }

    public boolean deletarPorId(int id){
        Tecnico item = procurarPorId(id);

        if (item == null){
            return false;
        }else {
            tecnicos.remove(item);
            return true;
        }
    }
}
