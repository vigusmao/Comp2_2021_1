public class ImpressoraJatoDeTinta extends Impressora {

    static int quantImpressorasJatosDeTintaCriadas = 0;

    public ImpressoraJatoDeTinta(String modelo, long numeroDeSerie) {
        super(modelo, numeroDeSerie);
        quantImpressorasJatosDeTintaCriadas++;
    }

    @Override
    public void executarRotinaLimpeza() {
        System.out.println("Limpando bicos de impressão e verificando tintas...");
    }

    @Override
    public void executarImpressaoPagina(String pagina) throws TintaEsgotadaException {

        if (getNivelTintaPreta() == 0) {
            throw new TintaEsgotadaException("preta");
        }

        System.out.println("Imprimindo utilizando jatos de tinta...");
    }

    public int getNivelTintaPreta() {
        return 10;  // ToDo IMPLEMENT ME!!!
    }

    public int getNivelTintaAmarela() {
        return 10;  // ToDo IMPLEMENT ME!!!
    }

    public int getNivelTintaMagenta() {
        return 10;  // ToDo IMPLEMENT ME!!!
    }

    public int getNivelTintaCiano() {
        return 10;  // ToDo IMPLEMENT ME!!!
    }
}
