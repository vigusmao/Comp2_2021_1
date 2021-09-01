package controle;

import exception.PesoMaximoExcedidoException;
import exception.VolumeMaximoExcedidoException;

public interface Transportador {

    void transportar(Transportavel transportavel, String endereco)
            throws PesoMaximoExcedidoException, VolumeMaximoExcedidoException;
}
