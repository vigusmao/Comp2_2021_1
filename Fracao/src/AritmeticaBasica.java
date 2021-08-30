public class AritmeticaBasica {

    /**
     *
     * @param x um inteiro positivo
     * @param y um inteiro positivo
     * @return o mdc, se for possível calcular
     */
    public static int calcularMaximoDivisorComum(int x, int y) {
        if (x == 0 || y == 0) {
            throw new RuntimeException("Os parâmetros precisam ser positivos.");
        } else {
            int resto = x % y;
            while (resto != 0) {
                x = y;
                y = resto;
                resto = x % y;
            }
            return y;
        }
    }

    public static boolean extrairSinal(double numero) {
        return numero >= 0;
    }
}
