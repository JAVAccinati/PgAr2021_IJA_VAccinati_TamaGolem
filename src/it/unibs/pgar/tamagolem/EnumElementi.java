package it.unibs.pgar.tamagolem;

/**
 * Classe enumerazione che contiene i vari elementi.
 * Gli indici assegnati a ciascuno sono utili per accetedere alla
 * matrice dell'equilibrio.
 */
public enum EnumElementi {
    SIMP(0),
    POGGERS(1),
    DISCORD_MOD(2),
    SUGAR_DADDY(3),
    E_GIRL(4),
    _69420(5),
    STEP_BRO(6),
    GOLD_DIGGER(7),
    KAREN(8);

    //ATTRIBUTI

    private int numeroListaElemento;

    //COSTRUTTORE

    EnumElementi(int numeroListaElemento) {
        this.numeroListaElemento = numeroListaElemento;
    }

    //GETTER AND SETTER

    public int getNumeroListaElemento() {
        return numeroListaElemento;
    }

    public void setNumeroListaElemento(int numeroListaElemento) {
        this.numeroListaElemento = numeroListaElemento;
    }

}
