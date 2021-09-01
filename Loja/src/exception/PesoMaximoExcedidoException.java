package exception;

public class PesoMaximoExcedidoException extends Exception {

    private int pesoMaximoSuportadoEmGramas;

    public PesoMaximoExcedidoException(int pesoMaximoSuportadoEmGramas) {
        this.pesoMaximoSuportadoEmGramas = pesoMaximoSuportadoEmGramas;
    }

    public int getPesoMaximoSuportadoEmGramas() {
        return pesoMaximoSuportadoEmGramas;
    }
}
