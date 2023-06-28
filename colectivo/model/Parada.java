package colectivo.model;

import java.util.List;

public class Parada {
    private String id;
    private String direccion; // Dirección de la parada
    private Linea linea; // Línea a la que pertenece la parada
    private List<Pasajero> pasajeros; // Lista de pasajeros en la parada

    public Parada() {
    }

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
        return pasajeros; // Devuelve la lista de pasajeros en la parada
    }

    @Override
    public String toString() {
        return "Parada [id: " + id + "\ndireccion: " + direccion + "\nlinea: " + linea + "\npasajeros: "
                + pasajeros.size() + "]";
    }
}