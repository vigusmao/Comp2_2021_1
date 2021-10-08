/**
 * Esta interface declara o método getPosicao(), que é necessário para que
 * algo seja considerado "colecionável". Trata-se da posição que aquele objeto
 * devera ocupar num álbum para colecionadores daquele tipo de objeto.
 */
public interface Colecionavel {

    /**
     * @return um inteiro positivo indicando a posição deste item colecionável em seu álbum
     */
    int getPosicao();
}
