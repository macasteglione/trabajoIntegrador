package colectivo.model;

public class Pasajero {
    private Parada parada; // Parada en la que se encuentra el pasajero
    private Parada paradaDestino;

    public Pasajero(Parada parada, Parada paradaDestino) {
        this.parada = parada;
        this.paradaDestino = paradaDestino;
    }

    private int calificacion; // Calificación del pasajero

    public int getCalificacion() {
        return calificacion; // Devuelve la calificación del pasajero
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion; // Establece la calificación del pasajero
    }

    public Parada getParadaDestino() {
        return paradaDestino;
    }

    public void setParadaDestino(Parada paradaDestino) {
        this.paradaDestino = paradaDestino;
    }

    @Override
    public String toString() {
        return "Pasajero [parada: " + parada.getId() + "\ncolectivo: "
                + "]"; // Devuelve una representación en forma de cadena del pasajero y su información
                       // asociada
    }
}