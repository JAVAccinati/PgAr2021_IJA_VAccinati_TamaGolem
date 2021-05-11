package it.unibs.pgar.tamagolem;

public class Partita {

    private static Giocatore[] giocatori = new Giocatore[2];

    //METODI
    public static boolean eseguiPartita(int grafo[][]) {
        //setup
        int indiceGiocatore = 1;
        Utility.setup();

        //inizializzazione giocatori
        inizializzaGiocatore(giocatori, indiceGiocatore);
        indiceGiocatore++;

        inizializzaGiocatore(giocatori, indiceGiocatore);

        //scontro
        boolean ripeti = scontro(grafo, giocatori);

        return ripeti;
    }

    private static void inizializzaGiocatore(Giocatore[] giocatori, int indiceGiocatore) {
        giocatori[indiceGiocatore - 1] = InterazioneUtente.creaGiocatore(indiceGiocatore);
        int indiceTamaGolem = giocatori[indiceGiocatore - 1].getTeam().size() + 1;
        giocatori[indiceGiocatore - 1].getTeam().add(evocazione(giocatori[indiceGiocatore - 1], indiceTamaGolem));
        InterazioneUtente.nuovaPagina();
    }

    private static TamaGolem evocazione(Giocatore giocatore, int indiceTamaGolem) {
        TamaGolem tamaGolem = InterazioneUtente.inizializzaTamaGolem(giocatore, indiceTamaGolem);
        return tamaGolem;
    }

    private static boolean scontro(int[][] grafo, Giocatore[] giocatori) {
        boolean finito = false;
        int pareggiConsecutivi = 0; //Dopo 5 pareggi consecutivi concludiamo che i 2 tamaGolem hanno lo stesso set di pietre

        InterazioneUtente.inizioScontro();
        InterazioneUtente.tamaGolemInCampo(giocatori);

        do {
            TamaGolem tamaGolem1 = giocatori[0].getTeam().get(giocatori[0].getTeam().size() - 1);
            TamaGolem tamaGolem2 = giocatori[1].getTeam().get(giocatori[1].getTeam().size() - 1);

            int danno = calcoloDanni(grafo, tamaGolem1, tamaGolem2);
            if (danno == 0) {
                pareggiConsecutivi++;
            } else {
                pareggiConsecutivi = 0;
            }
            InterazioneUtente.esecuzioneTurno(giocatori, danno);
            scalaPietre(tamaGolem1);
            scalaPietre(tamaGolem2);

            if (danno > 0 && !tamaGolem2.getStato()) {
                finito = sostituzioneTamaGolem(giocatori[1]);
                if (!finito) {
                    InterazioneUtente.tamaGolemInCampo(giocatori);
                }
            } else if (danno < 0 && !tamaGolem1.getStato()) {
                finito = sostituzioneTamaGolem(giocatori[0]);
                if (!finito) {
                    InterazioneUtente.tamaGolemInCampo(giocatori);
                }
            }

            if (pareggiConsecutivi == 5) {
                pareggiConsecutivi = 0;
                InterazioneUtente.ugualeSetPietre(tamaGolem1, tamaGolem2);
                tamaGolem1.setStato(false);
                tamaGolem2.setStato(false);
                boolean finito1 = sostituzioneTamaGolem(giocatori[0]);
                boolean finito2 = sostituzioneTamaGolem(giocatori[1]);
                finito = finito1 || finito2;
                if (!finito) {
                    InterazioneUtente.tamaGolemInCampo(giocatori);
                }
            }

        } while (!finito);

        int vincitore = 0;
        if ((giocatori[0].getTeam().size() == Utility.G && giocatori[0].getTeam().get(Utility.G - 1).getStato() == false)
                && (giocatori[1].getTeam().size() == Utility.G && giocatori[1].getTeam().get(Utility.G - 1).getStato() == false)) {
            vincitore = 0;
        } else if (giocatori[0].getTeam().size() == Utility.G && giocatori[0].getTeam().get(Utility.G - 1).getStato() == false) {
            vincitore = 2;
        } else if (giocatori[1].getTeam().size() == Utility.G && giocatori[1].getTeam().get(Utility.G - 1).getStato() == false) {
            vincitore = 1;
        }
        boolean ripeti = InterazioneUtente.finePartita(grafo, giocatori, vincitore);
        if (ripeti) {
            InterazioneUtente.nuovaPagina();
        }
        return ripeti;
    }

    private static void scalaPietre(TamaGolem tamaGolem) {
        EnumElementi[] pietre = tamaGolem.getPietre();
        EnumElementi temp = pietre[0];
        for (int i = 0; i < pietre.length - 1; i++) {
            pietre[i] = pietre[i + 1];
        }
        pietre[pietre.length - 1] = temp;
    }

    public static int calcoloDanni(int[][] grafo, TamaGolem tamaGolem1, TamaGolem tamaGolem2) {
        int i = tamaGolem1.getPietre()[0].getNumeroListaElemento();
        int j = tamaGolem2.getPietre()[0].getNumeroListaElemento();
        int danno = grafo[i][j];
        if (danno > 0) {
            tamaGolem2.setVita(tamaGolem2.getVita() - Math.abs(danno));
            if (tamaGolem2.getVita() <= 0) {
                tamaGolem2.setStato(false);
            }
        } else {
            tamaGolem1.setVita(tamaGolem1.getVita() - Math.abs(danno));
            if (tamaGolem1.getVita() <= 0) {
                tamaGolem1.setStato(false);
            }
        }
        return danno;
    }

    private static boolean sostituzioneTamaGolem(Giocatore giocatore) {
        boolean finito = false;
        TamaGolem tamaGolem = giocatore.getTeam().get(giocatore.getTeam().size() - 1);
        if (tamaGolem.getStato() == false) {
            if (giocatore.getTeam().size() < Utility.G) {
                int indiceTamaGolem = giocatore.getTeam().size() + 1;
                TamaGolem nuovoTamaGolem = InterazioneUtente.inizializzaTamaGolem(giocatore, indiceTamaGolem);
                giocatore.getTeam().add(nuovoTamaGolem);
                InterazioneUtente.nuovaPagina();
            } else {
                finito = true;
            }
        }
        return finito;
    }

}
