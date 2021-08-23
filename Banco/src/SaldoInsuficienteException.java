public class SaldoInsuficienteException extends Exception {

    private float saldoFaltante;

    public float getSaldoFaltante() {
        return saldoFaltante;
    }

    public void setSaldoFaltante(float saldoFaltante) {
        this.saldoFaltante = saldoFaltante;
    }
}
