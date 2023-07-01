package colectivo.app;

import colectivo.logica.SimuladorColectivos;

/**
 * Clase principal que inicia la aplicación del simulador de colectivos.
 * 
 * @author Matias Casteglione
 */
public class Aplicacion {
    /**
     * Punto de entrada principal de la aplicación.
     *
     * @param args los argumentos de la línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        SimuladorColectivos simulador = new SimuladorColectivos(); // Crear una instancia del simulador de colectivos
        simulador.simularViajes(); // Llamar al método para simular viajes en el simulador
    }
}