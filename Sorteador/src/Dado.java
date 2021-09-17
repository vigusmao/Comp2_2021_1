import java.util.Random;

public class Dado implements Sorteador {

    private final Random random = new Random();

    @Override
    public int sortear() {
        return random.nextInt(6) + 1;
    }
}
