public abstract class Impressora {

    public boolean imprimirDocumento(Documento documento) {
        // verifica se há papel suficiente (se não houver, não imprime)
        // ToDo IMPLEMENT ME!!!

        // incrementa o número de documentos impressos
        // ToDo IMPLEMENT ME!!!

        // para cada página, manda imprimir de fato
        for (int i = 1; i <= documento.getQuantPaginas(); i++) {
            executarImpressaoPagina(documento.getPagina(i));
        }

        return true;
    }

    public void carregarPapel(int numeroDeFolhas) {
        // ToDo IMPLEMENT ME!!!
    }

    public int getQuantidadeFolhasRestantes() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public int getQuantidadeDocumentosImpressos() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public abstract void executarRotinaLimpeza();

    public abstract void executarImpressaoPagina(String pagina);
}
