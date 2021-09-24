public class JogoMalucoComSorteadores<S1 extends Sorteador<Integer>, S2 extends Sorteador<Integer>>
        extends JogoDeDoisJogadores {

    /**
     * Sorteador que será utilizada, em cada rodada, pelo jogador 1.
     */
    private S1 sorteador1;

    /**
     * Sorteador que será utilizada, em cada rodada, pelo jogador 2.
     */
    private S2 sorteador2;

    public JogoMalucoComSorteadores(String nomeJogador1, String nomeJogador2,
                                    int numeroDeRodadas,
                                    S1 sorteador1, S2 sorteador2) {
        super("Jogo Maluco", nomeJogador1, nomeJogador2, numeroDeRodadas);
        this.sorteador1 = sorteador1;
        this.sorteador2 = sorteador2;
    }

    @Override
    protected Resultado executarRodadaDoJogo() {
        int valorSorteadoParaOJogador1 = sorteador1.sortear();
        int valorSorteadoParaOJogador2 = sorteador2.sortear();

        if (valorSorteadoParaOJogador1 > valorSorteadoParaOJogador2) {
            return Resultado.VITORIA_JOGADOR_1;
        } else if (valorSorteadoParaOJogador1 < valorSorteadoParaOJogador2) {
            return Resultado.VITORIA_JOGADOR_2;
        }
        return Resultado.EMPATE;
    }
}
