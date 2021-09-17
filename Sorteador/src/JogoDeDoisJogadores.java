import java.util.ArrayList;
import java.util.Date;

public abstract class JogoDeDoisJogadores {

    public static final int EMPATE = 0;
    public static final int VITORIA_JOGADOR_1 = 1;
    public static final int VITORIA_JOGADOR_2 = 2;

    private final String nomeJogo;
    private final String nomeJogador1;
    private final String nomeJogador2;
    private final int numeroDeRodadas;

    private ArrayList<Partida> historicoResultados;

    public JogoDeDoisJogadores(
            String nomeJogo, String nomeJogador1, String nomeJogador2,
            int numeroDeRodadas) {

        this.nomeJogo = nomeJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;

        this.historicoResultados = new ArrayList<>();
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public String getNomeJogador1() {
        return nomeJogador1;
    }

    public String getNomeJogador2() {
        return nomeJogador2;
    }

    public int getNumeroDeRodadas() {
        return numeroDeRodadas;
    }

    public int jogar() {

        int contVitoriasJogador1 = 0;
        int contVitoriasJogador2 = 0;

        for (int rodada = 1; rodada <= this.numeroDeRodadas; rodada++) {
            int resultadoDaRodada = executarRodadaDoJogo();
            switch (resultadoDaRodada) {
                case 1:
                    contVitoriasJogador1++;
                    break;
                case 2:
                    contVitoriasJogador2++;
                    break;
            }
        }

        // armazena a partida no histórico
        Partida partidaTerminada = new Partida(
                new Date(),
                contVitoriasJogador1,
                contVitoriasJogador2,
                this.numeroDeRodadas - contVitoriasJogador1 - contVitoriasJogador2);
        this.historicoResultados.add(partidaTerminada);

        return partidaTerminada.getResultado();
    }

    /**
     * @return o resultado da última partida;
     *         ou null, se nennuma partida jamais foi jogada
     */
    public String obterResultadoUltimoJogo() {

        if (this.historicoResultados.isEmpty()) {
            return null;
        }

        Partida ultimaPartidaJogada = this.historicoResultados.get(
                this.historicoResultados.size() - 1);

        int contRodadasVencidasJogador1 = ultimaPartidaJogada.contRodadasVencidasJogador1;
        int contRodadasVencidasJogador2 = ultimaPartidaJogada.contRodadasVencidasJogador1;

        if (contRodadasVencidasJogador1 == contRodadasVencidasJogador2) {
            return String.format("O jogo terminou em empate após %d rodadas.",
                    this.numeroDeRodadas);
        } else {

            String nomeVencedor;
            int pontosVencedor;
            int pontosPerdedor;

            if (contRodadasVencidasJogador1 > contRodadasVencidasJogador2) {
                nomeVencedor = this.nomeJogador1;
                pontosVencedor = contRodadasVencidasJogador1;
                pontosPerdedor = contRodadasVencidasJogador2;
            } else {
                nomeVencedor = this.nomeJogador2;
                pontosVencedor = contRodadasVencidasJogador2;
                pontosPerdedor = contRodadasVencidasJogador1;
            }

            return String.format("O jogador %s venceu o jogo por %d a %d",
                    nomeVencedor, pontosVencedor, pontosPerdedor);
        }
    }

    /**
     * Returna um float entre 0 e 100 indicando o percentual
     * de vitórias do Jogador 1 em todos os tempos.
     *
     * @return o percentual, como um float entre 0 (0%) e 100 (100%)
     */
    public float getPercentualVitoriasJogador1() {
        return 0;  // ToDo IMPLEMENT ME!!!!
    }

    /**
     * Returna um float entre 0 e 100 indicando o percentual
     * de vitórias do Jogador 2 em todos os tempos.
     *
     * @return o percentual, como um float entre 0 (0%) e 100 (100%)
     */
    public float getPercentualVitoriasJogador2() {
        return 0;  // ToDo IMPLEMENT ME!!!!
    }

    /**
     * Returna um float entre 0 e 100 indicando o percentual
     * de empates em todos os tempos.
     *
     * @return o percentual, como um float entre 0 (0%) e 100 (100%)
     */
    public float getPercentualEmpates() {
        return 0;  // ToDo IMPLEMENT ME!!!!
    }

    protected abstract int executarRodadaDoJogo();

    /**
     * Inner class auxiliar para armazenar resultados de partidas
     */
    private class Partida {

        final Date data;
        final int contRodadasVencidasJogador1;
        final int contRodadasVencidasJogador2;
        final int contEmpates;

        Partida(Date data,
                int contRodadasVencidasJogador1,
                int contRodadasVencidasJogador2,
                int contEmpates) {
            this.data = data;
            this.contRodadasVencidasJogador1 = contRodadasVencidasJogador1;
            this.contRodadasVencidasJogador2 = contRodadasVencidasJogador2;
            this.contEmpates = contEmpates;
        }

        int getResultado() {
            // retorno o resultado desta partida
            if (contRodadasVencidasJogador1 > contRodadasVencidasJogador2) {
                return VITORIA_JOGADOR_1;
            } else if (contRodadasVencidasJogador1 < contRodadasVencidasJogador2) {
                return VITORIA_JOGADOR_2;
            }
            return EMPATE;
        }
    }
}
