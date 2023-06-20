package colectivo.model;

import java.util.ArrayList;
import java.util.List;

public class Linea {
    private String id;
    private List<Parada> paradas;

    public Linea(String id) {
        this.id = id;
        paradas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void agregarParada(Parada parada) {
        paradas.add(parada);
    }

    public void eliminarParada(Parada parada) {
        paradas.remove(parada);
    }
}