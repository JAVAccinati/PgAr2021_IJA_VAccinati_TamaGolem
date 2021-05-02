package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

public class Setup3 {

    public static final int N = Elementi.values().length;

    public static final int DANNO_MIN = -10;
    public static final int DANNO_MAX = +10;

    public static int[][] generaGrafo() {
        int[][] grafo = new int[N][N];

        for (int i = 0; i < N - 3; i++) {
            //righe da i + 1 a N - 1
            int somma = sommatoria(grafo[i]);
            while (somma != 0) {
                int j;
                if (somma < 0) {
                    do {
                        j = NumeriCasuali.estraiIntero(i + 1, N - 1);
                    } while (grafo[i][j] == -1 || grafo[i][j] == DANNO_MAX - 1);
                    grafo[i][j]++;
                    somma++;
                } else if (somma > 0) {
                    do {
                        j = NumeriCasuali.estraiIntero(i + 1, N - 1);
                    } while (grafo[i][j] == +1 || grafo[i][j] == DANNO_MIN + 1);
                    grafo[i][j]--;
                    somma--;
                }
            }
            int count = 0;
            do {
                int j;
                do {
                    j = NumeriCasuali.estraiIntero(i + 1, N - 1);
                } while (grafo[i][j] == -1 || grafo[i][j] == DANNO_MAX - 1);
                grafo[i][j]++;
                do {
                    j = NumeriCasuali.estraiIntero(i + 1, N - 1);
                } while (grafo[i][j] == +1 || grafo[i][j] == DANNO_MIN + 1);
                grafo[i][j]--;
                count++;
            } while (count < 20);


            for (int j = i + 1; j < N; j++) {
                grafo[j][i] = -grafo[i][j];
            }


            //colonne da i + 1 a M - i
            /*for (int j = i + 1; j < N - i; j++) {

            }*/
        }
        //terzultima riga
        int sommaTerzultimaRiga = sommatoria(grafo[N - 3]);
        if (sommaTerzultimaRiga < 0) {
            grafo[N - 3][N - 2] = NumeriCasuali.estraiIntero(1, Math.max(-sommaTerzultimaRiga, DANNO_MAX));
            grafo[N - 3][N - 1] = -sommaTerzultimaRiga - grafo[N - 3][N - 2];
        } else if (sommaTerzultimaRiga > 0) {
            grafo[N - 3][N - 2] = NumeriCasuali.estraiIntero(Math.min(-sommaTerzultimaRiga, DANNO_MIN), -1);
            grafo[N - 3][N - 1] = -sommaTerzultimaRiga - grafo[N - 3][N - 2];
        } else {
            grafo[N - 3][N - 2] = NumeriCasuali.estraiIntero(1, DANNO_MAX);
            grafo[N - 3][N - 1] = -grafo[N - 3][N - 2];
        }
        grafo[N - 2][N - 3] = -grafo[N - 3][N - 2];
        grafo[N - 1][N - 3] = -grafo[N - 3][N - 1];

        //penultima riga
        grafo[N - 2][N - 1] = -sommatoria(grafo[N - 2]);
        grafo[N - 1][N - 2] = -grafo[N - 2][N - 1];

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

    public static int sommatoria(int[] riga) {
        int somma = 0;
        for (int i = 0; i < riga.length; i++) {
            somma += riga[i];
        }
        return somma;
    }
}
