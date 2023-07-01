package colectivo.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import colectivo.model.Colectivo;
import colectivo.model.Linea;
import colectivo.model.Parada;

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
        cargarLineas(lineaFilePath); // Cargar las líneas nuevamente (tiempo muerto, problema de dependencias)
        lineas.removeIf(linea -> linea.getParadas().isEmpty()); // Eliminar las líneas que no tienen paradas asociadas
        cargarColectivos(colectivoFilePath);
        cargarParadas(paradaFilePath); // Cargar las paradas nuevamente (tiempo muerto, problema de dependencias)
        paradas.removeIf(parada -> parada.getLinea().getParadas().isEmpty());
    }

       private void cargarLineas(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            String id = data[0];
            List<Parada> paradasPorLinea = new ArrayList<>();
            for (int i = 1; i < data.length; i++) {
                Parada parada = buscarParadaPorId(data[i]);
                if (parada != null)
                    paradasPorLinea.add(parada);
            }
            Linea linea = new Linea(id, paradasPorLinea, new ArrayList<>());
            lineas.add(linea);
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

    private void cargarColectivos(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            String id = data[0];
            Linea lineaColectivo = buscarLineaPorId(data[1]);
            int asientos = Integer.parseInt(data[2]);
            int totalPasajeros = Integer.parseInt(data[3]);
            if (lineaColectivo != null) {
                Colectivo colectivo = new Colectivo(id, asientos, totalPasajeros, new ArrayList<>(), lineaColectivo);
                colectivos.add(colectivo);
                lineaColectivo.getColectivos().add(colectivo);
            }
        }
        reader.close();
    }

    private Linea buscarLineaPorId(String id) {
        for (Linea linea : lineas) {
            if (linea.getId().equals(id))
                return linea;
        }
        return null;
    }

    private Parada buscarParadaPorId(String id) {
        for (Parada parada : paradas) {
            if (parada.getId().equals(id))
                return parada;
        }
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