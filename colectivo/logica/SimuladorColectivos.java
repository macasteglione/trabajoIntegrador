package colectivo.logica;

import colectivo.data.CargarArchivos;
import colectivo.model.*;
import java.util.List;
import java.util.Random;

public class SimuladorColectivos {

    public void simularViajes() {
        // Cargar los datos
        CargarArchivos.cargarDatos();

        // Obtener las listas de colectivos, líneas y paradas
        List<Colectivo> colectivos = CargarArchivos.getColectivos();
        List<Linea> lineas = CargarArchivos.getLineas();
        List<Parada> paradas = CargarArchivos.getParadas();
        int recorridos;

        // Iterar sobre los colectivos para simular los viajes individuales
        for (Colectivo colectivo : colectivos) {
            // Generar pasajeros aleatorios en cada parada para el colectivo actual
            generarPasajeros(paradas, colectivo);

            // Obtener la línea correspondiente al colectivo actual
            Linea linea = buscarLineaPorId(lineas, colectivo.getLinea());

            // Obtener el número de recorridos en la línea
            recorridos = linea.getParadas().size();

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
                // Seleccionar una parada aleatoria de la línea
                Random random = new Random();
                int indiceParadaActual = random.nextInt(paradasLinea.size());
                Parada paradaActual = paradasLinea.get(indiceParadaActual);

                // Imprimir información de la parada actual
                System.out.println(
                        "- Parada ID: " + paradaActual.getId() + ", Dirección: " + paradaActual.getDireccion());

                // Obtener los pasajeros de la parada actual
                List<Pasajero> pasajeros = paradaActual.getPasajeros();

                // Realizar la acción de quitar pasajeros del colectivo
                List<Pasajero> pasajerosBajados = quitarPasajeros(colectivo, pasajeros);

                // Realizar la acción de agregar pasajeros al colectivo
                List<Pasajero> pasajerosSubidos = agregarPasajeros(colectivo, paradaActual);

                // Imprimir información sobre los pasajeros que subieron y bajaron
                System.out.println("  Pasajeros que bajaron: " + pasajerosBajados.size());
                System.out.println("  Pasajeros que subieron: " + pasajerosSubidos.size());
                System.out.println(
                        "Pasajeros en la parada: " + (pasajerosBajados.size() - pasajerosSubidos.size()) + "\n");

                // Actualizar el conteo total de pasajeros
                totalPasajerosSubieron += pasajerosSubidos.size();
                totalPasajerosBajaron += pasajerosBajados.size();

                // Reducir el número de recorridos restantes
                recorridos--;
            }

            // Imprimir información final del colectivo
            System.out.println();
            double indiceSatisfaccion = calcularIndiceSatisfaccion(totalPasajerosSubieron, totalPasajerosBajaron);
            System.out.println("Índice de satisfacción del cliente: " + (indiceSatisfaccion * 100) + "%");
            double ocupacionPromedio = calcularOcupacionPromedio(colectivo, linea);
            System.out.println("Ocupación promedio: " + (ocupacionPromedio * 100) + "%");
        }
    }

    private void generarPasajeros(List<Parada> paradas, Colectivo colectivo) {
        Random random = new Random();

        // Generar pasajeros aleatorios en cada parada para el colectivo dado
        for (Parada parada : paradas) {
            int numPasajeros = random.nextInt(colectivo.getTotalPasajeros());
            for (int i = 0; i < numPasajeros; i++) {
                Pasajero pasajero = new Pasajero(parada, colectivo);
                parada.getPasajeros().add(pasajero);
            }
        }
    }

    private List<Pasajero> quitarPasajeros(Colectivo colectivo, List<Pasajero> pasajeros) {
        // Quitar pasajeros del colectivo y obtener la lista de pasajeros bajados
        List<Pasajero> pasajerosBajados = colectivo.bajarPasajeros(pasajeros);
        return pasajerosBajados;
    }

    private List<Pasajero> agregarPasajeros(Colectivo colectivo, Parada parada) {
        // Agregar pasajeros al colectivo desde la parada y obtener la lista de pasajeros subidos
        List<Pasajero> pasajerosSubidos = colectivo.subirPasajeros(parada.getPasajeros());
        return pasajerosSubidos;
    }

    private Linea buscarLineaPorId(List<Linea> lineas, String id) {
        // Buscar y devolver la línea correspondiente al ID dado
        for (Linea linea : lineas)
            if (linea.getId().equals(id))
                return linea;
        return null;
    }

    private double calcularIndiceSatisfaccion(int totalPasajerosSubieron, int totalPasajerosBajaron) {
        double maxCalificacion = 5.0;
        double calificacion5 = 20.0;
        double calificacion4 = 40.0;
        double calificacion3 = 15.0;
        double calificacion2 = 10.0;
        double calificacion1 = 15.0;

        // Calcular el índice de satisfacción del cliente basado en las calificaciones de los pasajeros
        double indiceSatisfaccion = (calificacion5 * 5 + calificacion4 * 4 + calificacion3 * 3 + calificacion2 * 2
                + calificacion1 * 1) / (maxCalificacion * (totalPasajerosSubieron + totalPasajerosBajaron));
        return indiceSatisfaccion;
    }

    private double calcularOcupacionPromedio(Colectivo colectivo, Linea linea) {
        int totalTramos = linea.getParadas().size() - 1;
        int pasajerosTransportados = colectivo.getTotalPasajeros() - colectivo.getAsientosDisponibles();

        // Calcular la ocupación promedio del colectivo en la línea
        double promedioOcupacion = (double) pasajerosTransportados
                / (double) (totalTramos * colectivo.getTotalPasajeros());
        return promedioOcupacion;
    }
}