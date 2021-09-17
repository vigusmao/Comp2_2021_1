public class DadosTriplos implements Sorteador {

    private Dado dadoComum;

    public DadosTriplos() {
        this.dadoComum = new Dado();
    }

    @Override
    public int sortear() {
        int resultado = 0;
        for (int i = 0; i < 3; i++) {
            resultado += this.dadoComum.sortear();
        }
        return resultado;
    }
}
