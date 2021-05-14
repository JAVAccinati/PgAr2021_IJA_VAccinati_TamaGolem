package it.unibs.pgar.tamagolem;

import java.util.ArrayList;

/**
 * Questa classe descrive l'oggetto giocatore, definito da un nome e il suo team di tamaGolem.
 */
public class Giocatore {

    //ATTRIBUTI

    private String nome;
    private ArrayList<TamaGolem> team;

    //COSTRUTTORE

    public Giocatore(String nome, ArrayList<TamaGolem> team) {
        this.nome = nome;
        this.team = team;
    }

    //GETTERS AND SETTERS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<TamaGolem> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<TamaGolem> team) {
        this.team = team;
    }

}
