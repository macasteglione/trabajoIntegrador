package colectivo.logica;

import java.util.List;
import java.util.Random;

import colectivo.data.CargarArchivos;
import colectivo.model.*;

public class Calculos {

    public void generarPasajeros(List<Parada> paradas, Colectivo colectivo) {
        Random random = new Random();

        // Generar pasajeros aleatorios en cada parada para el colectivo dado
        for (Parada parada : paradas) {
            int numPasajeros = random.nextInt(CargarArchivos.getTotalPasajeros());
            for (int i = 0; i < numPasajeros; i++) {
                Pasajero pasajero = new Pasajero(parada, colectivo);
                parada.getPasajeros().add(pasajero);
            }
        }
    }

    public List<Pasajero> quitarPasajeros(Colectivo colectivo, List<Pasajero> pasajeros) {
        // Quitar pasajeros del colectivo y obtener la lista de pasajeros bajados
        List<Pasajero> pasajerosBajados = colectivo.bajarPasajeros(pasajeros);
        return pasajerosBajados;
    }

    public List<Pasajero> agregarPasajeros(Colectivo colectivo, Parada parada) {
        // Agregar pasajeros al colectivo desde la parada y obtener la lista de
        // pasajeros subidos
        List<Pasajero> pasajeros = colectivo.subirPasajeros(parada.getPasajeros());
        return pasajeros;
    }

    public Linea buscarLineaPorId(List<Linea> lineas, String id) {
        // Buscar y devolver la línea correspondiente al ID dado
        for (Linea linea : lineas)
            if (linea.getId().equals(id))
                return linea;
        return null;
    }

    public double calcularIndiceSatisfaccion(int totalPasajerosSubieron, int totalPasajerosBajaron) {
        double maxCalificacion = 5.0;
        double calificacion5 = 20.0;
        double calificacion4 = 40.0;
        double calificacion3 = 15.0;
        double calificacion2 = 10.0;
        double calificacion1 = 15.0;

        // Calcular el índice de satisfacción del cliente basado en las calificaciones
        // de los pasajeros
        double indiceSatisfaccion = (calificacion5 * 5 + calificacion4 * 4 + calificacion3 * 3 + calificacion2 * 2
                + calificacion1 * 1) / (maxCalificacion * (totalPasajerosSubieron + totalPasajerosBajaron));
        return indiceSatisfaccion;
    }

    public double calcularOcupacionPromedio(Colectivo colectivo, Linea linea) {
        int totalTramos = linea.getParadas().size() - 1;
        int pasajerosTransportados = colectivo.getTotalPasajeros() - colectivo.getAsientosDisponibles();

        // Calcular la ocupación promedio del colectivo en la línea
        double promedioOcupacion = (double) pasajerosTransportados
                / (double) (totalTramos * colectivo.getTotalPasajeros());
        return promedioOcupacion;
    }
}
