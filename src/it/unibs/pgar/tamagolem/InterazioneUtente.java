package it.unibs.pgar.tamagolem;

import it.unibs.fp.ourlib.OurInputDati;

import java.util.ArrayList;

/**
 * Questa classe gestisce da sola tutte le interazioni con l'utente.
 */
public class InterazioneUtente {

    //COSTANTI

    public static final String NOME_GIOCATORE = "Inserisci il nome del giocatore %d: ";

    public static final String GENERAZIONE_TAMAGOLEM = "%s, e' ora di evocare il TamaGolem numero %d: ";
    public static final String CREAZIONE_NOME = "Il tuo TamaGolem si chiamera' %s";

    public static final String ELEMENTI_PIETRE = "Scegli le pietre DA DAr DA mangiar a %s: "; //manco il fru fru Tra le fratte di Pascoli
    public static final String PIETRE_DISPONIBILI = "%d) %-15s %s";
    public static final String DISPONIBILE = "\tAvailable!"; //free shipping with JAVAccinatiPrime !!!
    public static final String ESAURITO = "\tOut of stock... ";
    public static final String SCELTA_PIETRE = "Quale pietra vuoi DAr DA mangiar? ";
    public static final String ERRORE_PIETRE_FINITE = "ERRORE! Le pietre di questo tipo sono finite.";

    public static final String INIZIO_SCONTRO = "Inizio dello scontro: ";

    public static final String SCHIERAMENTO = "Vediamo in campo %s per %s e %s per %s!";
    public static final String VITA_TAMAGOLEM = "%s ha %d punti vita %n%s ha %d punti vita";

    public static final String LANCIO_PIETRA = "%s lancia la pietra %s";
    public static final String DANNO_SUBITO = "%s ha subito %d danni...";
    public static final String DANNO_SUBITO_ZERO = "Nessuno ha subito danni questo turno";
    public static final String PREMI_INVIO_PER_CONTINUARE = "Premi invio per continuare ";

    public static final String UGUALE_SET_PIETRE = "%s e %s hanno lo stesso set di pietre, questo perfetto equilibrio li ha mandati entrambi fuori combattimento";

    public static final String PAREGGIO = "Nessun vincitore, l'equilibrio ne e' uscito sovrano!";
    public static final String VINCITORE = "%s si e' rivelato trionfante e riceverà un biscotto da Obama e l'Ucraina da Putin! %s deve solo vergognarsi di se stesso.\n\n" +
            "Il team di %s:";

    public static final String SPIEGAZIONE_GRAFO = " - SCHEMA FINALE, ASSURDO ED INCREDIBILE DELL'EQUILIBRIOOOO -\n\n- LEGENDA -\nSimp -> 1\nPoggers -> 2\nDiscord mod -> 3" +
            "\nSugar Daddy -> 4\nE-girl -> 5\nBasic beach -> 6\nStep bro -> 7\nGold digger -> 8\nKaren -> 9\n";

    public static final String NUOVA_PARTITA = "-GAME OVER- \nVuoi giocare di nuovo? (non vergognarti se ormai sei dipendente, non e' mica eroina)";
    public static final String MSG_GIOCA_ANCORA = "Here we go again you dirty nerd <3";
    public static final String MSG_ADDIO = "Grazie per avere giocato\n- i JA_VAccinati";

    public static final String TAMAGOLEM_VINCITORE = "%d) %s";

    //METODI

    /**
     * Crea un oggetto giocatore dopo aver ricevuto in input il nome desiderato.
     * Ha come argomento l'indice del giocatore che si vuole creare.
     * @param indiceGiocatore: int
     * @return giocatoreCreato: Giocatore
     */
    public static Giocatore creaGiocatore(int indiceGiocatore) {
        String nome = OurInputDati.leggiStringaNonVuota(String.format(NOME_GIOCATORE, indiceGiocatore));

        ArrayList<TamaGolem> team = new ArrayList<>();

        Giocatore giocatore = new Giocatore(nome, team);
        return giocatore;
    }

