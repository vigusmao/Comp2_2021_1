import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CaracterMaisFrequenteTest {

    @Test
    public void testarCaracterMaisFrequente() {
        assertEquals('a', CaracterMaisFrequente.encontrarCaracterMaisFrequente("arara"));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequente("a r a r a 345 sdf hhh"));
    }

    @Test
    @Ignore
    public void testarPerformanceComparativa() {
        for (int tamanho = 1000; tamanho < 80000000; tamanho *= 2) {

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= tamanho / 5; i++) {
                sb.append("aabcd");
            }
            String texto = sb.toString();

            System.out.println("\n\ntamanho = " + tamanho);

//            long inicio = System.currentTimeMillis();
//            assertEquals('a',
//                    CaracterMaisFrequente.encontrarCaracterMaisFrequenteQuadratico(texto));
//            long duracao = System.currentTimeMillis() - inicio;
//            System.out.printf("\nDuração (quadrático) = %.3f segundos", (duracao / 1000f));

            long inicio = System.currentTimeMillis();
            assertEquals('a',
                    CaracterMaisFrequente.encontrarCaracterMaisFrequente(texto));
            long duracao = System.currentTimeMillis() - inicio;
            System.out.printf("\nDuração (linear) = %.3f segundos", (duracao / 1000f));

            inicio = System.currentTimeMillis();
            assertEquals('a',
                    CaracterMaisFrequente.encontrarCaracterMaisFrequenteElegante(texto));
            duracao = System.currentTimeMillis() - inicio;
            System.out.printf("\nDuração (elegante) = %.3f segundos", (duracao / 1000f));

        }
    }
}