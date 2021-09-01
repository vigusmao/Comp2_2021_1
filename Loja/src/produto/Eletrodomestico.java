package produto;

public class Eletrodomestico extends Produto {

    private static final float PRECO_DEFAULT = 1.99f;

    private int garantiaEmMeses;

    private int consumoEmWatts;

    private String tipo;

    private String marca;

    private String cor;

    public Eletrodomestico(long numeroDeSerie, String tipo, String marca) {
        super(numeroDeSerie, String.format("%s %s", tipo, marca));
    }

    @Override
    protected float getPrecoDefault() {
        return PRECO_DEFAULT;
    }
}
