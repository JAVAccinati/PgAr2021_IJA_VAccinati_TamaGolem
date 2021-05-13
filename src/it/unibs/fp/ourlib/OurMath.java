package it.unibs.fp.ourlib;

public class OurMath {
    public static double logBaseData(double argomento, double base){
        return Math.log(argomento) / Math.log(base);
    }

    /**
     * @param numero
     * @return -1 se il numero &egrave negativo, altrimenti il risultato
     */
    public static int fattoriale(int numero){
        int risultato = 1;
        if(numero < 0){
            return -1;
        }else{
            for(int i = 1; i <= numero; i++){
                risultato *= i;
            }
        }
        return risultato;
    }


}
