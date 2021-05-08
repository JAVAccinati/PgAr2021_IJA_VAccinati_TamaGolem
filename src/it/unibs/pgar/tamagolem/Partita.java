package it.unibs.pgar.tamagolem;

import java.util.ArrayList;

public class Partita {
    //COSTANTI
    public static final int N = Elementi.values().length;
    public static final int P = (int) Math.ceil((N + 1) / 3.0) + 1;
    public static final int G = (int) Math.ceil((N - 1) * (N - 2) / (2.0 * P));
    public static final int S = (int) Math.ceil((2.0 * G * P) / N) * N;
    public static final int S_FRATTO_N = S / N;

    private static Giocatore[] giocatori = new Giocatore[2];

    private static Giocatore giocatore1;
    private static Giocatore giocatore2;


    //METODI
    public static void eseguiPartita(int grafo[][]) {
        //setup
        int indiceGiocatore = 1;
        int indiceTamaGolem;
        String[] elementi = setupElementi();
        ArrayList<String> nomi = setupNomiTamaGolem();
        int[] pietreRimaste = setupPietreRimaste();

        giocatori[0] = InterazioneUtente.creaGiocatore(1);
        indiceTamaGolem = giocatori[indiceGiocatore - 1].getTeam().size() + 1;
        evocazione(indiceGiocatore, indiceTamaGolem, elementi, nomi, pietreRimaste);

        indiceGiocatore++;

        giocatori[1] = InterazioneUtente.creaGiocatore(2);
        indiceTamaGolem = giocatori[indiceGiocatore - 1].getTeam().size() + 1;
        evocazione(indiceGiocatore, indiceTamaGolem, elementi, nomi, pietreRimaste);

        // TODO da qui
        scontro();

        statoBattaglia();

    }

    private static ArrayList<String> setupNomiTamaGolem() {
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
        return nomi;
    }

    private static String[] setupElementi() {
        String[] elementi = new String[N];
        for (int i = 0; i < N; i++) {
            elementi[i] = Elementi.values()[i].toString();
        }
        return elementi;
    }

    private static int[] setupPietreRimaste() {
        int[] pietreRimaste = new int[N];
        for (int i = 0; i < N; i++) {
            pietreRimaste[i] = S_FRATTO_N;
        }
        return pietreRimaste;
    }

    private static void evocazione(int indiceGiocatore, int indiceTamaGolem, String[] elementi, ArrayList<String> nomi, int[] pietreRimaste) {
        TamaGolem tamaGolem = InterazioneUtente.inizializzaTamaGolem(indiceGiocatore, indiceTamaGolem, elementi, nomi, pietreRimaste);
    }

    private static void scontro() {

    }

    private static boolean statoBattaglia() {
        return true;
    }

    public static boolean giocaDiNuovo() {

        return false;
    }
}
