package colectivo.test;

import java.util.Random;
import java.util.ArrayList;

public class Prueba {

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> prueba = new ArrayList<>();
        int distribucionPasajeros;
        int numPasajeros = 1000;
        int paradas = 64;
        for (int j = 0; j < paradas; j++) {
            distribucionPasajeros = random.nextInt(numPasajeros / paradas);
            numPasajeros -= distribucionPasajeros;
            System.out.println(distribucionPasajeros);
            if (distribucionPasajeros > 0)
                for (int i = 0; i >= distribucionPasajeros; i++)
                    prueba.add(distribucionPasajeros);
        }
        System.out.println(prueba + " " + prueba.size());
    }
}
