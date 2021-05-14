package it.unibs.pgar.tamagolem;

/**
 * Classe enumerazione che contiene i vari elementi.
 * Gli indici assegnati a ciascuno sono utili per accetedere alla
 * matrice dell'equilibrio.
 */
public enum EnumElementi {
    SIMP(0, "SIMP"),
    POGGERS(1, "POGGERS"),
    DISCORD_MOD(2, "DISCORD MOD"),
    SUGAR_DADDY(3, "SUGAR DADDY"),
    E_GIRL(4, "E-GIRL"),
    _69420(5, "69420"),
    STEP_BRO(6, "STEP BRO"),
    GOLD_DIGGER(7, "GOLD DIGGER"),
    KAREN(8, "KAREN");

    //ATTRIBUTI

    private int numeroListaElemento;
    private String nome;

    //COSTRUTTORE

    EnumElementi(int numeroListaElemento, String nome) {
        this.numeroListaElemento = numeroListaElemento;
        this.nome = nome;
    }

    //GETTER AND SETTER

    public int getNumeroListaElemento() {
        return numeroListaElemento;
    }

    public void setNumeroListaElemento(int numeroListaElemento) {
        this.numeroListaElemento = numeroListaElemento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
