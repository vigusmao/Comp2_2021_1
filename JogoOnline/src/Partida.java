public class Partida {

    enum Resultado {
        VITORIA_JOGADOR_1,
        VITORIA_JOGADOR_2,
        EMPATE,
        PARTIDA_EM_ANDAMENTO,
        PARTIDA_NAO_INICIADA,
        PARTIDA_CANCELADA
    }

    private final Jogador jogador1;

    private final Jogador jogador2;

    private Resultado resultado;

    public Partida(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.resultado = Resultado.PARTIDA_NAO_INICIADA;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    /**
     * Retorna o resultado da partida.
     *
     * @return o resultado da partida (enum Resultado)
     */
    public Resultado getResultado() {
        return this.resultado;
    }
}
