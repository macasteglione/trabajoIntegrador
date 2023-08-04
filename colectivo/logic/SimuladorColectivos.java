package colectivo.logic;

import colectivo.data.CargarArchivos;
import colectivo.model.*;
import colectivo.ui.MostrarSimulacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que simula los viajes de los colectivos y realiza calculos
 * relacionados.
 * 
 * @author Matias Casteglione
 */
public class SimuladorColectivos {

    /**
     * Simula los viajes de los colectivos y realiza los calculos correspondientes.
     */
    public void simularViajes() {
        CargarArchivos.cargarDatos();
        List<Colectivo> colectivos = CargarArchivos.getColectivos();
        List<Integer> calificaciones = new ArrayList<>();
        int pasajerosConfig = CargarArchivos.getTotalPasajerosConfig();
        int pasajerosTransportadosTotal = 0;
        for (Colectivo colectivo : colectivos) {
            int recorridos = CargarArchivos.getRecorridos();
            MostrarSimulacion.mostrarDatosColectivo(colectivo, recorridos);
            int pasajerosTransportados = 0;
            while (recorridos > 0) {
                for (Parada paradaActual : colectivo.getLinea().getParadas()) {
                    int totalPasajerosSubieron = 0, totalPasajerosBajaron = 0;
                    for (Iterator<Pasajero> it = paradaActual.getPasajeros().iterator(); it.hasNext();) {
                        Pasajero pasajeroEnParada = it.next();
                        pasajeroEnParada.setEsperando(pasajeroEnParada.getEsperando() + 1);
                        for (Linea linea : pasajeroEnParada.getParadaDestino().getLineas())
                            if (linea.getId().equals(colectivo.getLinea().getId())
                                    && colectivo.getPasajeros().size() != colectivo.getTotalPasajeros()) {
                                if (pasajeroEnParada.getEsperando() > 2)
                                    calificaciones.add(2);
                                else if (pasajeroEnParada.getEsperando() == 2)
                                    calificaciones.add(3);
                                else if (colectivo.getAsientosDisponibles() <= 0)
                                    calificaciones.add(4);
                                else if (colectivo.getAsientosDisponibles() > 0)
                                    calificaciones.add(5);
                                totalPasajerosSubieron++;
                                colectivo.getPasajeros().add(pasajeroEnParada);
                                it.remove();
                            }
                    }
                    for (Iterator<Pasajero> it = colectivo.getPasajeros().iterator(); it.hasNext();) {
                        Pasajero pasajeroEnColectivo = it.next();
                        if (pasajeroEnColectivo.getParadaDestino().getDireccion().equals(paradaActual.getDireccion())) {
                            totalPasajerosBajaron++;
                            pasajerosTransportados++;
                            it.remove();
                        }
                    }
                    MostrarSimulacion.mostrarRecorridos(colectivo, paradaActual, totalPasajerosBajaron,
                            totalPasajerosSubieron);
                }
                recorridos--;
            }
            pasajerosTransportadosTotal += pasajerosTransportados;
            Calculos.calcularOcupacionPromedio(colectivo, pasajerosTransportados);
        }
        for (int i = 0; i < pasajerosConfig - pasajerosTransportadosTotal; i++)
            calificaciones.add(1);
        Calculos.calcularIndiceSatisfaccion(calificaciones);
    }
}