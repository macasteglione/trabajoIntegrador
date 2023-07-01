package colectivo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una línea de colectivos.
 * 
 * @author Matias Casteglione
 */
public class Linea {
    private String id;
    private List<Parada> paradas;
    private List<Colectivo> colectivos;

    /**
     * Constructor de la clase Linea.
     * 
     * @param id         el ID de la línea
     * @param paradas    la lista de paradas de la línea
     * @param colectivos la lista de colectivos de la línea
     */
    public Linea(String id, List<Parada> paradas, List<Colectivo> colectivos) {
        this.id = id;
        this.paradas = paradas;
        this.colectivos = colectivos;
    }

    /**
     * Obtiene el ID de la línea.
     * 
     * @return el ID de la línea
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID de la línea.
     * 
     * @param id el ID de la línea a establecer
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene la lista de paradas de la línea.
     * 
     * @return la lista de paradas de la línea
     */
    public List<Parada> getParadas() {
        return paradas;
    }

    /**
     * Obtiene la lista de colectivos de la línea.
     * 
     * @return la lista de colectivos de la línea
     */
    public List<Colectivo> getColectivos() {
        return colectivos;
    }

    @Override
    public String toString() {
        List<String> paradaIDs = new ArrayList<>();
        for (Parada parada : paradas)
            paradaIDs.add(parada.getId());
        return "Linea [id: " + id + ", Paradas: " + paradaIDs + ", Colectivos: " + colectivos.size() + "]\n";
    }
}