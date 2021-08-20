import org.junit.Test;

import static org.junit.Assert.*;

public class CaracterMaisFrequenteTest {

    @Test
    public void testarCaracterMaisFrequente() {
        assertEquals('a', CaracterMaisFrequente.encontrarCaracterMaisFrequente("arara"));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequente("a r a r a 345 sdf hhh"));
    }
}