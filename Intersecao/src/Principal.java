import java.util.ArrayList;
import java.util.Random;

public class Principal {

    static Random meuGerador = new Random();

    public static int obterTamanhoIntersecao(
            ArrayList<Integer> lista1, ArrayList<Integer> lista2) {

        // ToDo IMPLEMENT ME!!!!!
        return 0;
    }

    /**
     * Sorteia um inteiro aleatório entre "menor" (inclusive) e "maior" (inclusive)
     *
     * @param menor o limite inferior do intervalo desejado (fechado)
     * @param maior o limite superior do intervalo desejado (fechado)
     *
     * @return um inteiro pseudo-aleatório
     */
    public static int sortearInt(int menor, int maior) {
        return meuGerador.nextInt(maior - menor + 1) + menor;
    }

    public static void main(String[] args) {



    }
}
