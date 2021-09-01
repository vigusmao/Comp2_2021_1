package veiculo;

import controle.Dimensoes;
import controle.Transportador;
import controle.Transportavel;
import exception.PesoMaximoExcedidoException;
import exception.VolumeMaximoExcedidoException;

public class Veiculo implements Transportavel, Transportador {

    private int pesoMaximoSuportadoEmGramas;

    private int volumeMaximoEmCm3;

    public long getCodigo() {
        return 0;
    }

    public void andar(int velocidadeEmKmh) {

    }

    public int getNumeroPassageiros() {
        return 0;
    }

    @Override
    public void transportar(Transportavel transportavel, String endereco)
            throws PesoMaximoExcedidoException, VolumeMaximoExcedidoException {

        if (transportavel.getPesoEmGramas() > this.pesoMaximoSuportadoEmGramas) {
            throw new PesoMaximoExcedidoException(pesoMaximoSuportadoEmGramas);
        }

        if (transportavel.getDimensoes().getVolumeEmCm3() > this.volumeMaximoEmCm3) {
            throw new VolumeMaximoExcedidoException(volumeMaximoEmCm3);
        }

        // ToDo IMPLEMENT ME!!!
    }

    @Override
    public Dimensoes getDimensoes() {
        return null;
    }

    @Override
    public int getPesoEmGramas() {
        return 0;
    }
}
