import java.util.ArrayList;
import java.util.HashMap;

public class Grafica {

    private ArrayList<Impressora> impressoras;

    private HashMap<Long, Impressora> impressoraByNumeroDeSerie;

    private HashMap<String, ArrayList<Impressora>> impressorasByModelo;

    private int indiceProximaImpressoraNoRevezamento;

    private float precoPorPaginaPB;
    private float precoPorPaginaColorida;

    public Grafica() {
        this.impressoras = new ArrayList<>();
        this.impressoraByNumeroDeSerie = new HashMap<>();
    }

    public void imprimirDocumento(Documento documento) {

        if (this.indiceProximaImpressoraNoRevezamento >= this.impressoras.size()) {
            this.indiceProximaImpressoraNoRevezamento =
                    this.indiceProximaImpressoraNoRevezamento % this.impressoras.size();
        }
        Impressora impressoraDaVez = this.impressoras.get(this.indiceProximaImpressoraNoRevezamento);

        impressoraDaVez.imprimirDocumento(documento);

        this.indiceProximaImpressoraNoRevezamento++;
    }

    public float orcarImpressao(Documento documento) throws NullPointerException {
        return documento.getQuantPaginas() *
                (documento.isEmCores() ?
                        this.precoPorPaginaColorida :
                        this.precoPorPaginaPB);
    }

    /**
     * Adicionaremos uma impressora à lista de impressoras da loja
     * @param impressora um objeto (não nulo!!!) do tipo Impressora
     */
    public void adicionarImpressora(Impressora impressora) {
        this.impressoras.add(impressora);
        this.impressoraByNumeroDeSerie.put(
                impressora.getNumeroDeSerie(),
                impressora);
    }

    public void removerImpressora(Impressora impressora) {
        this.impressoras.remove(impressora);
        this.impressoraByNumeroDeSerie.remove(impressora.getNumeroDeSerie());
    }

    public void removerImpressora(int indiceImpressora) {
        Impressora impressoraRemovida = this.impressoras.remove(indiceImpressora);
        this.impressoraByNumeroDeSerie.remove(impressoraRemovida.getNumeroDeSerie());
    }

    public Impressora obterImpressora(long numeroDeSerie) {
        return this.impressoraByNumeroDeSerie.get(numeroDeSerie);
    }

    public void setPrecoPorPagina(float precoPorPagina, boolean emCores) {
        if (emCores) {
            this.precoPorPaginaColorida = precoPorPagina;
        } else {
            this.precoPorPaginaPB = precoPorPagina;
        }
    }

}
