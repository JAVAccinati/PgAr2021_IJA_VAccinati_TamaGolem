package it.unibs.pgar.tamagolem;

public class TamaGolemMain {

    public static void main(String[] args) {

        int[][] grafo = TabellaDanni.generaGrafo();

        boolean eseguiDiNuovo = true;
        do {

            eseguiDiNuovo = Partita.eseguiPartita(grafo);
        } while (eseguiDiNuovo);

    }

}
