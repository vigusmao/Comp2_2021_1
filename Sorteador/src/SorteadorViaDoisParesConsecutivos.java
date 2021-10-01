public class SorteadorViaDoisParesConsecutivos implements Sorteador<Integer> {

    private DadoGenerico<Integer> dado;

    public SorteadorViaDoisParesConsecutivos(DadoGenerico<Integer> dado) {
        this.dado = dado;
    }

    @Override
    public Integer sortear() {
        int resultadoA = this.dado.sortear();
        int resultadoB = this.dado.sortear();
        int resultadoC = this.dado.sortear();
        int resultadoD = this.dado.sortear();

        return resultadoA == resultadoB && resultadoC == resultadoD ? 1 : 0;
    }
}
