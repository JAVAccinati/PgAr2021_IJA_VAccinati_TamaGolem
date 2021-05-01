package it.unibs.pgar.tamagolem;

public enum Elementi {
    SIMP(0),
    POGGERS(1),
    DISCORD_MOD(2),
    SUGAR_DADDY(3),
    E_GIRL(4),
    BASIC_BEACH(5),
    STEP_BRO(6),
    GOLD_DIGGER(7),
    KAREN(8);

    private int numeroElemento;

    public int getNumeroElemento() {
        return numeroElemento;
    }

    public void setNumeroElemento(int numeroElemento) {
        this.numeroElemento = numeroElemento;
    }

    Elementi(int numeroElemento){
        this.numeroElemento = numeroElemento;
    }

}
