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
     */
    public static void main(String[] args) {

        boolean eseguiDiNuovo;
        //Volevamo fare un ciclo infinito perche' non vorrete piu' smettere di giocare,
        //ma visto che siamo generosi vi lasciamo la liberta' di scelta
        do {
            //anche se il grafo fa parte del setup, lo realizziamo in una classe
            //apposita vista la sua importanza
            int[][] grafo = TabellaDanni.generaGrafo();
            Utility.setup();
            eseguiDiNuovo = Partita.eseguiPartita(grafo);
        } while (eseguiDiNuovo);

    }

}
