package colectivo.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import colectivo.model.*;

public class Datos {
    private List<Colectivo> colectivos;
    private List<Linea> lineas;
    private List<Parada> paradas;
    private int totalPasajeros;
    private int recorridos;

    // Inicializar las listas de los objetos como una nueva lista vacía
    public Datos() {
        colectivos = new ArrayList<>();
        lineas = new ArrayList<>();
        paradas = new ArrayList<>();
    }

    public void cargarConfiguracion(String configFilePath) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(configFilePath));
        String lineaFilePath = properties.getProperty("linea"); // Ruta de los archivos
        String paradaFilePath = properties.getProperty("parada");
        String colectivoFilePath = properties.getProperty("colectivo");
        totalPasajeros = Integer.parseInt(properties.getProperty("pasajeros"));
        recorridos = Integer.parseInt(properties.getProperty("recorridos"));
        cargarLineas(lineaFilePath); // Cargar los datos desde el archivo
        cargarParadas(paradaFilePath);
        cargarColectivos(colectivoFilePath);
        cargarLineas(lineaFilePath); // Cargar las líneas nuevamente (tiempo muerto, problema de dependencias)
    }

    private void cargarLineas(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            String id = data[0]; // ID de la línea
            String[] paradaIds = Arrays.copyOfRange(data, 1, data.length); // IDs de las paradas asociadas a la línea
            List<Parada> paradasLinea = new ArrayList<>();
            for (String paradaId : paradaIds) {
                Parada parada = buscarParadaPorId(paradaId); // Buscar la parada por su ID
                if (parada != null)
                    paradasLinea.add(parada); // Agregar la parada a la lista de paradas de la línea
            }
            Linea linea = new Linea(id, paradasLinea); // Crear un objeto de tipo Linea con el ID y las paradas
            lineas.add(linea); // Agregar la línea a la lista de líneas de colectivos
        }
        reader.close();
    }

    private void cargarColectivos(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            String id = data[0]; // ID del colectivo
            String lineaId = data[1]; // ID de la línea a la que pertenece el colectivo
            int asientos = Integer.parseInt(data[2]); // Número de asientos del colectivo
            int totalPasajeros = Integer.parseInt(data[3]); // Número total de pasajeros del colectivo
            Linea linea = buscarLineaPorId(lineaId); // Buscar la línea por su ID
            if (linea != null) {
                // Crear un objeto de tipo Colectivo con el ID, los asientos y el total de pasajeros
                Colectivo colectivo = new Colectivo(id, asientos, totalPasajeros, new ArrayList<>());
                colectivo.setLinea(lineaId); // Establecer la línea a la que pertenece el colectivo
                colectivos.add(colectivo); // Agregar el colectivo a la lista de colectivos
            }
        }
        reader.close();
    }

    private void cargarParadas(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            String id = data[0]; // ID de la parada
            String direccion = data[1]; // Dirección de la parada
            String lineaID = data[2]; // ID de la línea a la que pertenece la parada
            Linea linea = buscarLineaPorId(lineaID); // Buscar la línea por su ID
            // Crear un objeto de tipo Parada con el ID, la dirección y la línea
            Parada parada = new Parada(id, direccion, linea, new ArrayList<>());
            paradas.add(parada); // Agregar la parada a la lista de paradas de colectivos
        }
        reader.close();
    }

    private Linea buscarLineaPorId(String id) {
        for (Linea linea : lineas)
            if (linea.getId().equals(id))
                return linea; // Devolver la línea si el ID coincide
        return null; // Devolver null si no se encuentra la línea
    }

    private Parada buscarParadaPorId(String id) {
        for (Parada parada : paradas)
            if (parada.getId().equals(id))
                return parada; // Devolver la parada si el ID coincide
        return null; // Devolver null si no se encuentra la parada
    }

    public List<Colectivo> getColectivos() {
        return colectivos;
    }

    public List<Linea> getLineas() {
        return lineas;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public int getTotalPasajeros() {
        return totalPasajeros;
    }

    public int getRecorridos() {
        return recorridos;
    }
}