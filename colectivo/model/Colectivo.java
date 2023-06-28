package colectivo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Colectivo {
    private String id;
    private int asientos;
    private int totalPasajeros;
    private List<Pasajero> pasajeros;
    private Parada parada;
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

    public List<Pasajero> subirPasajeros(List<Pasajero> pasajeros) {
        List<Pasajero> pasajerosSubidos = new ArrayList<>();
        for (Pasajero pasajero : pasajeros)
            if (totalPasajeros < asientos) {
                pasajerosSubidos.add(pasajero);
                totalPasajeros++;
                if (totalPasajeros == 1)
                    pasajero.setCalificacion(5);
                else if (totalPasajeros <= asientos)
                    pasajero.setCalificacion(4);
            } else
                pasajero.setCalificacion(3);
        return pasajerosSubidos;
    }

    public List<Pasajero> bajarPasajeros(List<Pasajero> pasajeros) {
        List<Pasajero> pasajerosBajados = new ArrayList<>();
        Iterator<Pasajero> iterator = pasajeros.iterator();
        while (iterator.hasNext()) {
            Pasajero pasajero = iterator.next();
            if (pasajero.getColectivo() == this) {
                pasajerosBajados.add(pasajero);
                iterator.remove();
                totalPasajeros--;
            }
        }
        return pasajerosBajados;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String lineaId) {
        this.linea = lineaId;
    }

    @Override
    public String toString() {
        return "Colectivo [id: " + id + "\nAsientos: " + asientos + "\ntotalPasajeros: " + totalPasajeros
                + "\npasajeros: " + pasajeros.size() + ", Linea: " + parada.getLinea().getId() + ", Parada: "
                + parada.getId() + "]";
    }

    public int getAsientosDisponibles() {
        return asientos - totalPasajeros;
    }
}