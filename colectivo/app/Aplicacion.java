package colectivo.app;

import colectivo.logica.Simulacion;
import colectivo.model.Colectivo;
import colectivo.model.Linea;
import colectivo.model.Parada;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import colectivo.data.*;

public class Aplicacion {
    public static void main(String[] args) throws IOException {
        try {
            CargarArchivos.cargar();

            List<Linea> lineas = CargarArchivos.getLineas();
            List<Parada> paradas = CargarArchivos.getParadas();
            List<Colectivo> colectivos = CargarArchivos.getColectivos();
            Properties config = CargarArchivos.getConfig();
            int cantidadPasajeros = CargarArchivos.getCantidadPasajeros();
            int numeroRecorridos = CargarArchivos.getNumeroRecorridos();

            // Establecer las propiedades adicionales en la simulación
            for (Colectivo colectivo : colectivos) {
                colectivo.setCantidadPasajeros(cantidadPasajeros);
                colectivo.setNumeroRecorridos(numeroRecorridos);
            }
            // Crear la instancia de la simulación y pasar las listas y la configuración
            Simulacion simulacion = new Simulacion(paradas, lineas, colectivos, config);

            // Iniciar la simulación
            simulacion.iniciarSimulacion();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}