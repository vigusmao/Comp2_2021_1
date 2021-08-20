public class ImpressoraMatricial extends Impressora {

    public ImpressoraMatricial(long numeroDeSerie) {
        super("Impressora Matricial Qualquer", numeroDeSerie);
    }

    @Override
    public void executarRotinaLimpeza() {
        System.out.println("Limpando matriz de caracteres...");
    }

    @Override
    public void executarImpressaoPagina(String pagina) {
        System.out.println("Imprimindo utilizando matriz de caracteres...");
    }
}
