import java.util.HashMap;
import java.util.Map;

public class DadoComum extends DadoGenerico<Integer> implements Sorteador<Integer> {

    public DadoComum() {
        super(obterMapaComFrequenciasUniformes());
    }

    private static Map<Integer, Integer> obterMapaComFrequenciasUniformes() {
        Map<Integer, Integer> mapa = new HashMap<>();
        mapa.put(1, 1);
        mapa.put(2, 1);
        mapa.put(3, 1);
        mapa.put(4, 1);
        mapa.put(5, 1);
        mapa.put(6, 1);
        return mapa;
    }
}
