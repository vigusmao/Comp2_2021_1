import java.util.Comparator;

public class ComparadorDeJogadoresLexicografico implements Comparator<Jogador> {

    @Override
    public int compare(Jogador o1, Jogador o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }
}
