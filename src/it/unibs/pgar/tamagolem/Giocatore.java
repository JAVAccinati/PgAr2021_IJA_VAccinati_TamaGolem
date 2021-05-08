package it.unibs.pgar.tamagolem;

import java.util.ArrayList;

public class Giocatore {
    //COSTANTI
    public static final int N = Elementi.values().length;
    public static final int P = (int) Math.ceil((N + 1) / 3.0) + 1;
    public static final int G = (int) Math.ceil((N - 1) * (N - 2) / (2.0 * P));

    private String nome;
    private int tamaGolemVivi;
    private ArrayList<TamaGolem> team;

    //COSTRUTTORE

    public Giocatore(String nome, ArrayList<TamaGolem> team) {
        this.nome = nome;
        this.tamaGolemVivi = G;
        this.team = team;
    }

    //GETTERS AND SETTERS

    public String getNom() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public int getTamaGolemVivi() {
        return tamaGolemVivi;
    }

    public void setTamaGolemVivi(int tamaGolemVivi) {
        this.tamaGolemVivi = tamaGolemVivi;
    }

    public ArrayList<TamaGolem> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<TamaGolem> team) {
        this.team = team;
    }

    //METODI

}
