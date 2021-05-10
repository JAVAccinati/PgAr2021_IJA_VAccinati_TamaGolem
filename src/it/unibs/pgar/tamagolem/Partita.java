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

    //METODI
    public static void eseguiPartita(int grafo[][]) {
        //setup
        int indiceGiocatore = 1;
        String[] elementi = setupElementi();
        ArrayList<String> nomi = setupNomiTamaGolem();
        int[] pietreRimaste = setupPietreRimaste();

        //inizializzazione giocatori
        inizializzaGiocatore(giocatori, indiceGiocatore, elementi, nomi, pietreRimaste);
        indiceGiocatore++;
        inizializzaGiocatore(giocatori, indiceGiocatore, elementi, nomi, pietreRimaste);

        //scontro
        scontro(grafo, giocatori);

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

    private static void inizializzaGiocatore(Giocatore[] giocatori, int indiceGiocatore, String[] elementi, ArrayList<String> nomi, int[] pietreRimaste) {
        giocatori[indiceGiocatore] = InterazioneUtente.creaGiocatore(indiceGiocatore + 1);
        int indiceTamaGolem = giocatori[indiceGiocatore - 1].getTeam().size() + 1;
        giocatori[indiceGiocatore].getTeam().add(evocazione(indiceGiocatore, indiceTamaGolem, elementi, nomi, pietreRimaste));
    }

    private static TamaGolem evocazione(int indiceGiocatore, int indiceTamaGolem, String[] elementi, ArrayList<String> nomi, int[] pietreRimaste) {
        TamaGolem tamaGolem = InterazioneUtente.inizializzaTamaGolem(indiceGiocatore, indiceTamaGolem, elementi, nomi, pietreRimaste);
        return tamaGolem;
    }

    private static void scontro(int[][] grafo, Giocatore[] giocatori) {
        boolean finito = false;

        InterazioneUtente.inizioScontro();
        do {
            TamaGolem tamaGolem1 = giocatori[0].getTeam().get(giocatori[0].getTeam().size() - 1);
            TamaGolem tamaGolem2 = giocatori[1].getTeam().get(giocatori[1].getTeam().size() - 1);

            InterazioneUtente.visualizzaCampoBattaglia(tamaGolem1, tamaGolem2);

            int danno = calcoloDanni(grafo, tamaGolem1, tamaGolem2);
            InterazioneUtente.esecuzioneTurno(tamaGolem1, tamaGolem2, danno);
            scalaPietre(tamaGolem1);
            scalaPietre(tamaGolem2);

            if(danno > 0 && !tamaGolem2.getStato()) {
                finito = sostituzioneTamaGolem(giocatori[1]);
            } else if (danno < 0 && !tamaGolem1.getStato()) {
                finito = sostituzioneTamaGolem(giocatori[0]);
            }


        } while (!finito);


    }

    private static void scalaPietre(TamaGolem tamaGolem) {
        Elementi[] pietre = tamaGolem.getPietre();
        Elementi temp = pietre[0];
        for (int i = 0; i < pietre.length - 1; i++) {
            pietre[i] = pietre[i + 1];
        }
        pietre[pietre.length - 1] = temp;
    }

    public static int calcoloDanni(int[][] grafo, TamaGolem tamaGolem1, TamaGolem tamaGolem2) {
        int i = tamaGolem1.getPietre()[0].getNumeroListaElemento();
        int j = tamaGolem2.getPietre()[0].getNumeroListaElemento();
        int danno = grafo[i][j];
        if (danno > 0) {
            tamaGolem2.setVita(tamaGolem2.getVita() - danno);
            if(tamaGolem2.getVita() <= 0){
                tamaGolem2.setStato(false);
            }
        } else {
            tamaGolem1.setVita(tamaGolem1.getVita() - danno);
            if(tamaGolem2.getVita() <= 0){
                tamaGolem2.setStato(false);
            }
        }
        return danno;
    }

    private static boolean sostituzioneTamaGolem(Giocatore giocatore, int indiceGiocatore){
        boolean finito = false;
        TamaGolem tamaGolem = giocatore.getTeam().get(giocatore.getTeam().size() - 1);
        if(tamaGolem.getStato() == false) {
            if(giocatore.getTeam().size() < G) {
                int indiceTamaGolem = giocatore.getTeam().size();
                //TODO SISTEMA argomenti metodi
                TamaGolem tamaGolem = InterazioneUtente.inizializzaTamaGolem(indiceGiocatore, indiceTamaGolem, elementi, nomi, pietreRimaste);
            } else {
                finito = true;
            }
        }
        return finito;
    }

    public static boolean giocaDiNuovo() {
        return false;
    }
}
