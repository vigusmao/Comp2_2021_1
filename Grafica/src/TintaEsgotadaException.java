public class TintaEsgotadaException extends Exception {

    private String cor;

    public TintaEsgotadaException(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }
}
