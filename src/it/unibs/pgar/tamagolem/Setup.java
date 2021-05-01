package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

public class Setup {

    public static final int N = Elementi.values().length;

    public static final int DANNO_MIN = -5;
    public static final int DANNO_MAX = 5;

    public static int[][] generaGrafo() {
        int[][] grafo = new int[N][N];

        for (int i = 0; i < N; i++) {
            grafo[i][i] = 0;
        }

        for (int i = 0; i < N; i++) {                // sulle righe si trova chi attacca
            for (int j = i + 1; j < N; j++) {        // e sulle colonne chi subisce
                do {
                    grafo[i][j] = NumeriCasuali.estraiIntero(DANNO_MIN, DANNO_MAX);
                } while (grafo[i][j] == 0);
            }
            for (int j = i + 1; j < N; j++) {
                grafo[j][i] = -grafo[i][j];
            }
        }

        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                grafo[i][j] = -grafo[j][i];
            }
        }*/

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

        return grafo;
    }

}
