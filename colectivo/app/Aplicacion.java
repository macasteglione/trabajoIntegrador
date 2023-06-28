package colectivo.app;

import colectivo.logica.SimuladorColectivos;

public class Aplicacion {
    public static void main(String[] args) {
        SimuladorColectivos simulador = new SimuladorColectivos(); // Crear una instancia del simulador de colectivos
        simulador.simularViajes(); // Llamar al método para simular viajes en el simulador
    }
}