import org.junit.Test;

import static org.junit.Assert.*;

public class AritmeticaBasicaTest {

    @Test
    public void testarCalcularMaximoDivisorComum() {

        assertEquals(3, AritmeticaBasica.calcularMaximoDivisorComum(3, 9));
        assertEquals(5, AritmeticaBasica.calcularMaximoDivisorComum(40, 55));
        assertEquals(1, AritmeticaBasica.calcularMaximoDivisorComum(50, 21));
        assertEquals(1, AritmeticaBasica.calcularMaximoDivisorComum(5, 7));
        assertEquals(1, AritmeticaBasica.calcularMaximoDivisorComum(5, 1));
    }
}