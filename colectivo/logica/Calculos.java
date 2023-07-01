package colectivo.logica;

import java.util.List;
import java.util.Random;
import colectivo.model.Linea;
import colectivo.model.Parada;
import colectivo.model.Pasajero;
import colectivo.model.Colectivo;

/**
 * Clase que contiene métodos para realizar cálculos relacionados con el sistema
 * de colectivos.
 * 
 * @author Matias Casteglione
 */
public class Calculos {

    /**
     * Genera pasajeros aleatoriamente y los asigna a las paradas de las líneas.
     *
     * @param lineas         la lista de líneas
     * @param pasajerosTotal el número total de pasajeros a generar
     */
    public void generarPasajeros(List<Linea> lineas, int pasajerosTotal) {
        Random random = new Random();
        for (Linea linea : lineas) {
            for (int i = 0; i < linea.getParadas().size() - 1; i++) {
                Parada parada = linea.getParadas().get(i);
                if (pasajerosTotal <= 0)
                    break;
                int distribucionPasajeros = Math.min(random.nextInt(20), pasajerosTotal);
                pasajerosTotal -= distribucionPasajeros;
                for (int j = 0; j < distribucionPasajeros; j++) {
                    Parada paradaDestino = linea.getParadas()
                            .get(random.nextInt(linea.getParadas().size() - i - 1) + i + 1);
                    Pasajero pasajero = new Pasajero(parada, paradaDestino);
                    parada.getPasajeros().add(pasajero);
                }
            }
        }
    }

    /**
     * Calcula el índice de satisfacción del cliente a partir de las calificaciones
     * recibidas.
     *
     * @param calificaciones la lista de calificaciones de los pasajeros
     */
    public void calcularIndiceSatisfaccion(List<Integer> calificaciones) {
        int[] calificacionesCont = new int[6];
        int totalPasajeros = calificaciones.size();
        double totalPuntos = 0;
        for (int calificacion : calificaciones)
            if (calificacion >= 1 && calificacion <= 5) {
                calificacionesCont[calificacion]++;
                totalPuntos += calificacion;
            }
        double indiceSatisfaccion = totalPuntos / (100 * totalPasajeros);
        System.out.println("Índice de satisfacción del cliente: " + (indiceSatisfaccion * 100) + "%");
        for (int i = 5; i >= 1; i--)
            System.out.println(calificacionesCont[i] + " pasajeros calificaron con " + i + " el servicio");
    }

    /**
     * Calcula la ocupación promedio del colectivo en función de los pasajeros
     * transportados.
     *
     * @param colectivo              el colectivo
     * @param pasajerosTransportados el número de pasajeros transportados
     */
    public void calcularOcupacionPromedio(Colectivo colectivo, int pasajerosTransportados) {
        int totalTramos = colectivo.getLinea().getParadas().size() - 1;
        double promedioOcupacion = (double) pasajerosTransportados
                / (double) (totalTramos * colectivo.getCapacidadMaxima());
        System.out.println("Pasajeros transportados: " + pasajerosTransportados + ". Ocupación promedio: "
                + (promedioOcupacion * 100) + "%");
    }
}