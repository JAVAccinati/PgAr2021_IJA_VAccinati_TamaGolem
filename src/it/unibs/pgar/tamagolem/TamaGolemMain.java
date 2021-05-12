package it.unibs.pgar.tamagolem;

/**
 * Come al solito il Main. Detto anche il deserto, detto anche i DM di CallMeRush.
 */
public class TamaGolemMain {

    /**
     * public static void main public static void main public static void main public static void main
     * public static void main public static void main public static void main public static void main
     * public static void main public static void main public static void main public static void main
     * public static void main public static void main public static void main public static void main
     * @param args
     */
    public static void main(String[] args) {

        boolean eseguiDiNuovo = true;
        //Volevamo fare un ciclo infinito perche' non vorrete piu' smettere di giocare,
        //ma visto che siamo generosi vi lasciamo la liberta' di scelta
        do {
            int[][] grafo = TabellaDanni.generaGrafo();
            eseguiDiNuovo = Partita.eseguiPartita(grafo);
        } while (eseguiDiNuovo);

    }

}
