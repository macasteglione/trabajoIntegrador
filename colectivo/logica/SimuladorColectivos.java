package colectivo.logica;

import colectivo.data.CargarArchivos;
import colectivo.model.*;
import java.util.List;

public class SimuladorColectivos {

    public void simularViajes() {
        // Cargar los datos
        CargarArchivos.cargarDatos();
        Calculos calc = new Calculos();

        // Obtener las listas de colectivos, líneas y paradas
        List<Colectivo> colectivos = CargarArchivos.getColectivos();
        List<Linea> lineas = CargarArchivos.getLineas();
        List<Parada> paradas = CargarArchivos.getParadas();
        int recorridos;

        // Iterar sobre los colectivos para simular los viajes individuales
        for (Colectivo colectivo : colectivos) {
            // Generar pasajeros aleatorios en cada parada para el colectivo actual
            calc.generarPasajeros(paradas, colectivo);

            // Obtener la línea correspondiente al colectivo actual
            Linea linea = calc.buscarLineaPorId(lineas, colectivo.getLinea());

            // Obtener el número de recorridos en la línea
            recorridos = CargarArchivos.getRecorridos();

            // Imprimir información del colectivo y la línea
            System.out.println("Colectivo ID: " + colectivo.getId());
            System.out.println("Linea: " + colectivo.getLinea());
            System.out.println("Asientos disponibles: " + Math.abs(colectivo.getAsientosDisponibles() - recorridos));
            System.out.println("Recorridos restantes: " + recorridos);
            System.out.println("Paradas visitadas:");

            // Obtener la lista de paradas de la línea
            List<Parada> paradasLinea = linea.getParadas();

            // Inicializar variables para el conteo de pasajeros
            int totalPasajerosSubieron = 0;
            int totalPasajerosBajaron = 0;

            // Realizar el recorrido por las paradas de la línea
            while (recorridos > 0) {
                for (int i = 0; i < linea.getParadas().size(); i++) {
                    // Seleccionar una parada aleatoria de la línea
                    Parada paradaActual = paradasLinea.get(i);

                    // Imprimir información de la parada actual
                    System.out.println(
                            "- Parada ID: " + paradaActual.getId() + ", Dirección: " + paradaActual.getDireccion());

                    // Obtener los pasajeros de la parada actual
                    List<Pasajero> pasajeros = paradaActual.getPasajeros();

                    // Realizar la acción de quitar pasajeros del colectivo
                    List<Pasajero> pasajerosBajados = calc.quitarPasajeros(colectivo, pasajeros);

                    // Realizar la acción de agregar pasajeros al colectivo
                    List<Pasajero> pasajerosSubidos = calc.agregarPasajeros(colectivo, paradaActual);

                    // Imprimir información sobre los pasajeros que subieron y bajaron
                    System.out.println("  Pasajeros que bajaron: " + pasajerosBajados.size());
                    System.out.println("  Pasajeros que subieron: " + pasajerosSubidos.size());
                    System.out.println(
                            "Pasajeros en la parada: " + (pasajerosBajados.size() - pasajerosSubidos.size()) + "\n");

                    // Actualizar el conteo total de pasajeros
                    totalPasajerosSubieron += pasajerosSubidos.size();
                    totalPasajerosBajaron += pasajerosBajados.size();

                }
                // Reducir el número de recorridos restantes
                recorridos--;
            }

            // Imprimir información final del colectivo
            System.out.println();
            double indiceSatisfaccion = calc.calcularIndiceSatisfaccion(totalPasajerosSubieron, totalPasajerosBajaron);
            System.out.println("Índice de satisfacción del cliente: " + (indiceSatisfaccion * 100) + "%");
            double ocupacionPromedio = calc.calcularOcupacionPromedio(colectivo, linea);
            System.out.println("Ocupación promedio: " + (ocupacionPromedio * 100) + "%");
        }
    }
}