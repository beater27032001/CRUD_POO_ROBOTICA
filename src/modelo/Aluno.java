package modelo;

public class Aluno extends Pessoa{

    private int idade;

    public Aluno(String nome, String cpf, Equipe equipe, int idade) {
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
        return "Aluno{" +
                "idade=" + idade +
                '}';
    }
}
