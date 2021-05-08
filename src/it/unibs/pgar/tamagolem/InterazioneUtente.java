package it.unibs.pgar.tamagolem;

import it.unibs.fp.ourlib.OurInputDati;

import java.util.ArrayList;

public class InterazioneUtente {

    //COSTANTI
    public static final int N = Elementi.values().length; //9
    public static final int P = (int) Math.ceil((N + 1) / 3.0) + 1; //5
    public static final int G = (int) Math.ceil((N - 1) * (N - 2) / (2.0 * P)); //6
    public static final int S = (int) Math.ceil((2.0 * G * P) / N) * N; //7 * 9 = 63 (7 per ogni elemento)
    public static final int S_FRATTO_N = S / N; //7

    public static final String NOME_GIOCATORE = "Inserisci il nome del giocatore %d: ";

    public static final String GENERAZIONE_TAMAGOLEM = "Giocatore %d, e' ora di evocare il TamaGolem numero %d: ";
    public static final String CREAZIONE_NOME = "Il tuo TamaGolem si chiamera' %s";
    public static final String ELEMENTI_PIETRE = "Scegli le pietre DA DAr DA mangiar al TamaGolem %s: ";
    public static final String PIETRE_DISPONIBILI = "%d) %s (%d/%d)";
    public static final String SCELTA_PIETRE = "Quale pietra vuoi DAr DA mangiar? ";
    public static final String ERRORE_PIETRE_FINITE = "ERRORE! Le pietre di questo tipo sono finite.";

    //METODI
    public static Giocatore creaGiocatore(int indiceGiocatore) {
        String nome = OurInputDati.leggiStringaNonVuota(String.format(NOME_GIOCATORE, indiceGiocatore));

        ArrayList<TamaGolem> team = new ArrayList<>();

        Giocatore giocatore = new Giocatore(nome, team);
        return giocatore;
    }

    public static TamaGolem inizializzaTamaGolem(int indiceGiocatore, int indiceTamaGolem, String[] elementi, ArrayList<String> nomi, int[] pietreRimaste) {

        System.out.println(String.format(GENERAZIONE_TAMAGOLEM, indiceGiocatore, indiceTamaGolem));
        String nome = TamaGolem.generaNomeTamaGolem(nomi);
        System.out.println(String.format(CREAZIONE_NOME, nome));

        Elementi[] pietreTamagolem = new Elementi[P];
        System.out.println(String.format(ELEMENTI_PIETRE, nome));
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < N; j++) {
                System.out.println(String.format(PIETRE_DISPONIBILI, j + 1, elementi[j], pietreRimaste[j], S_FRATTO_N));
            }
            int indice = OurInputDati.leggiIntero(SCELTA_PIETRE, 1, N);
            while (pietreRimaste[indice - 1] == 0) {
                System.out.println(ERRORE_PIETRE_FINITE);
                indice = OurInputDati.leggiIntero(SCELTA_PIETRE, 1, N);
            }
            pietreRimaste[indice - 1]--;
            pietreTamagolem[i] = Elementi.values()[indice];
        }

        TamaGolem tamaGolem = new TamaGolem(nome, pietreTamagolem);
        return tamaGolem;
    }
}
