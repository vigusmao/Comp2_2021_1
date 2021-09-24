public class DadosDeGamao implements Sorteador<Integer> {

    private DadoComum dadoComum;

    public DadosDeGamao() {
        this.dadoComum = new DadoComum();
    }


    @Override
    public Integer sortear() {
        int valor1 = this.dadoComum.sortear();
        int valor2 = this.dadoComum.sortear();
        int resultado = valor1 + valor2;
        if (valor1 == valor2) {
            resultado *= 2;
        }
        return resultado;
    }
}
