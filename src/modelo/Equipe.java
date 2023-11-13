package modelo;

public class Equipe {

    private int id;
    private String nome;
    private Uf uf;

    public Equipe(String nome, Uf uf) {
        this.id = 0;
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
        return "{Nome: " + nome + " - " +
                "UF: " + uf + " - " +
                "id: " + id +
                '}';
    }


}
