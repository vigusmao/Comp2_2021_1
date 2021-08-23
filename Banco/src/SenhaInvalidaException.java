public class SenhaInvalidaException extends Exception {

    private int numeroDeTentativasErradas;

    public SenhaInvalidaException(int numeroDeTentativasErradas) {
        this.numeroDeTentativasErradas = numeroDeTentativasErradas;
    }

    public int getNumeroDeTentativasErradas() {
        return numeroDeTentativasErradas;
    }
}
