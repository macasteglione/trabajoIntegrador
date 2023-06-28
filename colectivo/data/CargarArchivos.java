package colectivo.data;

import colectivo.model.*;
import java.io.IOException;
import java.util.List;

public class CargarArchivos {

    private static List<Colectivo> colectivos;
    private static List<Linea> lineas;
    private static List<Parada> paradas;
    private static int recorridos;
    private static int getTotalPasajeros;

    public static void cargarDatos() {
        Datos system = new Datos();
        try {
            system.cargarConfiguracion("config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        colectivos = system.getColectivos();
        lineas = system.getLineas();
        paradas = system.getParadas();
        recorridos = system.getRecorridos();
        getTotalPasajeros = system.getTotalPasajeros();
        lineas.removeIf(linea -> linea.getParadas().isEmpty());
    }

    public static List<Colectivo> getColectivos() {
        return colectivos;
    }

    public static List<Linea> getLineas() {
        return lineas;
    }

    public static List<Parada> getParadas() {
        return paradas;
    }

    public static int getRecorridos() {
        return recorridos;
    }

    public static int getTotalPasajeros() {
        return getTotalPasajeros;
    }
}