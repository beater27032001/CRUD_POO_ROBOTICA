package modelo;

public class Equipe {

    private int id;
    private String nome;
    private Uf uf;

    public Equipe(int id, String nome, Uf uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
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

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", uf=" + uf +
                '}';
    }
}
