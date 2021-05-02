package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

public class Setup2 {

    public static final int LUNGHEZZA_RIGA = 5;
    public static final int R_MAX = 5;
    public static final int R_MIN = -5;

    public static void main(String[] args) {

        int tabella[][] = new int[LUNGHEZZA_RIGA][LUNGHEZZA_RIGA];

        int S1 = 0;
        int S_max = 0;
        int S_min = 0;
        int i;
        int k;
        int j;


        for (k = 0; k < LUNGHEZZA_RIGA; k++) {

            for (i = 0; i < LUNGHEZZA_RIGA; i++) {
                S1 = 0;

                if (i == k) {
                    tabella[k][i] = 0;
                }


                if (i != k) {
                    do {
                        tabella[k][i] = NumeriCasuali.estraiIntero(R_MIN, R_MAX);

                    } while (tabella[k][i] == 0);
                }

                for (j = 0; j <= i; j++) {
                    S1 = S1 + tabella[k][j];
                }

                S_max = (LUNGHEZZA_RIGA - (i + 1)) * R_MAX;
                S_min = -S_max;
			
	/*		if(i == LUNGHEZZA_RIGA - 1 && S1 != 0)         //controllo se la somma � 0 quando arrivo a fine riga e la cambio se � deiversa da 0
			{
				while(S1 != 0)
				{
					int temp = NumeriCasuali.estraiIntero(0, i);
					while(temp == k)
						temp = NumeriCasuali.estraiIntero(0, i);
					
					while(S1 > 0)
					{
						--tabella[k][temp];
					    --S1;
					    if(tabella[k][temp] == 0)
					    {
					    	--tabella[k][temp];
						    --S1;
					    }
					}
					
					while(S1 < 0)
					{
						++tabella[k][temp];
					    ++S1;
					    if(tabella[k][temp] == 0)
					    {
					    	++tabella[k][temp];
						    ++S1;
					    }
					}
				}
			}
			
			*/


                if (S1 > S_max || S1 < S_min) {

                    int temp = NumeriCasuali.estraiIntero(0, i);
                    //	while(temp == k)
                    //		temp = NumeriCasuali.estraiIntero(0, i);

                    while (S1 > S_max) {
                        while (tabella[k][i] <= R_MIN || temp == k)                 //lo esegue solo se vado troppo in basso
                        {
                            temp = NumeriCasuali.estraiIntero(0, i);
                        }
                        --tabella[k][temp];
                        --S1;
                        if (tabella[k][temp] == 0) {
                            --tabella[k][temp];
                            --S1;
                        }

                    }

                    while (S1 < S_min)                             //lo esegue se vado troppo in alto
                    {
                        while (tabella[k][i] >= R_MAX || temp == k) {
                            temp = NumeriCasuali.estraiIntero(0, i);
                        }
                        ++tabella[k][temp];
                        ++S1;
                        if (tabella[k][temp] == 0) {
                            ++tabella[k][temp];
                            ++S1;
                        }
                    }
                }
                //tabella[i][k] = - tabella[k][i];                        //riempie i valori opposti
            }
        }

        for (int a = 0; a < LUNGHEZZA_RIGA; a++) {
            for (int b = 0; b < LUNGHEZZA_RIGA; b++) {
                System.out.format("%3d", tabella[a][b]);
            }
            System.out.println("\n");
        }

    }

}
