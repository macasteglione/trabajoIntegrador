package colectivo.model;

import java.util.ArrayList;
import java.util.List;

public class Colectivo {
    private String id;
    private Linea linea;
    private int cantidadAsientos;
    private int totalPasajeros;
    private int numeroRecorridos;
    private List<Pasajero> pasajeros;
    private Parada paradaActual;

    public Colectivo(String id, Linea linea, int cantidadAsientos, int totalPasajeros) {
        this.id = id;
        this.linea = linea;
        this.cantidadAsientos = cantidadAsientos;
        this.totalPasajeros = totalPasajeros;
        pasajeros = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public int getTotalPasajeros() {
        return totalPasajeros;
    }

    public int getNumeroRecorridos() {
        return numeroRecorridos;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void agregarPasajero(Pasajero pasajero) {
        pasajeros.add(pasajero);
    }

    public void eliminarPasajero(Pasajero pasajero) {
        pasajeros.remove(pasajero);
    }

    public void eliminarPasajeros(List<Pasajero> pasajerosEliminar) {
        pasajeros.removeAll(pasajerosEliminar);
    }

    public void setParadaActual(Parada siguienteParada) {
        this.paradaActual = siguienteParada;
    }

    public Parada getParadaActual() {
        return paradaActual;
    }

    public void setNumeroRecorridos(int numeroRecorridos) {
        this.numeroRecorridos = numeroRecorridos;
    }

    public void subirPasajeros(int cantidad) {
        if (pasajeros.size() + cantidad <= cantidadAsientos) {
            for (int i = 0; i < cantidad; i++) {
                Pasajero pasajero = new Pasajero(0, null);
                pasajeros.add(pasajero);
            }
        } else {
            System.out.println("No hay suficientes asientos disponibles para subir a todos los pasajeros.");
        }
    }

    public void agregarPasajeros(List<Pasajero> pasajerosAgregar) {
        int asientosDisponibles = cantidadAsientos - pasajeros.size();
        int cantidadPasajerosAgregar = Math.min(asientosDisponibles, pasajerosAgregar.size());
        pasajeros.addAll(pasajerosAgregar.subList(0, cantidadPasajerosAgregar));
    }

    public void vaciarPasajeros() {
        pasajeros.clear();
    }
}