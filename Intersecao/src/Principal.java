import java.util.*;

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

//        HashSet<Integer> mapinhaComOsElementosDaLista1 = new HashSet<>();
//        for (Integer elementoDaLista1 : lista1) {
//            mapinhaComOsElementosDaLista1.add(elementoDaLista1);
//        }
//
//        for (Integer elementoDaLista2 : lista2) {
//            if (mapinhaComOsElementosDaLista1.contains(elementoDaLista2)) {
//                intersecao.add(elementoDaLista2);
//            }
//        }

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

        while (true) {

            ArrayList<Integer> lista1 = new ArrayList<>();
            ArrayList<Integer> lista2 = new ArrayList<>();

            Scanner scanner = new Scanner(System.in);
            System.out.printf("\nQual o tamanho das listas? ");
            int tamanho = scanner.nextInt();

            if (tamanho <= 0) {
                return;
            }

            for (int i = 1; i <= tamanho; i++) {
                lista1.add(sortearInt(1, 10 * tamanho));
                lista2.add(sortearInt(1, 10 * tamanho));
            }

//        System.out.println(lista1);
//        System.out.println(lista2);

            long inicio = System.currentTimeMillis();
            System.out.printf("\nAs listas possuem %d elemento(s) em comum.",
                    obterTamanhoIntersecao(lista1, lista2));
            long duracao = System.currentTimeMillis() - inicio;
            System.out.printf("\nDuracao = %.3f segundos", duracao / 1000.0);
        }
    }
}
