package it.unibs.pgar.tamagolem;

import java.util.ArrayList;

public class Partita {
    //COSTANTI
    public static final int N = Elementi.values().length;
    public static final int P = (int) Math.ceil((N + 1) / 3.0) + 1;
    public static final int G = (int) Math.ceil((N - 1) * (N - 2) / (2.0 * P));
    public static final int S = (int) Math.ceil((2.0 * G * P) / N) * N;
    public static final int S_FRATTO_N = S / N;

    private static Giocatore giocatore1;
    private static Giocatore giocatore2;

    //METODI
    public static void eseguiPartita(int grafo[][]) {

        //genera lista nomi tamagolem
        ArrayList<String> nomi = new ArrayList<>();
        for (int i = 0; i < NomiTamaGolem.values().length; i++) {
            char[] nomiChar = NomiTamaGolem.values()[i].toString().toCharArray();
            for (int j = 0; j < nomiChar.length; j++) {
                if (nomiChar[j] == '_') {
                    nomiChar[j] = ' ';
                }
            }
            nomi.add(new String(nomiChar));
        }

        int[] pietreRimaste = new int[N];
        for (int k = 0; k < N; k++) {
            pietreRimaste[k] = S_FRATTO_N;
        }

        giocatore1 = InterazioneUtente.creaGiocatore(1, pietreRimaste);
        giocatore2 = InterazioneUtente.creaGiocatore(2, pietreRimaste);

        evocazione();

        scontro();

        statoBattaglia();

    }

    private static void evocazione() {

    }

    private static void scontro() {

    }

    private static boolean statoBattaglia() {
        return true;
    }

    public static boolean giocaDiNuovo() {

        return true;
    }
}
