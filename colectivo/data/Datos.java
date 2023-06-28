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

    public Datos() {
        colectivos = new ArrayList<>();
        lineas = new ArrayList<>();
        paradas = new ArrayList<>();
    }

    public void cargarConfiguracion(String configFilePath) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(configFilePath));
        String lineaFilePath = properties.getProperty("linea");
        String paradaFilePath = properties.getProperty("parada");
        String colectivoFilePath = properties.getProperty("colectivo");
        totalPasajeros = Integer.parseInt(properties.getProperty("pasajeros"));
        recorridos = Integer.parseInt(properties.getProperty("recorridos"));
        cargarLineas(lineaFilePath); // tiempo muerto, solo para tener las lineas
        cargarParadas(paradaFilePath);
        cargarColectivos(colectivoFilePath);
        cargarLineas(lineaFilePath);
    }

    private void cargarLineas(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            String id = data[0];
            String[] paradaIds = Arrays.copyOfRange(data, 1, data.length);
            List<Parada> paradasLinea = new ArrayList<>();
            for (String paradaId : paradaIds) {
                Parada parada = buscarParadaPorId(paradaId);
                if (parada != null)
                    paradasLinea.add(parada);
            }
            Linea linea = new Linea(id, paradasLinea);
            lineas.add(linea);
        }
        reader.close();
    }

    private void cargarColectivos(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            String id = data[0];
            String lineaId = data[1];
            int asientos = Integer.parseInt(data[2]);
            int totalPasajeros = Integer.parseInt(data[3]);
            Linea linea = buscarLineaPorId(lineaId);
            if (linea != null) {
                Colectivo colectivo = new Colectivo(id, asientos, totalPasajeros, new ArrayList<>());
                colectivo.setLinea(lineaId);
                colectivos.add(colectivo);
            }
        }
        reader.close();
    }

    private void cargarParadas(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            String id = data[0];
            String direccion = data[1];
            String lineaID = data[2];
            Linea linea = buscarLineaPorId(lineaID);
            Parada parada = new Parada(id, direccion, linea, new ArrayList<>());
            paradas.add(parada);
        }
        reader.close();
    }

    private Linea buscarLineaPorId(String id) {
        for (Linea linea : lineas)
            if (linea.getId().equals(id))
                return linea;
        return null;
    }

    private Parada buscarParadaPorId(String id) {
        for (Parada parada : paradas)
            if (parada.getId().equals(id))
                return parada;
        return null;
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