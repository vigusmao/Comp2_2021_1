import java.util.Map;
import java.util.Random;

public class DadoGenerico<T> implements Sorteador<T> {

    private Map<T, Integer> frequenciaByResultado;

    private int somaDasFrequenciasRelativas;

    private Random random;

    public DadoGenerico(Map<T, Integer> frequenciaByResultado) {
        this.frequenciaByResultado = frequenciaByResultado;

        this.somaDasFrequenciasRelativas = 0;
        for (int freq : frequenciaByResultado.values()) {
            somaDasFrequenciasRelativas += freq;
        }

        this.random = new Random();
    }

    @Override
    public T sortear() {

        double numeroSorteado = this.random.nextDouble() * this.somaDasFrequenciasRelativas;

        int freqAcumulada = 0;

        for (Map.Entry<T, Integer> resultadoEFrequencia : this.frequenciaByResultado.entrySet()) {
            T resultado = resultadoEFrequencia.getKey();
            int frequenciaRelativa = resultadoEFrequencia.getValue();

            freqAcumulada += frequenciaRelativa;

            if (numeroSorteado < freqAcumulada) {
                return resultado;
            }
        }

        return null;
    }
}
