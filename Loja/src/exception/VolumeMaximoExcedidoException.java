package exception;

public class VolumeMaximoExcedidoException extends Exception {

    private int volumeMaximoEmCm3;

    public VolumeMaximoExcedidoException(int volumeMaximoEmCm3) {
        this.volumeMaximoEmCm3 = volumeMaximoEmCm3;
    }

    public int getVolumeMaximoEmCm3() {
        return volumeMaximoEmCm3;
    }
}
