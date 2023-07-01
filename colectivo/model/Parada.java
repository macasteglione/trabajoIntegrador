package colectivo.model;

import java.util.List;

public class Parada {
    private String id;
    private String direccion;
    private Linea linea;
    private List<Pasajero> pasajeros;

    public Parada(String id, String direccion, Linea linea, List<Pasajero> pasajeros) {
        this.id = id;
        this.direccion = direccion;
        this.linea = linea;
        this.pasajeros = pasajeros;
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public Linea getLinea() {
        return linea;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    @Override
    public String toString() {
        return "Parada [id: " + id + ", direccion: " + direccion + ", linea: " + linea.getId() + ", pasajeros: "
                + pasajeros.size() + "]\n";
    }
}