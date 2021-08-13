public class ImpressoraLaser extends Impressora {

    @Override
    public void executarRotinaLimpeza() {
        System.out.println("Limpando toner...");
    }

    @Override
    public void executarImpressaoPagina(String pagina) {
        System.out.println("Imprimindo utilizando laser...");
    }
}
