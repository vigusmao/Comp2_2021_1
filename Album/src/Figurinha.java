public class Figurinha implements Colecionavel {

    private final int posicao;

    private String urlDaImagem;

    private int alturaEmPixels;

    private int larguraEmPixels;

    public Figurinha(int posicao, String urlDaImagem) {
        this.posicao = posicao;
        this.urlDaImagem = urlDaImagem;
    }

    @Override
    public int getPosicao() {
       return this.posicao;
    }
}
