package colectivo.model;

import java.util.ArrayList;
import java.util.Iterator;
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

    public List<Pasajero> subirPasajeros(List<Pasajero> pasajeros) {
        List<Pasajero> pasajerosSubidos = new ArrayList<>();
        for (Pasajero pasajero : pasajeros)
            if (totalPasajeros < asientos) {
                pasajerosSubidos.add(pasajero); // Agrega el pasajero a la lista de pasajeros subidos
                totalPasajeros++; // Incrementa el contador de pasajeros en el colectivo
                if (totalPasajeros == 1)
                    pasajero.setCalificacion(5); // Establece la calificación del pasajero a 5 si es el primer pasajero en subir
                else if (totalPasajeros <= asientos)
                    pasajero.setCalificacion(4); // Establece la calificación del pasajero a 4 si hay asientos disponibles
            } else
                pasajero.setCalificacion(3); // Establece la calificación del pasajero a 3 si no hay asientos disponibles
        return pasajerosSubidos; // Devuelve la lista de pasajeros subidos
    }

    public List<Pasajero> bajarPasajeros(List<Pasajero> pasajeros) {
        List<Pasajero> pasajerosBajados = new ArrayList<>();
        Iterator<Pasajero> iterator = pasajeros.iterator();
        while (iterator.hasNext()) {
            Pasajero pasajero = iterator.next();
            if (pasajero.getColectivo() == this) {
                pasajerosBajados.add(pasajero); // Agrega el pasajero a la lista de pasajeros bajados
                iterator.remove(); // Elimina el pasajero de la lista de pasajeros en el colectivo
                totalPasajeros--; // Decrementa el contador de pasajeros en el colectivo
            }
        }
        return pasajerosBajados; // Devuelve la lista de pasajeros bajados
    }

    @Override
    public String toString() {
        return "Colectivo [id: " + id + "\nAsientos: " + asientos + "\ntotalPasajeros: " + totalPasajeros
                + "\npasajeros: " + pasajeros.size() + ", Linea: " + parada.getLinea().getId() + ", Parada: "
                + parada.getId() + "]"; // Devuelve una representación en forma de cadena del colectivo y su estado
    }
}