package it.unibs.pgar.tamagolem;

public class TamaGolemMain {

    public static final int N = Elementi.values().length;

    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            count += Setup.generaGrafo();
            /*int[][] grafo = Setup.generaGrafo();*/
        }
        System.out.println(count);
    }
}
