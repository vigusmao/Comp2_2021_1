public class ImpressoraLaser extends Impressora {

    static int quantImpressorasLaserCriadas = 0;

    private final int potenciaEmWatts;

    public ImpressoraLaser(String modelo, long numeroDeSerie, int potenciaEmWatts) {
        super(modelo, numeroDeSerie);
        this.potenciaEmWatts = potenciaEmWatts;
        quantImpressorasLaserCriadas++;
    }

    @Override
    public void executarRotinaLimpeza() {
        System.out.println("Limpando toner...");
    }

    @Override
    public void executarImpressaoPagina(String pagina) {
        System.out.println("Imprimindo utilizando laser...");
    }

    public int getPotenciaEmWatts() {
        return potenciaEmWatts;
    }
}
