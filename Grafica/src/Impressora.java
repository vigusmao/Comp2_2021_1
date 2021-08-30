public abstract class Impressora {

    private final String modelo;

    private final long numeroDeSerie;

    private int quantFolhasRestantes;

    private int quantDocumentosImpressos;

    public Impressora(String modelo, long numeroDeSerie) {
        this.modelo = modelo;
        this.numeroDeSerie = numeroDeSerie;
    }

    /**
     * Imprime, se possível, o documento fornecido.
     *
     * @param documento O documento a ser impresso
     *
     * @throws PapelInsuficienteException se não houver folhas suficientes para o documento inteiro
     * @throws TintaEsgotadaException se acabar a tinta
     */
    public void imprimirDocumento(Documento documento)
            throws PapelInsuficienteException, TintaEsgotadaException {

        // verifica se há papel suficiente (se não houver, não imprime)
        if (quantFolhasRestantes < documento.getQuantPaginas()) {
            throw new PapelInsuficienteException();
        }

        // incrementa o número de documentos impressos
        this.quantDocumentosImpressos++;

        // para cada página, manda imprimir de fato
        for (int i = 1; i <= documento.getQuantPaginas(); i++) {
            executarImpressaoPagina(documento.getPagina(i));
            this.quantFolhasRestantes--;
        }
    }

    public void carregarPapel(int numeroDeFolhas) {
        this.quantFolhasRestantes += numeroDeFolhas;
    }

    public int getQuantidadeFolhasRestantes() {
        return this.quantFolhasRestantes;
    }

    public int getQuantidadeDocumentosImpressos() {
        return this.quantDocumentosImpressos;
    }

    public String getModelo() {
        return modelo;
    }

    public long getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public abstract void executarRotinaLimpeza();

    public abstract void executarImpressaoPagina(String pagina) throws TintaEsgotadaException;
}
