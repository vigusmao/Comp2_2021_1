import java.util.HashMap;

public class CaracterMaisFrequente {

    public static char encontrarCaracterMaisFrequenteQuadratico(String texto) {

        // Algoritmo ineficiente (quadrático):

        char maisFrequente = texto.charAt(0);
        int ocorrenciasDoMaisFrequente = 1;

        for (int i = 0; i < texto.length(); i++) {
            char caracterDaVez = texto.charAt(i);
            int contOcorrencias = 1;
            for (int j = i + 1; j < texto.length(); j++) {
                if (texto.charAt(j) == caracterDaVez) {
                    contOcorrencias++;
                }
            }

            if (contOcorrencias > ocorrenciasDoMaisFrequente) {
                maisFrequente = caracterDaVez;
                ocorrenciasDoMaisFrequente = contOcorrencias;
            }
        }

        return maisFrequente;
    }

    public static char encontrarCaracterMaisFrequente(String texto) {
        HashMap<Character, Integer> ocorreciasByCatacter = new HashMap<>();

        // preparo um mapa de ocorrência por caracter
        for (int i = 0; i < texto.length(); i++) {
            char caracterDaVez = texto.charAt(i);
            Integer contadorAteOMomento = ocorreciasByCatacter.get(caracterDaVez);
            if (contadorAteOMomento == null) {
                // trata-se da primeira ocorrência deste caracter no texto
                ocorreciasByCatacter.put(caracterDaVez, 1);
            } else {
                ocorreciasByCatacter.put(caracterDaVez, contadorAteOMomento + 1);
            }
        }

        // itero pelo mapa para descobrir quem foi o mais frequente
        int maxOcorrencias = 0;
        Character charMaisFrequente = null;
        for (Character caracter : ocorreciasByCatacter.keySet()) {
            int ocorrencias = ocorreciasByCatacter.get(caracter);
            if (ocorrencias > maxOcorrencias) {
                maxOcorrencias = ocorrencias;
                charMaisFrequente = caracter;
            }
        }

        return charMaisFrequente;
    }

    public static char encontrarCaracterMaisFrequenteElegante(String texto) {
        HashMap<Character, Integer> ocorreciasByCatacter = new HashMap<>();

        int maxOcorrencias = 0;
        Character charMaisFrequente = null;

        for (int i = 0; i < texto.length(); i++) {
            char caracterDaVez = texto.charAt(i);
            Integer contadorAteOMomento = ocorreciasByCatacter.get(caracterDaVez);
            int contadorAtualizado =
                    contadorAteOMomento == null
                            ? 1 // trata-se da primeira ocorrência deste caracter no texto
                            : contadorAteOMomento + 1;  // já existia, vou incrementa

            ocorreciasByCatacter.put(caracterDaVez, contadorAtualizado);
            if (contadorAtualizado > maxOcorrencias) {
                maxOcorrencias = contadorAtualizado;
                charMaisFrequente = caracterDaVez;
            }
        }

        return charMaisFrequente;
    }
}
