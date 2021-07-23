import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Principal {

    static Random meuGerador = new Random();

    public static int obterTamanhoIntersecao(
            ArrayList<Integer> lista1, ArrayList<Integer> lista2) {

        return obterIntersecao(lista1, lista2).size();
    }

    public static ArrayList<Integer> obterIntersecao(
            ArrayList<Integer> lista1, ArrayList<Integer> lista2) {

        ArrayList<Integer> intersecao = new ArrayList<>();
        for (Integer elemento : lista1) {  // for each...
            if (lista2.contains(elemento) && !intersecao.contains(elemento)) {
                intersecao.add(elemento);
            }
        }
        return intersecao;
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

        ArrayList<Integer> lista1 = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.printf("\nQual o tamanho das listas? ");
        int tamanho = scanner.nextInt();

        for (int i = 1; i <= tamanho; i++) {
            lista1.add(sortearInt(1, 10 * tamanho));
            lista2.add(sortearInt(1, 10 * tamanho));
        }

        System.out.println(lista1);
        System.out.println(lista2);

        System.out.printf("\nAs listas possuem %d elemento(s) em comum.",
                obterTamanhoIntersecao(lista1, lista2));
    }
}
