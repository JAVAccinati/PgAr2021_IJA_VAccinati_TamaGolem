package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

import java.util.ArrayList;

public class TamaGolem {
    //COSTANTI
    public static final int V = 7;
    public static final int N = Elementi.values().length;
    public static final int P = (int) Math.ceil((N + 1) / 3.0) + 1;

    private String nome;
    private int vita;
    private Elementi[] pietre;
    private boolean stato; //vivo (true) o troppo stanco per combattere (false) {perche' i nostri tamagolem non muoiono mai grazie del potere dell'amicizia}

    //COSTRUTTORE
    public TamaGolem(String nome, Elementi[] pietre) {
        this.nome = nome;
        this.vita = V;
        this.pietre = pietre;
        this.stato = true;
    }

    //GETTERS AND SETTERS
    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public Elementi[] getPietre() {
        return pietre;
    }

    public void setPietre(Elementi[] pietre) {
        this.pietre = pietre;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    //METODI
    public static String generaNomeTamaGolem(ArrayList<String> nomi) {
        int indice = NumeriCasuali.estraiIntero(0, nomi.size());
        String nome = nomi.get(indice);
        nomi.remove(indice);
        return nome;
    }

}
