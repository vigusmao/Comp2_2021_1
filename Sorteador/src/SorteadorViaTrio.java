public class SorteadorViaTrio implements Sorteador<Integer> {

    private DadoGenerico<Integer> dado;

    public SorteadorViaTrio(DadoGenerico<Integer> dado) {
        this.dado = dado;
    }

    @Override
    public Integer sortear() {
        int resultadoA = this.dado.sortear();
        int resultadoB = this.dado.sortear();
        int resultadoC = this.dado.sortear();

        return resultadoA == resultadoB && resultadoA == resultadoC ? 1 : 0;
    }
}
