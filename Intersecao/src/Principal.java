import java.util.*;

public class Principal {

    static Random meuGerador = new Random();

    public static ArrayList<Integer> obterIntersecaoQuadratico(
            ArrayList<Integer> lista1, ArrayList<Integer> lista2) {

        HashSet<Integer> intersecaoComoConjunto = new HashSet<>();

        for (Integer elemento : lista1) {  // for each...
            if (lista2.contains(elemento)) {
                intersecaoComoConjunto.add(elemento);
            }
        }  // O(n^2)

        ArrayList<Integer> intersecaoComoLista = new ArrayList<>();
        intersecaoComoLista.addAll(intersecaoComoConjunto);  // O(n)

        return intersecaoComoLista;
    }

    public static ArrayList<Integer> obterIntersecaoLinear(
            ArrayList<Integer> lista1, ArrayList<Integer> lista2) {

        HashSet<Integer> intersecaoComoConjunto = new HashSet<>();

        HashSet<Integer> hashSetComOsElementosDaLista1 = new HashSet<>();

        for (Integer elementoDaLista1 : lista1) {
            hashSetComOsElementosDaLista1.add(elementoDaLista1);  // O(1), isto é, tempo constante
        }  // O(n)

        for (Integer elementoDaLista2 : lista2) {
            if (hashSetComOsElementosDaLista1.contains(elementoDaLista2)) {  // O(1)
                intersecaoComoConjunto.add(elementoDaLista2);  // O(1)
            }
        }  // O(n)

        ArrayList<Integer> intersecaoComoLista = new ArrayList<>();
        intersecaoComoLista.addAll(intersecaoComoConjunto);  // O(n)

        return intersecaoComoLista;
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
            System.out.printf("\n\nQual o tamanho das listas? ");
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
                    obterIntersecaoQuadratico(lista1, lista2).size());
            long duracao = System.currentTimeMillis() - inicio;
            System.out.printf("\nDuracao (quadratico) = %.3f segundos", duracao / 1000.0);

            inicio = System.currentTimeMillis();
            System.out.printf("\nAs listas possuem %d elemento(s) em comum.",
                    obterIntersecaoLinear(lista1, lista2).size());
            duracao = System.currentTimeMillis() - inicio;
            System.out.printf("\nDuracao (linear) = %.3f segundos", duracao / 1000.0);

        }
    }
}
