package colectivo.model;

import java.util.List;

public class Colectivo {
    private String id;
    private int asientos;
    private int capacidadMaxima; // Número total de pasajeros en el colectivo
    private List<Pasajero> pasajeros; // Lista de pasajeros en el colectivo
    private Linea linea;

    public Colectivo(String id, int asientos, int capacidadMaxima, List<Pasajero> pasajeros, Linea linea) {
        this.id = id;
        this.asientos = asientos;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajeros = pasajeros;
        this.linea = linea;
    }

    public String getId() {
        return id;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima - pasajeros.size();
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(List<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public int getAsientosDisponibles() {
        return asientos - pasajeros.size();
    }

    @Override
    public String toString() {
        return "Colectivo [id: " + id + "\nAsientos: " + asientos + "\ntotalPasajeros: " + capacidadMaxima
                + "\npasajeros: " + pasajeros.size() + ", Linea: " + linea + "]"; // Devuelve una representación en forma de cadena del colectivo y su estado
    }
}