package colectivo.app;

import colectivo.logica.Simulacion;
import colectivo.model.*;

import java.io.IOException;
import java.util.List;

import colectivo.data.*;

public class Aplicacion {
    public static void main(String[] args) throws IOException {
        try {
            CargarArchivos.cargar();

            List<Linea> lineas = CargarArchivos.getLineas();
            List<Parada> paradas = CargarArchivos.getParadas();
            List<Colectivo> colectivos = CargarArchivos.getColectivos();

            // Crear la instancia de la simulación y pasar las listas y la configuración
            Simulacion simulacion = new Simulacion(lineas, colectivos);

            // Iniciar la simulación
            simulacion.iniciarSimulacion();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}