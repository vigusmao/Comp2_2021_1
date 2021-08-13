public class ImpressoraJatoDeTinta extends Impressora {

    @Override
    public void executarRotinaLimpeza() {
        System.out.println("Limpando bicos de impressão e verificando tintas...");
    }

    @Override
    public void executarImpressaoPagina(String pagina) {
        System.out.println("Imprimindo utilizando jatos de tinta...");
    }

    public int getNivelTintaPreta() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public int getNivelTintaAmarela() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public int getNivelTintaMagenta() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public int getNivelTintaCiano() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }
}
