import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DadoGenericoTest {

    @Test
    public void testarFuncionamento() {

        Map<String, Integer> mapinha = new HashMap<>();
        mapinha.put("pedra", 2);
        mapinha.put("papel", 3);
        mapinha.put("tesoura", 10);

        DadoGenerico<String> dadoGenerico = new DadoGenerico<>(mapinha);

        int contPedra = 0;
        int contPapel = 0;
        int contTesoura = 0;


        final int TOTAL_SORTEIOS = 10_000;

        for (int i = 0; i < TOTAL_SORTEIOS; i++) {
            final String resultado = dadoGenerico.sortear();

            System.out.println(resultado);

            switch (resultado) {
                case "pedra":
                    contPedra++;
                    break;
                case "papel":
                    contPapel++;
                    break;
                case "tesoura":
                    contTesoura++;
                    break;
            }
        }

        final double frequenciaPedra = contPedra / (double) TOTAL_SORTEIOS;
        final double frequenciaPapel = contPapel / (double) TOTAL_SORTEIOS;
        final double frequenciaTesoura = contTesoura / (double) TOTAL_SORTEIOS;


        assertTrue(Math.abs(frequenciaPedra - 2/15.0) < 0.01);
        assertTrue(Math.abs(frequenciaPapel - 3/15.0) < 0.01);
        assertTrue(Math.abs(frequenciaTesoura - 10/15.0) < 0.01);
    }
}