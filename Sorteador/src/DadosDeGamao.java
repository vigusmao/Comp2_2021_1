public class DadosDeGamao implements Sorteador {

    private Dado dadoComum;

    public DadosDeGamao() {
        this.dadoComum = new Dado();
    }


    @Override
    public int sortear() {
        int valor1 = this.dadoComum.sortear();
        int valor2 = this.dadoComum.sortear();
        int resultado = valor1 + valor2;
        if (valor1 == valor2) {
            resultado *= 2;
        }
        return resultado;
    }
}
