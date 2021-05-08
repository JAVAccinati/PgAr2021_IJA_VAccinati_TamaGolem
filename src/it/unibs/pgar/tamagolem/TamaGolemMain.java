package it.unibs.pgar.tamagolem;

public class TamaGolemMain {
    //COSTANTI
    public static final int N = Elementi.values().length;

    public static void main(String[] args) {

        int[][] grafo = Setup.generaGrafo();
        //stampaGrafo(grafo);

        boolean eseguiDiNuovo = true;
        do {
            Partita.eseguiPartita(grafo);
            eseguiDiNuovo = Partita.giocaDiNuovo();
        } while (eseguiDiNuovo);


    }

    public static void stampaGrafo(int[][] grafo) {
        int somma = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                somma += grafo[i][j];
                System.out.print(String.format("%4d", grafo[i][j]));
            }
            System.out.println(String.format("%7d", somma));
            somma = 0;
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                somma += grafo[j][i];
            }
            System.out.print(String.format("%4d", somma));
            somma = 0;
        }
    }
}
