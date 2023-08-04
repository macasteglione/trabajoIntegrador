package colectivo.ui;

import colectivo.model.Colectivo;
import colectivo.model.Parada;

/**
 * Clase que se encarga de mostrar información relacionada con la simulacion del
 * colectivo.
 * 
 * @author Matias Casteglione
 */
public class MostrarSimulacion {

    /**
     * Muestra los datos del colectivo.
     *
     * @param colectivo  El colectivo del que se mostraran los datos.
     * @param recorridos El numero de recorridos restantes del colectivo.
     */
    public static void mostrarDatosColectivo(Colectivo colectivo, int recorridos) {
        System.out.println("Colectivo ID: " + colectivo.getId());
        System.out.println("Linea: " + colectivo.getLinea().getId());
        System.out.println("Capacidad de pasajeros: " + colectivo.getTotalPasajeros());
        System.out.println("Recorridos restantes: " + recorridos);
        System.out.println("Paradas visitadas:");
    }

    /**
     * Muestra la ocupacion promedio del colectivo.
     *
     * @param pasajerosTransportados El numero total de pasajeros transportados.
     * @param promedioOcupacion      El promedio de ocupacion del colectivo.
     */
    public static void mostrarOcupacionPromedio(int pasajerosTransportados, double promedioOcupacion) {
        System.out.println("Pasajeros transportados: " + pasajerosTransportados + ". Ocupación promedio: "
                + (promedioOcupacion * 100) + "%");
    }

    /**
     * Muestra el indice de satisfaccion del cliente y las calificaciones recibidas.
     *
     * @param indiceSatisfaccion El indice de satisfaccion del cliente.
     * @param calificacionesCont El arreglo de calificaciones recibidas.
     */
    public static void mostrarIndiceSatisfaccion(double indiceSatisfaccion, int[] calificacionesCont) {
        System.out.println("Índice de satisfacción del cliente: " + (indiceSatisfaccion * 100) + "%");
        for (int i = 5; i >= 1; i--)
            System.out.println(calificacionesCont[i] + " pasajeros calificaron con " + i + " el servicio");
    }

    /**
     * Muestra los datos de un recorrido del colectivo.
     *
     * @param colectivo              El colectivo del que se mostraran los datos.
     * @param paradaActual           La parada actual del recorrido.
     * @param totalPasajerosBajaron  El numero total de pasajeros que bajaron en la
     *                               parada actual.
     * @param totalPasajerosSubieron El numero total de pasajeros que subieron en la
     *                               parada actual.
     */
    public static void mostrarRecorridos(Colectivo colectivo, Parada paradaActual, int totalPasajerosBajaron,
            int totalPasajerosSubieron) {
        System.out.println(
                "- Parada ID: " + paradaActual.getId() + ", Dirección: " + paradaActual.getDireccion());
        System.out.println("Asientos disponibles: " + colectivo.getAsientosDisponibles());
        System.out.println("Pasajeros en el colectivo: " + colectivo.getPasajeros().size());
        System.out.println("  Pasajeros que subieron: " + totalPasajerosSubieron);
        System.out.println("  Pasajeros que bajaron: " + totalPasajerosBajaron);
        System.out.println("Pasajeros esperando en la parada: " + paradaActual.getPasajeros().size() + "\n");
    }
}