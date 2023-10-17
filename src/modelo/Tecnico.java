package modelo;

public class Tecnico extends Pessoa{

    private int idade;

    public Tecnico(String nome, String cpf, Equipe equipe, int idade) {
        super(nome, cpf, equipe);
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "idade=" + idade +
                '}';
    }
}
