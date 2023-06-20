package colectivo.logica;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import colectivo.model.*;

public class Simulacion {
    private List<Parada> paradas;
    private List<Linea> lineas;
    private List<Colectivo> colectivos;
    private Properties config;

    public Simulacion(List<Parada> paradas, List<Linea> lineas, List<Colectivo> colectivos, Properties config) {
        this.paradas = paradas;
        this.lineas = lineas;
        this.colectivos = colectivos;
        this.config = config;
    }

    public void iniciarSimulacion() {
        Random random = new Random();

        // Generate passengers at the stops
        generarPasajerosEnParadas();

        // Simulate the movement of the colectivos
        while (true) {
            for (Colectivo colectivo : colectivos) {
                // Get the next stop on the colectivo's line
                List<Parada> paradasLinea = colectivo.getLinea().getParadas();

                if (paradasLinea.isEmpty()) {
                    System.out.println("La línea del colectivo " + colectivo.getId() + " no tiene paradas asignadas.");
                    continue; // Skip to the next iteration of the loop
                }

                int indiceParadaActual = paradasLinea.indexOf(colectivo.getParadaActual());
                int indiceSiguienteParada = (indiceParadaActual + 1) % paradasLinea.size();
                Parada siguienteParada = paradasLinea.get(indiceSiguienteParada);

                // Simulate travel time
                int tiempoViaje = random.nextInt(6) + 5; // Between 5 and 10 minutes
                System.out.println("Colectivo " + colectivo.getId() + " viajando desde "
                        + colectivo.getParadaActual().getDireccion() + " hasta " + siguienteParada.getDireccion()
                        + " (Tiempo estimado: " + tiempoViaje + " minutos)");

                try {
                    Thread.sleep(tiempoViaje * 1000); // Wait for tiempoViaje in seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Disembark passengers at the next stop
                bajarPasajeros(colectivo);

                // Board passengers at the next stop
                subirPasajeros(colectivo);

                // Update the current stop of the colectivo
                colectivo.setParadaActual(siguienteParada);
            }
        }
    }

    private void generarPasajerosEnParadas() {
        Random random = new Random();

        for (Parada parada : paradas) {
            int cantidadPasajeros = random.nextInt(6); // Between 0 and 5 passengers
            System.out.println("Generando " + cantidadPasajeros + " pasajeros en la parada " + parada.getDireccion());
        }
    }

    private void bajarPasajeros(Colectivo colectivo) {
        List<Pasajero> pasajeros = colectivo.getPasajeros();
        int cantidadPasajerosBajados = pasajeros.size();

        pasajeros.clear();

        System.out.println("Colectivo " + colectivo.getId() + " bajando " + cantidadPasajerosBajados + " pasajeros en "
                + colectivo.getParadaActual().getDireccion());
    }

    private void subirPasajeros(Colectivo colectivo) {
        List<Pasajero> pasajeros = colectivo.getPasajeros();
        int capacidadDisponible = colectivo.getCantidadAsientos() - pasajeros.size();

        if (capacidadDisponible > 0) {
            System.out.println("Colectivo " + colectivo.getId() + " subiendo " + capacidadDisponible + " pasajeros en "
                    + colectivo.getParadaActual().getDireccion());
            colectivo.subirPasajeros(capacidadDisponible);
        } else {
            System.out.println("Colectivo " + colectivo.getId() + " no puede subir más pasajeros en "
                    + colectivo.getParadaActual().getDireccion() + " (Capacidad máxima alcanzada)");
        }
    }
}