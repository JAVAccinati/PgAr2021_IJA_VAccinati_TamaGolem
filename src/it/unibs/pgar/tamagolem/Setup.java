package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

public class Setup {

    public static final int N = Elementi.values().length;

    public static final int DANNO_MAX = 3;
    public static final int DANNO_MIN = -3;

    public static int[][] generaGrafo() {
        int[][] grafo = new int[N][N];

        for (int i = 0; i < N; i++) {
            grafo[i][i] = 0;
        }

        for (int i = 1; i < N - 1; i++) {                // sulle righe si trova chi attacca
            for (int j = i + 1; j < N - 1; j++) {        // e sulle colonne chi subisce
                do {
                    grafo[i][j] = NumeriCasuali.estraiIntero(DANNO_MIN, DANNO_MAX);

                } while (grafo[i][j] == 0);

                grafo[j][i] = -grafo[i][j];
            }

        }

        for (int i = 1; i < N - 1; i++) {
            do {
                grafo[0][i] = NumeriCasuali.estraiIntero(DANNO_MIN, DANNO_MAX);

            } while (grafo[0][i] == 0 /*&& controlloSomma(grafo[0], i)*/);

            grafo[i][0] = -grafo[0][i];
        }

        //Inserisco ultima riga/colonna
        for (int i = 1; i < N - 1; i++) {
            grafo[i][8] = calcolaUltimoElemento(grafo[i]);
            grafo[8][i] = -grafo[i][8];
        }

        //no zeri nella prima e ultima colonna (no controllo angoli)
        //guardando la foto che vi ho mandato si vede come gli elementi
        //della prima riga siano collegati con quelli dell'ultima colonna
        //attraverso l'angolo in alto a dx.
        //Sfrutto questo collegamento per poter modificare "liberamente"
        //i valori uguali a zero nell'ultima colonna
        for (int i = 1; i < N - 1; i++) {
            if (grafo[i][8] == 0) {
                if (grafo[0][i] != 1) {
                    grafo[i][8]--;
                    grafo[8][i] = -grafo[i][8];
                    grafo[0][i]--;
                    grafo[i][0] = -grafo[0][i];
                } else {
                    grafo[i][8]++;
                    grafo[8][i] = -grafo[i][8];
                    grafo[0][i]++;
                    grafo[i][0] = -grafo[0][i];
                }

            }
        }

        //inserisco angoli
        grafo[8][0] = calcolaUltimoElemento(grafo[8]);
        grafo[0][8] = -grafo[8][0];

        //controllo angoli diversi da zero
        //in maniera del tutto analogo sfrutto i collegamenti tra i
        //vertici di questi triangoli
        if (grafo[0][8] == 0) {
            for (int i = 1; i < N - 1; i++) {
                if (grafo[0][i] != -1 && grafo[i][8] != -1) {
                    grafo[0][8]--;
                    grafo[8][0] = -grafo[0][8];
                    grafo[0][i]++;
                    grafo[i][0] = -grafo[0][i];
                    grafo[i][8]++;
                    grafo[8][i] = -grafo[i][8];
                    break;
                }

            }

        }

        //stampa
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

    private static int calcolaUltimoElemento(int[] riga) {
        int inversoNumero = 0;
        for (int i = 0; i < N; i++) {
            inversoNumero += riga[i];
        }
        return -inversoNumero;
    }



}
