package modelo;

public class Pessoa {

    private int id;
    private String nome;
    private String cpf;
    private Equipe equipe;

    public Pessoa(int id, String nome, String cpf, Equipe equipe) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.equipe = equipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", equipe=" + equipe +
                '}';
    }
}
