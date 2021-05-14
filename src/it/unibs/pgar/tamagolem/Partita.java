package it.unibs.pgar.tamagolem;

/**
 * Questa classe gestisce tutte le varie fase della partita.
 * Si appoggia pesantemente sulla classe InterazioneUtente in quanto i vari avvenimenti che qui
 * vengono calcolati, sono mostrati all'utente grazie alla suddetta classe.
 */
public class Partita {

    private static Giocatore[] giocatori = new Giocatore[2];

    //METODI

    /**
     * Questo metodo permette facilmente di eseguire la partita piu' volte.
     * Il valore booleano ritornato rappresenta infatti la volonta' dell'utente di giocare nuovamente o meno.
     * Al suo interno si può vedere chiaramente la suddivisione nelle varie fasi della partita stessa.
     *
     * @param grafo: int[][]
     * @return giocaDiNuovo: boolean
     */
    public static boolean eseguiPartita(int[][] grafo) {

        //inizializzazione giocatori
        int indiceGiocatore = 1;
        inizializzaGiocatore(giocatori, indiceGiocatore, "");
        indiceGiocatore++;
        inizializzaGiocatore(giocatori, indiceGiocatore, giocatori[0].getNome());

        //scontro
        boolean ripeti = scontro(grafo, giocatori);

        return ripeti;
    }

    /**
     * Crea un oggetto giocatore e lo salva, grazie all'indice(in verita' indice - 1 visto che le persone contano da 1 come i tardi), in un array
     * di giocatori per permette di passare nei metodi futuri entrambi i giocatori piu' facilemente.
     *
     * @param giocatori:       Giocatori[]
     * @param indiceGiocatore: int
     */
    private static void inizializzaGiocatore(Giocatore[] giocatori, int indiceGiocatore, String nomeEsistente) {
        giocatori[indiceGiocatore - 1] = InterazioneUtente.creaGiocatore(indiceGiocatore, nomeEsistente);
        int indiceTamaGolem = giocatori[indiceGiocatore - 1].getTeam().size() + 1;
        giocatori[indiceGiocatore - 1].getTeam().add(evocazione(giocatori[indiceGiocatore - 1], indiceTamaGolem));
        InterazioneUtente.nuovaPagina();
    }

    /**
     * Questo metodo esiste soprattutto per una questione di chiarezza del codice.
     * Evoca un tamaGolem sfruttando un metodo di InterazioneUtente e lo salva nel team del giocatore all'indice selezionato.
     *
     * @param giocatore:       Giocatore
     * @param indiceTamaGolem: int
     * @return tamaGolemCreato: TamaGolem
     */
    private static TamaGolem evocazione(Giocatore giocatore, int indiceTamaGolem) {
        return InterazioneUtente.inizializzaTamaGolem(giocatore, indiceTamaGolem);
    }

    /**
     * Si occupa della parte corposa della partita: il combattimento, la sostituzione dei tamaGolem e l'eventuale constatazione di un vincitore.
     * I vari momenti sono gestiti da metodi specializzati.
     *
     * @param grafo:     int[]
     * @param giocatori: Giocatori[]
     * @return giocaDiNuovo: boolean
     */
    private static boolean scontro(int[][] grafo, Giocatore[] giocatori) {
        boolean finito = false;
        int pareggiConsecutivi = 0; //Dopo 5 pareggi consecutivi concludiamo che i 2 tamaGolem hanno lo stesso set di pietre.
        //In tal caso entrambi vengono mandati KO e, se entrambi i giocatori possono evocare un nuovo tamaGolem, si prosegue lo scontro.

        InterazioneUtente.inizioScontro();
        InterazioneUtente.tamaGolemInCampo(giocatori);

        do {
            TamaGolem tamaGolem1 = giocatori[0].getTeam().get(giocatori[0].getTeam().size() - 1);
            TamaGolem tamaGolem2 = giocatori[1].getTeam().get(giocatori[1].getTeam().size() - 1);

            //Fase di attacco
            int danno = calcoloDanni(grafo, tamaGolem1, tamaGolem2);
            if (danno == 0) {
                pareggiConsecutivi++;
            } else {
                pareggiConsecutivi = 0;
            }
            InterazioneUtente.esecuzioneTurno(giocatori, danno);
            scalaPietre(tamaGolem1);
            scalaPietre(tamaGolem2);

            //Fase di sostituzione
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

            //Caso uguale set pietre
            if (pareggiConsecutivi == 5) {
                pareggiConsecutivi = 0;
                InterazioneUtente.ugualeSetPietre(tamaGolem1, tamaGolem2);
                tamaGolem1.setStato(false);
                tamaGolem2.setStato(false);
                boolean finito1 = sostituzioneTamaGolem(giocatori[0]);
                boolean finito2 = sostituzioneTamaGolem(giocatori[1]);
                //basta che uno solo dei due giocatori abbia esaurito i tamaGolem e la partita finisce
                finito = finito1 || finito2;
                if (!finito) {
                    InterazioneUtente.tamaGolemInCampo(giocatori);
                }
            }

        } while (!finito);

        //Calcolo vincitore
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

    /**
     * Questo array fa scalare la pietra lanciata in fondo all'array in dotazione a ciascun tamaGolem per prepararlo al prossimo
     * turno di attacco
     *
     * @param tamaGolem: TamaGolem
     */
    private static void scalaPietre(TamaGolem tamaGolem) {
        EnumElementi[] pietre = tamaGolem.getPietre();
        EnumElementi temp = pietre[0];
        for (int i = 0; i < pietre.length - 1; i++) {
            pietre[i] = pietre[i + 1];
        }
        pietre[pietre.length - 1] = temp;
    }

    /**
     * Accedendo alla matrice dell'equilibrio viene calcolato ed inflitto il danno a corretto tamaGolem.
     * Il danno fatto viene ritornato per poter essere stampato da un altro metodo.
     *
     * @param grafo:      int[][]
     * @param tamaGolem1: TamaGolem
     * @param tamaGolem2: TamaGolem
     * @return dannoFatto: int
     */
    private static int calcoloDanni(int[][] grafo, TamaGolem tamaGolem1, TamaGolem tamaGolem2) {
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

    /**
     * Metodo che entra in gioco quando un tamaGolem va KO.
     * Qualora il giocatore che sta effetuando la sostituzione ha ancora spazio in team gli permette di evocare un nuovo Tamagolem e ritorna false
     * In caso contrario la partita deve essere terminata e il metodo ritorna true.
     *
     * @param giocatore: Giocatore
     * @return partitaFinita: booleanGvoi
     */
    private static boolean sostituzioneTamaGolem(Giocatore giocatore) {
        boolean finito = false;
        TamaGolem tamaGolem = giocatore.getTeam().get(giocatore.getTeam().size() - 1);
        if (!tamaGolem.getStato()) {
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
