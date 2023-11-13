package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Campeonato {

    private int id;
    private String nome;
    private ArrayList<Equipe> equipes;
    private LocalDateTime dia;

    public Campeonato(String nome, ArrayList<Equipe> equipes, LocalDateTime dia) {
        this.id = 0;
        this.nome = nome;
        this.equipes = equipes;
        this.dia = dia;
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

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(ArrayList<Equipe> equipes) {
        this.equipes = equipes;
    }

    public LocalDateTime getDia() {
        return dia;
    }

    public void setDia(LocalDateTime dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "{Nome: " + nome + " - " +
                "equipes: (" + equipes + ") - " +
                "dia: " + dia + " - " +
                "id: " + id +
                '}';
    }
}
