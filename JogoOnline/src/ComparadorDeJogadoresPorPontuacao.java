import java.util.Comparator;

public class ComparadorDeJogadoresPorPontuacao implements Comparator<Jogador> {

    @Override
    public int compare(Jogador o1, Jogador o2) {
        return o1.getPontuacao() - o2.getPontuacao();
    }
}