    /**
     * Crea un oggetto tamaGolem, caratterizzato da un nome (che viene scelto casualmente tra quelli disponibili) e il set di pietre a lui asseganto.
     * I due argomenti passati hanno il solo scopo di rendere piu' chiara l'interazione con l'utente.
     * @param giocatore: Giocatore
     * @param indiceTamaGolem: int
     * @return tamaGolemCreato: TamaGolem
     */
    public static TamaGolem inizializzaTamaGolem(Giocatore giocatore, int indiceTamaGolem) {

        System.out.println(String.format(GENERAZIONE_TAMAGOLEM, giocatore.getNome(), indiceTamaGolem));
        String nome = TamaGolem.generaNomeTamaGolem();
        System.out.println(String.format(CREAZIONE_NOME, nome));

        EnumElementi[] pietreTamagolem = new EnumElementi[Utility.P];
        int[] pietreRimaste = Utility.getPietreRimaste();

        System.out.println(String.format(ELEMENTI_PIETRE, nome));
        for (int i = 0; i < Utility.P; i++) {
            System.out.println();
            for (int j = 0; j < Utility.N; j++) {
                String stato = pietreRimaste[j] > 0 ? DISPONIBILE : ESAURITO;
                System.out.println(String.format(PIETRE_DISPONIBILI, j + 1, Utility.Elementi[j], stato));
            }
            int indice = OurInputDati.leggiIntero(SCELTA_PIETRE, 1, Utility.N);
            while (pietreRimaste[indice - 1] == 0) {
                System.out.println(ERRORE_PIETRE_FINITE);
                indice = OurInputDati.leggiIntero(SCELTA_PIETRE, 1, Utility.N);
            }
            pietreRimaste[indice - 1]--;
            pietreTamagolem[i] = EnumElementi.values()[indice - 1];
        }

        Utility.setPietreRimaste(pietreRimaste);

        TamaGolem tamaGolem = new TamaGolem(nome, pietreTamagolem);
        return tamaGolem;
    }

    /**
     * Metodo utilizzato nascondere i precedenti input immessi da un giocatore.
     * In questo modo i due utenti che si stanno sfidando non possono sbirciare direttamente le scelte del loro avversario.
     */
    public static void nuovaPagina() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Metodo che stampa l'avviso di inizio dello scontro
     */
    public static void inizioScontro() {
        System.out.println(INIZIO_SCONTRO);
        System.out.println();
    }

    /**
     * Accedendo ai dati contenuti nei team dei due giocatori, questo metodo visualizza la situazione sul campo di battaglia:
     * quali tamaGolem si stanno affrontando e quanti punti salute hanno.
     * @param giocatori: Giocatori
     */
    public static void tamaGolemInCampo(Giocatore[] giocatori) {
        TamaGolem tamaGolem1 = giocatori[0].getTeam().get(giocatori[0].getTeam().size() - 1);
        TamaGolem tamaGolem2 = giocatori[1].getTeam().get(giocatori[1].getTeam().size() - 1);

        System.out.println(String.format(SCHIERAMENTO, tamaGolem1.getNome(), giocatori[0].getNome(), tamaGolem2.getNome(), giocatori[1].getNome()));
        visualizzaVita(giocatori);
        System.out.println();
    }

    /**
     * Stampa i punti salute dei due tamaGolem schierati da ciascuno dei giocatori
     * @param giocatori: Giocatori
     */
    public static void visualizzaVita(Giocatore[] giocatori) {
        TamaGolem tamaGolem1 = giocatori[0].getTeam().get(giocatori[0].getTeam().size() - 1);
        TamaGolem tamaGolem2 = giocatori[1].getTeam().get(giocatori[1].getTeam().size() - 1);

        System.out.println(String.format(VITA_TAMAGOLEM, tamaGolem1.getNome(), tamaGolem1.getVita(), tamaGolem2.getNome(), tamaGolem2.getVita()));
        System.out.println();
    }

