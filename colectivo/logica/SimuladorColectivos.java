package colectivo.logica;

import colectivo.data.CargarArchivos;
import colectivo.model.*;
import java.util.List;
import java.util.Random;

public class SimuladorColectivos {

    public void simularViajes() {
        CargarArchivos.cargarDatos();
        List<Colectivo> colectivos = CargarArchivos.getColectivos();
        List<Linea> lineas = CargarArchivos.getLineas();
        List<Parada> paradas = CargarArchivos.getParadas();
        int recorridos;
        for (Colectivo colectivo : colectivos) {
            generarPasajeros(paradas, colectivo);
            Linea linea = buscarLineaPorId(lineas, colectivo.getLinea());
            recorridos = linea.getParadas().size();
            System.out.println("Colectivo ID: " + colectivo.getId());
            System.out.println("Linea: " + colectivo.getLinea());
            System.out.println("Asientos disponibles: " + Math.abs(colectivo.getAsientosDisponibles() - recorridos));
            System.out.println("Recorridos restantes: " + recorridos);
            System.out.println("Paradas visitadas:");
            List<Parada> paradasLinea = linea.getParadas();
            Random random = new Random();
            int totalPasajerosSubieron = 0;
            int totalPasajerosBajaron = 0;
            while (recorridos > 0) {
                int indiceParadaActual = random.nextInt(paradasLinea.size());
                Parada paradaActual = paradasLinea.get(indiceParadaActual);
                System.out.println(
                        "- Parada ID: " + paradaActual.getId() + ", Dirección: " + paradaActual.getDireccion());
                List<Pasajero> pasajeros = paradaActual.getPasajeros();
                List<Pasajero> pasajerosBajados = quitarPasajeros(colectivo, pasajeros);
                List<Pasajero> pasajerosSubidos = agregarPasajeros(colectivo, paradaActual);
                System.out.println("  Pasajeros que bajaron: " + pasajerosBajados.size());
                System.out.println("  Pasajeros que subieron: " + pasajerosSubidos.size());
                System.out.println(
                        "Pasajeros en la parada: " + (pasajerosBajados.size() - pasajerosSubidos.size()) + "\n");
                totalPasajerosSubieron += pasajerosSubidos.size();
                totalPasajerosBajaron += pasajerosBajados.size();
                recorridos--;
            }
            System.out.println();
            double indiceSatisfaccion = calcularIndiceSatisfaccion(totalPasajerosSubieron, totalPasajerosBajaron);
            System.out.println("Índice de satisfacción del cliente: " + (indiceSatisfaccion * 100) + "%");
            double ocupacionPromedio = calcularOcupacionPromedio(colectivo, linea);
            System.out.println("Ocupación promedio: " + (ocupacionPromedio * 100) + "%");
        }
    }

    private void generarPasajeros(List<Parada> paradas, Colectivo colectivo) {
        Random random = new Random();
        for (Parada parada : paradas) {
            int numPasajeros = random.nextInt(colectivo.getTotalPasajeros());
            for (int i = 0; i < numPasajeros; i++) {
                Pasajero pasajero = new Pasajero(parada, colectivo);
                parada.getPasajeros().add(pasajero);
            }
        }
    }

    private List<Pasajero> quitarPasajeros(Colectivo colectivo, List<Pasajero> pasajeros) {
        List<Pasajero> pasajerosBajados = colectivo.bajarPasajeros(pasajeros);
        return pasajerosBajados;
    }

    private List<Pasajero> agregarPasajeros(Colectivo colectivo, Parada parada) {
        List<Pasajero> pasajerosSubidos = colectivo.subirPasajeros(parada.getPasajeros());
        return pasajerosSubidos;
    }

    private Linea buscarLineaPorId(List<Linea> lineas, String id) {
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
        double indiceSatisfaccion = (calificacion5 * 5 + calificacion4 * 4 + calificacion3 * 3 + calificacion2 * 2
                + calificacion1 * 1) / (maxCalificacion * (totalPasajerosSubieron + totalPasajerosBajaron));
        return indiceSatisfaccion;
    }

    private double calcularOcupacionPromedio(Colectivo colectivo, Linea linea) {
        int totalTramos = linea.getParadas().size() - 1;
        int pasajerosTransportados = colectivo.getTotalPasajeros() - colectivo.getAsientosDisponibles();
        double promedioOcupacion = (double) pasajerosTransportados
                / (double) (totalTramos * colectivo.getTotalPasajeros());
        return promedioOcupacion;
    }
}