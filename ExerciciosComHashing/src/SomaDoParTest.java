import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SomaDoParTest {

    private ArrayList<Integer> lista;

    @Before
    public void setUp() {
        int[] array = new int[] {1, 45, -8, 50, 12, 900, -7, 4, 49};
        lista = new ArrayList<>();
        for (int elemento : array) {
            lista.add(elemento);
        }
    }

    @Test
    public void testarSomaDoParQuandoEncontra() {
        assertEquals(Integer.valueOf(-8), SomaDoPar.encontrarParComSomaDada(lista, 4));
        assertEquals(Integer.valueOf(4), SomaDoPar.encontrarParComSomaDada(lista, 53));
    }

    @Test
    public void testarSomaDoParQuandoNaoEncontra() {
        assertNull(SomaDoPar.encontrarParComSomaDada(lista, 1000000));
        assertNull(SomaDoPar.encontrarParComSomaDada(lista, 1));
        assertNull(SomaDoPar.encontrarParComSomaDada(lista, 0));
    }

    @Test
    @Ignore
    public void testarPerformanceComparativa() {
        for (int tamanho = 1000; tamanho < 80000000; tamanho *= 2) {

            ArrayList<Integer> lista = new ArrayList<>();
            for (int i = 1; i <= tamanho; i++) {
                lista.add(i);
            }

            System.out.println("\n\ntamanho = " + tamanho);

            long inicio = System.currentTimeMillis();
            assertEquals(tamanho - 1,
                    (int) SomaDoPar.encontrarParComSomaDadaQuadratico(lista, 2 * tamanho - 1));
            long duracao = System.currentTimeMillis() - inicio;
            System.out.printf("\nDuração (quadrático) = %.3f segundos", (duracao / 1000f));

            inicio = System.currentTimeMillis();
            assertEquals(tamanho - 1,
                    (int) SomaDoPar.encontrarParComSomaDada(lista, 2 * tamanho - 1));
            duracao = System.currentTimeMillis() - inicio;
            System.out.printf("\nDuração (linear) = %.3f segundos", (duracao / 1000f));

            inicio = System.currentTimeMillis();
            assertEquals(tamanho - 1,
                    (int) SomaDoPar.encontrarParComSomaDadaElegante(lista, 2 * tamanho - 1));
            duracao = System.currentTimeMillis() - inicio;
            System.out.printf("\nDuração (elegante) = %.3f segundos", (duracao / 1000f));

        }
    }
}