    /**
     * Nonostante i vari calcoli e modifiche avvengano nel metodo scontro della classe Partita, questo metodo
     * rende partecipe i giocatori di quanto sta accadendo sul campo di battaglia mentre due tamaGolem combattono.
     * Riceve come argomento i due giocatori e il danno che e' stato fatto in quel determinato turno.
     * @param giocatori: Giocatori
     * @param danno: int
     */
    public static void esecuzioneTurno(Giocatore[] giocatori, int danno) {
        TamaGolem tamaGolem1 = giocatori[0].getTeam().get(giocatori[0].getTeam().size() - 1);
        TamaGolem tamaGolem2 = giocatori[1].getTeam().get(giocatori[1].getTeam().size() - 1);

        System.out.println(String.format(LANCIO_PIETRA, tamaGolem1.getNome(), tamaGolem1.getPietre()[0].toString()));
        System.out.println(String.format(LANCIO_PIETRA, tamaGolem2.getNome(), tamaGolem2.getPietre()[0].toString()));
        if (danno > 0) {
            System.out.println(String.format(DANNO_SUBITO, tamaGolem2.getNome(), Math.abs(danno)));
        } else if (danno < 0) {
            System.out.println(String.format(DANNO_SUBITO, tamaGolem1.getNome(), Math.abs(danno)));
        } else {
            System.out.println(DANNO_SUBITO_ZERO);
        }
        System.out.println();
        visualizzaVita(giocatori);
        //Visto che l'interazione con l'utente avviene solamente con una nuova evocazione, per non rendere gli scontri istantanei
        //e sopratutto incomprensibili abbiamo inserito questa sorta di chackpoint.
        String easterEgg = OurInputDati.leggiStringa(PREMI_INVIO_PER_CONTINUARE);
        System.out.println();
        System.out.println();
    }

    /**
     * Metodo che stampa un messaggio specifico nell'eventualita che 2 tamaGolem abbaino lo stesso set di pietre,
     * condizione che porterebbe ad una battaglia infinita.
     * @param tamaGolem1: tamaGolem
     * @param tamaGolem2: tamaGolem
     */
    public static void ugualeSetPietre(TamaGolem tamaGolem1, TamaGolem tamaGolem2) {
        System.out.println(String.format(UGUALE_SET_PIETRE, tamaGolem1.getNome(), tamaGolem2.getNome()));
    }

    /**
     * Visualizza la matrice dell'equilibrio e la relativa legenda per permettere agli utenti di comprenderla
     * piu' facilmente
     * @param grafo
     */
    public static void stampaGrafo(int[][] grafo) {
        System.out.println(SPIEGAZIONE_GRAFO);
        for (int i = 0; i < Utility.N + 1; i++) {
            if (i == 0) {
                System.out.print("   \\");
            } else {
                System.out.print(String.format("%3d)", i));
            }
        }
        for (int i = 0; i < Utility.N; i++) {
            for (int j = 0; j < Utility.N + 1; j++) {
                if (j == 0) {
                    System.out.println();
                    System.out.print(String.format("%3d)", i + 1));
                } else {
                    System.out.print(String.format("%4d", grafo[i][j - 1]));
                }
            }
        }
        System.out.println();
    }

    /**
     * Stampa a console il risultato della battaglia e la matrice dell'equilibrio che ha sancito le
     * sorti dello scontro.
     * Ritorna infine un valore booleano che esprime la volonta' degli utenti di giocare nuovamente o meno.
     * @param grafo: int[][]
     * @param giocatori: Giocatori[][]
     * @param vincitore: int
     * @return giocaAncora: boolean
     */
    public static boolean finePartita(int[][] grafo, Giocatore[] giocatori, int vincitore) {
        switch (vincitore) {
            case 0:
                System.out.println(PAREGGIO);
                break;
            case 1:
                System.out.println(String.format(VINCITORE, giocatori[0].getNome(), giocatori[1].getNome(), giocatori[0].getNome()));
                stampaSquadraVincitrice(giocatori[0]);
                break;
            case 2:
                System.out.println(String.format(VINCITORE, giocatori[1].getNome(), giocatori[0].getNome(), giocatori[1].getNome()));
                stampaSquadraVincitrice(giocatori[1]);
                break;
        }

        stampaGrafo(grafo);

        System.out.println();
        System.out.println();

        boolean nuovaPartita = OurInputDati.yesOrNo(NUOVA_PARTITA);
        if (nuovaPartita) {
            System.out.println(MSG_GIOCA_ANCORA);
            String invio = OurInputDati.leggiStringa(PREMI_INVIO_PER_CONTINUARE);
        } else {
            System.out.println(MSG_ADDIO);
        }
        return nuovaPartita;
    }

    /**
     * Stampa l'intera squadra vincitrice!
     * @param giocatore
     */
    public static void stampaSquadraVincitrice(Giocatore giocatore) {
        ArrayList<TamaGolem> team = giocatore.getTeam();
        for (int i = 0; i < team.size(); i++) {
            System.out.println(String.format(TAMAGOLEM_VINCITORE, i + 1, team.get(i).getNome()));
        }
        System.out.println();
    }

}
