package colectivo.model;

public class Pasajero {
    private int id;
    private Parada destino;

    public Pasajero(int i, Parada destino) {
        this.id = i;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public Parada getDestino() {
        return destino;
    }
}