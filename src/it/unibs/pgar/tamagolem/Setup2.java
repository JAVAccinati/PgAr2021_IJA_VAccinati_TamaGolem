package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

public class Setup2 {

    public static final int N = Elementi.values().length;

    public static final int DANNO_MAX = +7;
    public static final int DANNO_MIN = -7;

    public static final int VITA = 7;

    public static int[][] generaGrafo() {
        int[][] grafo = new int[N][N];

        boolean fine = false; //tutti != 0, per almeno count volte e almeno uno == vita
        int count = 50;
        do {
            int i, j;
            boolean ripeti = false;
            do {
                ripeti = false;

                i = NumeriCasuali.estraiIntero(0, N - 2);
                j = NumeriCasuali.estraiIntero(i + 1, N - 1);
                //non sono sui lati dell tabella
                if (!(i == 0 || j == N - 1)) {
                    int casuale = NumeriCasuali.estraiIntero(0, 3);
                    //se casuale è pari -> triangolo verticale, se casuale è dispari -> triangolo orizzontale,
                    //se casuale = 0 || 1 -> - 1, se casuale = 2 || 3 -> + 1
                    int daAggiungere = casuale > 1 ? +1 : -1;

                    if (casuale % 2 == 0) {
                        if(Math.abs(grafo[i][j] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[0][i] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[0][j] - daAggiungere) <= DANNO_MAX) {
                            grafo[i][j] += daAggiungere;
                            grafo[0][i] += daAggiungere;
                            grafo[0][j] -= daAggiungere;
                        } else {
                            ripeti = true;
                        }
                    } else {
                        if(Math.abs(grafo[i][j] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[i][N - 1] - daAggiungere) <= DANNO_MAX && Math.abs(grafo[j][N - 1] + daAggiungere) <= DANNO_MAX) {
                            grafo[i][j] += daAggiungere;
                            grafo[i][N - 1] -= daAggiungere;
                            grafo[j][N - 1] += daAggiungere;
                        } else {
                            ripeti = true;
                        }
                    } //sono sui lati della tabella ma non nell'angolo
                } else if(i == 0 ^ j == N - 1) {
                    if (i == 0) {
                        int casuale;
                        do {
                            casuale = NumeriCasuali.estraiIntero(1, N - 1);
                        }while(casuale == j);
                        int daAggiungere = casuale > (N - 1) / 2 ? +1 : -1;
                        if(casuale > j) {
                            int temp = j;
                            j = casuale;
                            casuale = temp;
                        }
                        if(Math.abs(grafo[casuale][j] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[i][j] - daAggiungere) <= DANNO_MAX && Math.abs(grafo[i][casuale] + daAggiungere) <= DANNO_MAX) {
                            grafo[casuale][j] += daAggiungere;
                            grafo[i][j] -= daAggiungere;
                            grafo[i][casuale] += daAggiungere;
                        } else {
                            ripeti = true;
                        }
                    } else {
                        int casuale;
                        do {
                            casuale = NumeriCasuali.estraiIntero(0, N - 2);
                        }while(casuale == i);
                        int daAggiungere = casuale > (N - 1) / 2 ? +1 : -1;
                        if(casuale > i) {
                            int temp = i;
                            i = casuale;
                            casuale = temp;
                        }
                        if(Math.abs(grafo[casuale][i] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[i][j] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[casuale][j] - daAggiungere) <= DANNO_MAX) {
                            grafo[casuale][i] += daAggiungere;
                            grafo[i][j] += daAggiungere;
                            grafo[casuale][j] -= daAggiungere;
                        } else {
                            ripeti = true;
                        }
                    }
                } else {
                    ripeti = true;
                }
            } while (ripeti);

            count--;
            int nZeri = 0;
            int max = -1;
            for (int k = 0; k < N - 1; k++) {
                for (int l = k + 1; l < N; l++) {
                    if (grafo[k][l] == 0)
                        nZeri++;
                    max = Math.max(Math.abs(grafo[k][l]), max);
                }
            }
            if (count <= 0 && nZeri == 0 && max == VITA)
                fine = true;
        } while (!fine);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                grafo[i][j] = -grafo[j][i];
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

}
