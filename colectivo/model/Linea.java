package colectivo.model;

import java.util.ArrayList;
import java.util.List;

public class Linea extends Colectivo {
    private String id;
    private List<Parada> paradas; // Lista de paradas de la línea

    public Linea(String id, int asientos, int totalPasajeros, List<Pasajero> pasajeros, String id2,
            List<Parada> paradas) {
        super(id, asientos, totalPasajeros, pasajeros);
        this.id = id2;
        this.paradas = paradas;
    }

    public Linea(String id, List<Parada> paradas) {
        this.id = id;
        this.paradas = paradas;
    }

    public Linea() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    @Override
    public String toString() {
        List<String> paradaIDs = new ArrayList<>();
        for (Parada parada : paradas)
            paradaIDs.add(parada.getId()); // Obtiene los IDs de las paradas y los agrega a la lista
        return "Linea [id: " + id + "\nparadas: " + paradaIDs + "]"; // Devuelve una representación en forma de cadena de la línea y sus paradas
    }
}