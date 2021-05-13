package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

public class TabellaDanni {

    //COSTANTI

    public static final int DANNO_MAX = +Utility.V;
    public static final int DANNO_MIN = -Utility.V; //non lo usiamo, ma e' per chiarezza

    //METODI

    /**
     * Il metodo e' particolarmente complesso e gestisce la generazione della matrice dell'equilibrio.
     * Per spiegarlo facciamo riferimento al README.md su consiglio di EnricoBilla.
     * I numeri commentati rappresentano il paragrafo al quale fare riferimento
     * @return grafoGenerato: int[][]
     */
    public static int[][] generaGrafo() {
        int[][] grafo = new int[Utility.N][Utility.N];

        //La generazione termina quando tutti gli elementi sopra la diagonale della matrice
        //sono tutti diversi da 0, il ciclo e' stato eseguito per almeno count volte(inutile, ma piace ad alessio) e
        // almeno uno uguale alla vita massima dei tamaGolem.

        boolean fine = false;
        int count = 50;
        // 1
        do {
            int i, j;
            boolean ripeti;
            do {
                ripeti = false;
                // 2
                i = NumeriCasuali.estraiIntero(0, Utility.N - 2);
                j = NumeriCasuali.estraiIntero(i + 1, Utility.N - 1);
                // 3
                if (!(i == 0 || j == Utility.N - 1)) {
                    int casuale = NumeriCasuali.estraiIntero(0, 3);
                    //se casuale è pari -> triangolo verticale, se casuale è dispari -> triangolo orizzontale,
                    //se casuale = 0 || 1 -> - 1, se casuale = 2 || 3 -> + 1
                    int daAggiungere = casuale > 1 ? +1 : -1;

                    if (casuale % 2 == 0) {
                        if (Math.abs(grafo[i][j] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[0][i] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[0][j] - daAggiungere) <= DANNO_MAX) {
                            grafo[i][j] += daAggiungere;
                            grafo[0][i] += daAggiungere;
                            grafo[0][j] -= daAggiungere;
                        } else {
                            ripeti = true;
                        }
                    } else {
                        if (Math.abs(grafo[i][j] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[i][Utility.N - 1] - daAggiungere) <= DANNO_MAX && Math.abs(grafo[j][Utility.N - 1] + daAggiungere) <= DANNO_MAX) {
                            grafo[i][j] += daAggiungere;
                            grafo[i][Utility.N - 1] -= daAggiungere;
                            grafo[j][Utility.N - 1] += daAggiungere;
                        } else {
                            ripeti = true;
                        }
                    }
                    // 4
                } else if (i == 0 ^ j == Utility.N - 1) {
                    if (i == 0) {
                        int casuale;
                        do {
                            casuale = NumeriCasuali.estraiIntero(1, Utility.N - 1);
                        } while (casuale == j);
                        int daAggiungere = casuale > (Utility.N - 1) / 2 ? +1 : -1;
                        if (casuale > j) {
                            int temp = j;
                            j = casuale;
                            casuale = temp;
                        }
                        if (Math.abs(grafo[casuale][j] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[i][j] - daAggiungere) <= DANNO_MAX && Math.abs(grafo[i][casuale] + daAggiungere) <= DANNO_MAX) {
                            grafo[casuale][j] += daAggiungere;
                            grafo[i][j] -= daAggiungere;
                            grafo[i][casuale] += daAggiungere;
                        } else {
                            ripeti = true;
                        }
                    } else {
                        int casuale;
                        do {
                            casuale = NumeriCasuali.estraiIntero(0, Utility.N - 2);
                        } while (casuale == i);
                        int daAggiungere = casuale > (Utility.N - 1) / 2 ? +1 : -1;
                        if (casuale > i) {
                            int temp = i;
                            i = casuale;
                            casuale = temp;
                        }
                        if (Math.abs(grafo[casuale][i] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[i][j] + daAggiungere) <= DANNO_MAX && Math.abs(grafo[casuale][j] - daAggiungere) <= DANNO_MAX) {
                            grafo[casuale][i] += daAggiungere;
                            grafo[i][j] += daAggiungere;
                            grafo[casuale][j] -= daAggiungere;
                        } else {
                            ripeti = true;
                        }
                    }
                    // L'elemento si trova in alto a destra
                } else {
                    ripeti = true;
                }
            } while (ripeti);

            //controllo limiti della generazione
            count--;
            int nZeri = 0;
            int max = -1;
            for (int k = 0; k < Utility.N - 1; k++) {
                for (int l = k + 1; l < Utility.N; l++) {
                    if (grafo[k][l] == 0)
                        nZeri++;
                    max = Math.max(Math.abs(grafo[k][l]), max);
                }
            }
            if (count <= 0 && nZeri == 0 && max == Utility.V)
                fine = true;
        } while (!fine);

        //generazione della zona inferiore alla diagonale della matrice
        for (int i = 1; i < Utility.N; i++) {
            for (int j = 0; j < i; j++) {
                grafo[i][j] = -grafo[j][i];
            }
        }

        return grafo;
    }

}
