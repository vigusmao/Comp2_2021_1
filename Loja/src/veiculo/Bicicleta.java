package veiculo;

import controle.Vendavel;

public class Bicicleta extends Veiculo implements Vendavel {

    @Override
    public float getPrecoSugerido() {
        return 0;
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
