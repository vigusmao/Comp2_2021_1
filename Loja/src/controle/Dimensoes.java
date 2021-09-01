package controle;

public class Dimensoes {

    private int larguraEmCentimetros;
    private int comprimentoEmCentimetros;
    private int alturaEmCentimetros;

    public Dimensoes(int larguraEmCentimetros, int comprimentoEmCentimetros, int alturaEmCentimetros) {
        this.larguraEmCentimetros = larguraEmCentimetros;
        this.comprimentoEmCentimetros = comprimentoEmCentimetros;
        this.alturaEmCentimetros = alturaEmCentimetros;
    }

    public int getLarguraEmCentimetros() {
        return larguraEmCentimetros;
    }

    public void setLarguraEmCentimetros(int larguraEmCentimetros) {
        this.larguraEmCentimetros = larguraEmCentimetros;
    }

    public int getComprimentoEmCentimetros() {
        return comprimentoEmCentimetros;
    }

    public void setComprimentoEmCentimetros(int comprimentoEmCentimetros) {
        this.comprimentoEmCentimetros = comprimentoEmCentimetros;
    }

    public int getAlturaEmCentimetros() {
        return alturaEmCentimetros;
    }

    public void setAlturaEmCentimetros(int alturaEmCentimetros) {
        this.alturaEmCentimetros = alturaEmCentimetros;
    }

    public int getVolumeEmCm3() {
        return larguraEmCentimetros * comprimentoEmCentimetros * alturaEmCentimetros;
    }
}
