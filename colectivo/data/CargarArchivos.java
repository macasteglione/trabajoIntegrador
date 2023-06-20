package colectivo.data;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import colectivo.model.*;

public class CargarArchivos {
    private static List<Linea> lineas;
    private static List<Parada> paradas;
    private static List<Colectivo> colectivos;
    private static Properties config;
    private static int cantidadPasajeros, numeroRecorridos;

    public static void cargar() throws IOException {
        config = CargarConfig.cargarConfiguracion("./config.properties");
        String rutaLinea = config.getProperty("linea");
        String rutaParada = config.getProperty("parada");
        String rutaColectivo = config.getProperty("colectivo");
        lineas = Datos.cargarLineas(rutaLinea, rutaParada);
        paradas = Datos.cargarParadas(rutaParada);
        colectivos = Datos.cargarColectivos(rutaColectivo);
        cantidadPasajeros = Integer.parseInt(config.getProperty("pasajeros"));
        numeroRecorridos = Integer.parseInt(config.getProperty("recorridos"));
    }

    public static List<Linea> getLineas() {
        return lineas;
    }

    public static List<Parada> getParadas() {
        return paradas;
    }

    public static List<Colectivo> getColectivos() {
        return colectivos;
    }

    public static Properties getConfig() {
        return config;
    }

    public static int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public static int getNumeroRecorridos() {
        return numeroRecorridos;
    }
}