package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

public class Setup {

    public static final int N = Elementi.values().length;

    public static final int DANNO_MAX = 3;
    public static final int DANNO_MIN = -3;

    public static int[][] generaGrafo() {
        int[][] grafo = new int[N][N];
        int[] sommeParziali = new int[N];

        for (int i = 0; i < N; i++) {
            grafo[i][i] = 0;
        }

        for (int i = 1; i < N - 1; i++) {                // sulle righe si trova chi attacca
            for (int j = i + 1; j < N - 1; j++) {        // e sulle colonne chi subisce
                do {
                    grafo[i][j] = NumeriCasuali.estraiIntero(DANNO_MIN, DANNO_MAX);

                } while (grafo[i][j] == 0 /*|| controlloSomma(grafo[i], j)*/);

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

        //inserisco angoli
        grafo[8][0] = calcolaUltimoElemento(grafo[8]);
        grafo[0][8] = -grafo[8][0];

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

    /*public static boolean controlloSomma(int[] riga, int indiceUltimoElementoInserito) {
        int somma = 0;
        for (int i = 0; i <= indiceUltimoElementoInserito; i++) {
            somma = somma + riga[i];
        }
        int sommaMassima = 5 * (N - (indiceUltimoElementoInserito + 1));
        if (somma > Math.abs(sommaMassima))
            return false;
        return true;
    }
    */


}
