package it.unibs.pgar.tamagolem;

import java.util.ArrayList;

/**
 * Classe di utilita', contiene informazioni invariabili e metodi che sono essenziali lungo tutta
 * l'esecuzione del programma.
 */
public class Utility {

    //COSTANTI

    public static final int N = EnumElementi.values().length; //9
    public static final int P = (int) Math.ceil((N + 1) / 3.0) + 1; //5
    public static final int G = (int) Math.ceil((N - 1) * (N - 2) / (2.0 * P)); //6
    public static final int S = (int) Math.ceil((2.0 * G * P) / N) * N; //7 * 9 = 63 (7 per ogni elemento)
    public static final int S_FRATTO_N = S / N; //7
    public static final int V = 7;

    public static final String[] Elementi = setupElementi();

    //Questi due array non sono costanti, come invece lo e' quello di elementi, ma abbiamo deciso di salvarli qui
    //poiche' sono utili in varie classe diverse.
    private static ArrayList<String> nomi;
    private static int[] pietreRimaste;

    //GETTERS E SETTERS

    public static ArrayList<String> getNomi() {
        return nomi;
    }

    public static void setNomi(ArrayList<String> nomi) {
        Utility.nomi = nomi;
    }

    public static int[] getPietreRimaste() {
        return pietreRimaste;
    }

    public static void setPietreRimaste(int[] pietreRimaste) {
        Utility.pietreRimaste = pietreRimaste;
    }

    //METODI

    /**
     * Inizializza assieme l'arraylist di nomi disponibili per i tamaGolem e l'array delle pietre rimaste a disposizione.
     */
    public static void setup() {
        setNomi(setupNomiTamaGolem());
        setPietreRimaste(setupPietreRimaste());
    }

    /**
     * Accedendo all'enumerazione NomiTamaGolem, inizializza e restituisce un arraylist
     * di stringhe contenente tutti i nomi disponibili.
     *
     * @return nomi: ArrayList String
     */
    public static ArrayList<String> setupNomiTamaGolem() {
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

    /**
     * Accedendo all'enumerazione EnumElementi, inizializza e restituisce
     * un array di stringhe contenente i vari elementi presenti nel gioco.
     *
     * @return elementi: String[]
     */
    public static String[] setupElementi() {
        String[] elementi = new String[N];
        for (int i = 0; i < N; i++) {
            elementi[i] = EnumElementi.values()[i].toString();
        }
        return elementi;
    }

    /**
     * Inizializza e restituisce un array contenente le pietre rimaste di ciascun tipo.
     * L'indice di ogni elemento in EnumElementi determina la posizione di questo all'interno dell'array.
     *
     * @return pietreRimaste: int[]
     */
    public static int[] setupPietreRimaste() {
        int[] pietreRimaste = new int[N];
        for (int i = 0; i < N; i++) {
            pietreRimaste[i] = S_FRATTO_N;
        }
        return pietreRimaste;
    }

}
