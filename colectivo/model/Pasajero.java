package colectivo.model;

public class Pasajero {
    private Parada parada;
    private Parada paradaDestino;
    private int esperando = 0;

    public Pasajero(Parada parada, Parada paradaDestino) {
        this.parada = parada;
        this.paradaDestino = paradaDestino;
    }

    public Parada getParada() {
        return parada;
    }

    public Parada getParadaDestino() {
        return paradaDestino;
    }

    public void setParadaDestino(Parada paradaDestino) {
        this.paradaDestino = paradaDestino;
    }

    public int getEsperando() {
        return esperando;
    }

    public void setEsperando(int esperando) {
        this.esperando = esperando;
    }

    @Override
    public String toString() {
        return "Pasajero [parada: " + parada.getId() + ", paradaDestino: " + paradaDestino.getId() + "]";
    }
}