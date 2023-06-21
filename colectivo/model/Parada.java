package colectivo.model;

import java.util.ArrayList;
import java.util.List;

public class Parada {
    private String id;
    private String direccion;
    private List<Pasajero> listaPasajeros;

    public Parada(String id, String direccion) {
        this.id = id;
        this.direccion = direccion;
        this.listaPasajeros = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Pasajero> getListaPasajeros() {
        return listaPasajeros;
    }

    public void agregarPasajero(Pasajero pasajero) {
        listaPasajeros.add(pasajero);
    }
}