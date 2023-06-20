package colectivo.model;

public class Pasajero {
    private int calificacion;
    private Parada destino;

    public Pasajero(int calificacion, Parada destino) {
        this.calificacion = calificacion;
        this.destino = destino;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public Parada getDestino() {
        return destino;
    }
}