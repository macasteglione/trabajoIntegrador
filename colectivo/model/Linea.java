package colectivo.model;

import java.util.ArrayList;
import java.util.List;

public class Linea {
    private String id;
    private List<Parada> paradas;
    private List<Colectivo> colectivos;

    public Linea(String id, List<Parada> paradas, List<Colectivo> colectivos) {
        this.id = id;
        this.paradas = paradas;
        this.colectivos = colectivos;
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

    public List<Colectivo> getColectivos() {
        return colectivos;
    }

    @Override
    public String toString() {
        List<String> paradaIDs = new ArrayList<>();
        for (Parada parada : paradas)
            paradaIDs.add(parada.getId());
        return "Linea [id: " + id + ", Paradas: " + paradaIDs + ", Colectivos: " + colectivos.size() + "]";
    }
}