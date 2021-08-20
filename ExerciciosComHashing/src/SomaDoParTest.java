import org.junit.Before;
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

}