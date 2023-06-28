package colectivo.model;

public class Pasajero {
    Parada parada; // Parada en la que se encuentra el pasajero
    private Colectivo colectivo; // Colectivo en el que viaja el pasajero

    public Pasajero(Parada parada, Colectivo colectivo) {
        this.parada = parada;
        this.colectivo = colectivo;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    private int calificacion; // Calificación del pasajero

    public int getCalificacion() {
        return calificacion; // Devuelve la calificación del pasajero
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion; // Establece la calificación del pasajero
    }

    @Override
    public String toString() {
        return "Pasajero [parada: " + parada.getId() + "\ncolectivo: " + (colectivo != null ? colectivo.getId() : "N/A")
                + "]"; // Devuelve una representación en forma de cadena del pasajero y su información asociada
    }
}