public class Selo implements Colecionavel {

    private String pais;

    private float valorMonetario;

    private int anoLancamento;

    private String urlDaImagem;

    private final int posicao;

    public Selo(int posicao) {
        this.posicao = posicao;
    }

    @Override
    public int getPosicao() {
        return this.posicao;
    }

    public String getPais() {
        return pais;
    }
}
