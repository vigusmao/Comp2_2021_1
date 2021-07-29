public class AritmeticaBasica {

    public static int calcularMaximoDivisorComum(int x, int y) {
        int resto = x % y;
        while (resto != 0) {
            x = y;
            y = resto;
            resto = x % y;
        }
        return y;
    }
}
