package colectivo.model;

import java.util.ArrayList;
import java.util.List;

public class Linea {
    private String id;
    private List<Parada> paradas;

    public Linea(String id) {
        this.id = id;
        this.paradas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void agregarParada(Parada parada) {
        paradas.add(parada);
    }
}