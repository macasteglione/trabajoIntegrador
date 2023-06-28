package colectivo.model;

public class Pasajero {
    Parada parada;
    private Colectivo colectivo;

    public Pasajero(Parada parada, Colectivo colectivo) {
        this.parada = parada;
        this.colectivo = colectivo;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    private int calificacion;

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Pasajero [parada: " + parada.getId() + "\ncolectivo: " + (colectivo != null ? colectivo.getId() : "N/A")
                + "]";
    }
}