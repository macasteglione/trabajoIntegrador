package colectivo.model;

import java.util.List;

/**
 * Clase que representa una parada de colectivo.
 * 
 * @author Matias Casteglione
 */
public class Parada {
    private String id;
    private String direccion;
    private Linea linea;
    private List<Pasajero> pasajeros;

    /**
     * Constructor de la clase Parada.
     * 
     * @param id        el ID de la parada
     * @param direccion la dirección de la parada
     * @param linea     la línea a la que pertenece la parada
     * @param pasajeros la lista de pasajeros en la parada
     */
    public Parada(String id, String direccion, Linea linea, List<Pasajero> pasajeros) {
        this.id = id;
        this.direccion = direccion;
        this.linea = linea;
        this.pasajeros = pasajeros;
    }

    /**
     * Obtiene el ID de la parada.
     * 
     * @return el ID de la parada
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene la dirección de la parada.
     * 
     * @return la dirección de la parada
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Obtiene la línea a la que pertenece la parada.
     * 
     * @return la línea a la que pertenece la parada
     */
    public Linea getLinea() {
        return linea;
    }

    /**
     * Obtiene la lista de pasajeros en la parada.
     * 
     * @return la lista de pasajeros en la parada
     */
    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    @Override
    public String toString() {
        return "Parada [id: " + id + ", direccion: " + direccion + ", linea: " + linea.getId() + ", pasajeros: "
                + pasajeros.size() + "]\n";
    }
}