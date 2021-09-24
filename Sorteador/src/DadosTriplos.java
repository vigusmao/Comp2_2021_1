public class DadosTriplos implements Sorteador<Integer> {

    private DadoComum dadoComum;

    public DadosTriplos() {
        this.dadoComum = new DadoComum();
    }

    @Override
    public Integer sortear() {
        int resultado = 0;
        for (int i = 0; i < 3; i++) {
            resultado += this.dadoComum.sortear();
        }
        return resultado;
    }
}
