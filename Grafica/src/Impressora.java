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
     * @return true, se impressão bem sucedida; false, caso contrário
     */
    public boolean imprimirDocumento(Documento documento) {
        // verifica se há papel suficiente (se não houver, não imprime)
        if (quantFolhasRestantes < documento.getQuantPaginas()) {
            return false;
        }

        // incrementa o número de documentos impressos
        this.quantDocumentosImpressos++;

        // para cada página, manda imprimir de fato
        for (int i = 1; i <= documento.getQuantPaginas(); i++) {
            executarImpressaoPagina(documento.getPagina(i));
            this.quantFolhasRestantes--;
        }

        return true;
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

    public abstract void executarImpressaoPagina(String pagina);
}
