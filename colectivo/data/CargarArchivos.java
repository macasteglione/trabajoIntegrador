package colectivo.data;

import colectivo.logica.Calculos;
import colectivo.model.Colectivo;
import colectivo.model.Linea;
import colectivo.model.Parada;
import colectivo.model.Pasajero;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarArchivos {
    private static List<Colectivo> colectivos;
    private static List<Linea> lineas;
    private static List<Parada> paradas;
    private static List<Pasajero> totalPasajeros = new ArrayList<>();
    private static int recorridos;
    private static int totalPasajerosConfig;

    public static void cargarDatos() {
        Datos system = new Datos();
        Calculos calc = new Calculos();
        try {
            system.cargarConfiguracion("config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        lineas = system.getLineas();
        paradas = system.getParadas();
        colectivos = system.getColectivos();
        recorridos = system.getRecorridos();
        totalPasajerosConfig = system.getTotalPasajeros();
        calc.generarPasajeros(lineas, totalPasajerosConfig);
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

    public static int getTotalPasajerosConfig() {
        return totalPasajerosConfig;
    }

    public static List<Pasajero> getTotalPasajeros() {
        return totalPasajeros;
    }
}