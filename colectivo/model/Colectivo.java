package colectivo.model;

import java.util.List;

public class Colectivo {
    private String id;
    private int asientos;
    private int totalPasajeros; // Número total de pasajeros en el colectivo
    private List<Pasajero> pasajeros; // Lista de pasajeros en el colectivo
    private Parada parada; // Parada actual del colectivo
    private String linea;

    public Colectivo(String id, int asientos, int totalPasajeros, List<Pasajero> pasajeros) {
        this.id = id;
        this.asientos = asientos;
        this.totalPasajeros = totalPasajeros;
        this.pasajeros = pasajeros;
    }

    public Colectivo() {
    }

    public String getId() {
        return id;
    }

    public int getTotalPasajeros() {
        return totalPasajeros;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(List<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String lineaId) {
        this.linea = lineaId;
    }

    public int getAsientosDisponibles() {
        return asientos - totalPasajeros;
    }

    @Override
    public String toString() {
        return "Colectivo [id: " + id + "\nAsientos: " + asientos + "\ntotalPasajeros: " + totalPasajeros
                + "\npasajeros: " + pasajeros.size() + ", Linea: " + parada.getLinea().getId() + ", Parada: "
                + parada.getId() + "]"; // Devuelve una representación en forma de cadena del colectivo y su estado
    }
}