import java.util.Map;

public class DadoGenerico<T> implements Sorteador<T> {

    Map<T, Integer> frequenciaByResultado;

    public DadoGenerico(Map<T, Integer> frequenciaByResultado) {
        this.frequenciaByResultado = frequenciaByResultado;
    }

    @Override
    public T sortear() {
        // ToDo IMPLEMENT ME!!!!
        return null;
    }
}
