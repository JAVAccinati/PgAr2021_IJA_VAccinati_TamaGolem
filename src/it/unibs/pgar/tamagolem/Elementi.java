package it.unibs.pgar.tamagolem;

import it.unibs.fp.mylib.NumeriCasuali;

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

    private int numeroListaElemento;
    private int indiceElemento;

    public int getNumeroListaElemento() {
        return numeroListaElemento;
    }

    public void setNumeroListaElemento(int numeroListaElemento) {
        this.numeroListaElemento = numeroListaElemento;
    }

    Elementi(int numeroListaElemento){
        this.numeroListaElemento = numeroListaElemento;
        indiceElemento = NumeriCasuali.estraiIntero(0, 9);
    }

}
