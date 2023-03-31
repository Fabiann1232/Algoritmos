import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class agente {

    private int numCiudades;
    private ArrayList<Integer> ruta;
    private int[][] distancias;

    public agente(int numCiudades, int[][] distancias) {
        this.numCiudades = numCiudades;
        this.ruta = new ArrayList<Integer>();
        this.distancias = distancias;
        for (int i = 0; i < numCiudades; i++) {
            ruta.add(i);
        }
        Collections.shuffle(ruta);
    }

    public int getCosto() {
        int costo = 0;
        for (int i = 0; i < numCiudades; i++) {
            int ciudadActual = ruta.get(i);
            int ciudadSiguiente = ruta.get((i + 1) % numCiudades);
            costo += distancias[ciudadActual][ciudadSiguiente];
        }
        return costo;
    }

    public void recocidoSimulado(double temperaturaInicial, double temperaturaFinal, int iteraciones) {
        Random rand = new Random();
        double temperatura = temperaturaInicial;
        agente mejorRuta = new agente(numCiudades, distancias);
        int costoActual = getCosto();
        mejorRuta.ruta = new ArrayList<Integer>(ruta);
        int mejorCosto = costoActual;

        for (int i = 0; i < iteraciones; i++) {
            int ciudad1 = rand.nextInt(numCiudades);
            int ciudad2 = rand.nextInt(numCiudades);
            Collections.swap(ruta, ciudad1, ciudad2);
            int nuevoCosto = getCosto();
            int deltaCosto = nuevoCosto - costoActual;
            if (deltaCosto < 0) {
                costoActual = nuevoCosto;
                if (costoActual < mejorCosto) {
                    mejorCosto = costoActual;
                    mejorRuta.ruta = new ArrayList<Integer>(ruta);
                }
            } else {
                double probabilidad = Math.exp(-deltaCosto / temperatura);
                double r = rand.nextDouble();
                if (r < probabilidad) {
                    costoActual = nuevoCosto;
                } else {
                    Collections.swap(ruta, ciudad1, ciudad2);
                }
            }
            temperatura = temperaturaInicial
                    * Math.pow((temperaturaFinal / temperaturaInicial), ((double) i / (double) iteraciones));
        }
        ruta = mejorRuta.ruta;
    }

    public void imprimirRuta() {
        for (int i = 0; i < numCiudades; i++) {
            System.out.print(ruta.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int numCiudades = 5;
        int[][] distancias = { { 0, 10, 15, 20, 30 },
                { 10, 0, 35, 25, 20 },
                { 15, 35, 0, 30, 10 },
                { 20, 30, 20, 30, 0 } };

        agente agenteViajero = new agente(numCiudades, distancias);
        System.out.println("Ruta inicial:");
        agenteViajero.imprimirRuta();

        double temperaturaInicial = 1000;
        double temperaturaFinal = 0.1;
        int iteraciones = 1000;

        agenteViajero.recocidoSimulado(temperaturaInicial, temperaturaFinal, iteraciones);

        System.out.println("Ruta final:");
        agenteViajero.imprimirRuta();
        System.out.println("Costo final: " + agenteViajero.getCosto());
    }
}
