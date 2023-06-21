package colectivo.logica;

import colectivo.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Simulacion {
    private List<Linea> lineas;
    private List<Colectivo> colectivos;

    public Simulacion(List<Linea> lineas, List<Colectivo> colectivos) {
        this.lineas = lineas;
        this.colectivos = colectivos;
    }

    public void cargarLinea(Linea linea) {
        lineas.add(linea);
    }

    public void cargarColectivo(Colectivo colectivo) {
        colectivos.add(colectivo);
    }

    public void iniciarSimulacion() {
        for (Colectivo colectivo : colectivos) {
            System.out.println("======================");
            System.out.println("Colectivo: " + colectivo.getId());
            System.out.println("Linea: " + colectivo.getLinea().getId());
            System.out.println("Cantidad de asientos: " + colectivo.getCantidadAsientos());
            System.out.println("Capacidad máxima: " + colectivo.getTotalPasajeros());
            System.out.println("======================");

            List<Parada> recorrido = colectivo.getLinea().getParadas();
            for (Parada parada : recorrido) {
                System.out.println("Parada: " + parada.getId());
                System.out.println("Dirección: " + parada.getDireccion());

                // Bajar pasajeros
                List<Pasajero> pasajerosBajados = new ArrayList<>();
                for (Pasajero pasajero : colectivo.getPasajeros()) {
                    if (pasajero.getDestino().equals(parada)) {
                        pasajerosBajados.add(pasajero);
                    }
                }
                colectivo.eliminarPasajeros(pasajerosBajados);
                System.out.println("Pasajeros bajados: " + pasajerosBajados.size());

                // Subir pasajeros
                List<Pasajero> pasajerosSubidos = new ArrayList<>(parada.getListaPasajeros());
                Iterator<Pasajero> iterator = pasajerosSubidos.iterator();
                int asientosDisponibles = colectivo.getCantidadAsientos() - colectivo.getPasajeros().size();
                int pasajerosSubidosCount = Math.min(asientosDisponibles, pasajerosSubidos.size());
                int subidos = 0;
                while (iterator.hasNext() && subidos < pasajerosSubidosCount) {
                    Pasajero pasajero = iterator.next();
                    colectivo.agregarPasajero(pasajero);
                    iterator.remove();
                    subidos++;
                }
                System.out.println("Pasajeros subidos: " + subidos);

                System.out.println("Cantidad de pasajeros en el colectivo: " + colectivo.getPasajeros().size());
                System.out.println("Cantidad de personas en la parada: " + pasajerosSubidos.size());
                System.out.println();
            }

            colectivo.vaciarPasajeros();
            System.out.println("Recorrido finalizado. Colectivo vaciado.");
            System.out.println();
        }
    }
}