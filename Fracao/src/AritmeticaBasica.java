public class AritmeticaBasica {

    /**
     *
     * @param x
     * @param y
     * @return o mdc, se for possível calcular;
     * null, se um dos parâmetros for 0
     */
    public static Integer calcularMaximoDivisorComum(int x, int y) {
        if (x == 0 || y == 0) {
            return null;
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
