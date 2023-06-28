package colectivo.app;

import colectivo.logica.SimuladorColectivos;

public class Aplicacion {
    public static void main(String[] args) {
        SimuladorColectivos simulador = new SimuladorColectivos();
        simulador.simularViajes();
    }
}