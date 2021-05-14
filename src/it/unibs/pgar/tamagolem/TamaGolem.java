package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

import java.util.ArrayList;

/**
 * Classe che descrive l'oggetto tamaGolem e i metodi ad esso associati
 */
public class TamaGolem {

    //ATTRIBUTI

    private String nome;
    private int vita;
    private EnumElementi[] pietre;
    private boolean stato; //vivo (true) o troppo stanco per combattere (false) {perche' i nostri tamagolem non muoiono mai grazie del potere dell'amicizia}

    //COSTRUTTORE

    public TamaGolem(String nome, EnumElementi[] pietre) {
        this.nome = nome;
        this.vita = Utility.V;
        this.pietre = pietre;
        this.stato = true;
    }

    //GETTERS AND SETTERS

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public EnumElementi[] getPietre() {
        return pietre;
    }

    public void setPietre(EnumElementi[] pietre) {
        this.pietre = pietre;
    }

    public boolean getStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public String getNome() {
        return nome;
    }

    //METODI

    /**
     * Visto che ci teniamo all'esperienza ludica dell'utente generiamo casualmente un nome
     * per ogni tenero tamaGolem evocato. I nomi provengono dal file NomiTamaGolem.
     * <p>
     * P.S. i nomi li scegliamo noi perche' abbiamo deciso un tema
     *
     * @return nomeTamaGolem: String
     */
    public static String generaNomeTamaGolem() {
        ArrayList<String> nomi = Utility.getNomi();
        int indice = NumeriCasuali.estraiIntero(0, nomi.size() - 1);
        String nome = nomi.get(indice);
        nomi.remove(indice);
        Utility.setNomi(nomi);
        return nome;
    }

}
