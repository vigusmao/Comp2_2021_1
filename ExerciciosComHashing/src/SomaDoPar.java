import java.util.ArrayList;
import java.util.HashSet;

public class SomaDoPar {

    /**
     * Percorre a lista dada para encontrar um par de elementos
     * cuja soma seja o valor desejado.
     *
     * @param numeros uma lista de inteiros
     * @param somaDesejada a soma desejada
     *
     * @return O menor dos elementos do par encontrado;
     *         ou null, caso nenhum par de elementos some o valor desejado
     */
    public static Integer encontrarParComSomaDadaQuadratico(
            ArrayList<Integer> numeros, int somaDesejada) {

        // Algoritmo ineficiente (quadrático):

        for (int i = 0; i < numeros.size(); i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                int x = numeros.get(i);
                int y = numeros.get(j);
                if (x + y == somaDesejada) {
                    return Math.min(x, y);
                }
            }
        }
        return null;
    }

    /**
     * Percorre a lista dada para encontrar um par de elementos
     * cuja soma seja o valor desejado.
     *
     * @param numeros uma lista de inteiros
     * @param somaDesejada a soma desejada
     *
     * @return O menor dos elementos do par encontrado;
     *         ou null, caso nenhum par de elementos some o valor desejado
     */
    public static Integer encontrarParComSomaDada(
            ArrayList<Integer> numeros, int somaDesejada) {

     // Algoritmo eficiente (linear):

        // 1) pré-processa: joga toda a lista num hash set
        HashSet<Integer> conjunto = new HashSet<>();
        conjunto.addAll(numeros);

        // 2) para cada elemento da lista...
        for (int x : numeros) {
            // computa o complemento dele
            int y = somaDesejada - x;
            // pergunta pro hash set se contém o complemento
            if (conjunto.contains(y)) {
                return Math.min(x, y);
            }
        }
        return null;
    }

    /**
     * Percorre a lista dada para encontrar um par de elementos
     * cuja soma seja o valor desejado.
     *
     * @param numeros uma lista de inteiros
     * @param somaDesejada a soma desejada
     *
     * @return O menor dos elementos do par encontrado;
     *         ou null, caso nenhum par de elementos some o valor desejado
     */
    public static Integer encontrarParComSomaDadaElegante(
            ArrayList<Integer> numeros, int somaDesejada) {

        // 1) cria um hash set vazio
        HashSet<Integer> conjunto = new HashSet<>();

        // 2) para cada elemento x da lista...
        for (int x : numeros) {
            // computa o complemento dele
            int y = somaDesejada - x;
            // pergunta pro hash set se contém o complemento
            if (conjunto.contains(y)) {
                return Math.min(x, y);
            }
            conjunto.add(x);
        }
        return null;
    }
}
