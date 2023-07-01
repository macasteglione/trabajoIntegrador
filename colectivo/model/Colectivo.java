package colectivo.model;

import java.util.List;

/**
 * Clase que representa un colectivo.
 * 
 * @author Matias Casteglione
 */
public class Colectivo {
    private String id;
    private int asientos;
    private int capacidadMaxima;
    private List<Pasajero> pasajeros;
    private Linea linea;

    /**
     * Constructor de la clase Colectivo.
     * 
     * @param id              el ID del colectivo
     * @param asientos        el número total de asientos del colectivo
     * @param capacidadMaxima la capacidad máxima de pasajeros del colectivo
     * @param pasajeros       la lista de pasajeros actualmente en el colectivo
     * @param linea           la línea a la que pertenece el colectivo
     */
    public Colectivo(String id, int asientos, int capacidadMaxima, List<Pasajero> pasajeros, Linea linea) {
        this.id = id;
        this.asientos = asientos;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajeros = pasajeros;
        this.linea = linea;
    }

    /**
     * Obtiene el ID del colectivo.
     * 
     * @return el ID del colectivo
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene la capacidad máxima de pasajeros del colectivo.
     * 
     * @return la capacidad máxima de pasajeros del colectivo
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima - pasajeros.size();
    }

    /**
     * Obtiene la lista de pasajeros actualmente en el colectivo.
     * 
     * @return la lista de pasajeros actualmente en el colectivo
     */
    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    /**
     * Establece la lista de pasajeros del colectivo.
     * 
     * @param pasajeros la lista de pasajeros a establecer
     */
    public void setPasajeros(List<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    /**
     * Obtiene la línea a la que pertenece el colectivo.
     * 
     * @return la línea a la que pertenece el colectivo
     */
    public Linea getLinea() {
        return linea;
    }

    /**
     * Establece la línea a la que pertenece el colectivo.
     * 
     * @param linea la línea a establecer
     */
    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    /**
     * Obtiene la cantidad de asientos disponibles en el colectivo.
     * 
     * @return la cantidad de asientos disponibles en el colectivo
     */
    public int getAsientosDisponibles() {
        return asientos - pasajeros.size();
    }

    @Override
    public String toString() {
        return "Colectivo [id: " + id + "\nAsientos: " + asientos + "\ntotalPasajeros: " + capacidadMaxima
                + "\npasajeros: " + pasajeros.size() + ", Linea: " + linea + "]\n";
    }
}