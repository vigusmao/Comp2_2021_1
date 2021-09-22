import java.util.ArrayList;
import java.util.Date;

public abstract class JogoDeDoisJogadores {

    enum Resultado {
        EMPATE,
        VITORIA_JOGADOR_1,
        VITORIA_JOGADOR_2
    }

    private final String nomeJogo;
    private final String nomeJogador1;
    private final String nomeJogador2;
    private final int numeroDeRodadas;

    private ArrayList<Partida> historicoResultados;

    private int contPartidasVencidasPeloJogador1;
    private int contPartidasVencidasPeloJogador2;
    private int contPartidasEmpatadas;

    public JogoDeDoisJogadores(
            String nomeJogo, String nomeJogador1, String nomeJogador2,
            int numeroDeRodadas) {

        this.nomeJogo = nomeJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;

        this.historicoResultados = new ArrayList<>();

        this.contPartidasVencidasPeloJogador1 = 0;
        this.contPartidasVencidasPeloJogador2 = 0;
        this.contPartidasEmpatadas = 0;
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

    public Resultado jogar() {

        int contVitoriasJogador1 = 0;
        int contVitoriasJogador2 = 0;

        for (int rodada = 1; rodada <= this.numeroDeRodadas; rodada++) {
            Resultado resultadoDaRodada = executarRodadaDoJogo();
            switch (resultadoDaRodada) {
                case VITORIA_JOGADOR_1:
                    contVitoriasJogador1++;
                    break;
                case VITORIA_JOGADOR_2:
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

        Resultado resultadoFinalDaPartida = partidaTerminada.getResultado();

        switch (resultadoFinalDaPartida) {
            case VITORIA_JOGADOR_1:
                this.contPartidasVencidasPeloJogador1++;
                break;
            case VITORIA_JOGADOR_2:
                this.contPartidasVencidasPeloJogador2++;
                break;
            case EMPATE: default:
                this.contPartidasEmpatadas++;
        }

        return resultadoFinalDaPartida;
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
        int contRodadasVencidasJogador2 = ultimaPartidaJogada.contRodadasVencidasJogador2;

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
    public double getPercentualVitoriasJogador1() {
        return 100.0 * this.contPartidasVencidasPeloJogador1 / this.historicoResultados.size();
    }

    /**
     * Returna um float entre 0 e 100 indicando o percentual
     * de vitórias do Jogador 2 em todos os tempos.
     *
     * @return o percentual, como um float entre 0 (0%) e 100 (100%)
     */
    public double getPercentualVitoriasJogador2() {
        return 100.0 * this.contPartidasVencidasPeloJogador2 / this.historicoResultados.size();
    }

    /**
     * Returna um float entre 0 e 100 indicando o percentual
     * de empates em todos os tempos.
     *
     * @return o percentual, como um float entre 0 (0%) e 100 (100%)
     */
    public double getPercentualEmpates() {
        return 100.0 * this.contPartidasEmpatadas / this.historicoResultados.size();
    }

    protected abstract Resultado executarRodadaDoJogo();

    /**
     * Inner class auxiliar para armazenar resultados de partidas
     */
    private class Partida {

        final Date data;
        final int contRodadasVencidasJogador1;
        final int contRodadasVencidasJogador2;
        final int contRodadasEmpatadas;

        Partida(Date data,
                int contRodadasVencidasJogador1,
                int contRodadasVencidasJogador2,
                int contRodadasEmpatadas) {
            this.data = data;
            this.contRodadasVencidasJogador1 = contRodadasVencidasJogador1;
            this.contRodadasVencidasJogador2 = contRodadasVencidasJogador2;
            this.contRodadasEmpatadas = contRodadasEmpatadas;
        }

        Resultado getResultado() {
            // retorna o resultado desta partida
            if (contRodadasVencidasJogador1 > contRodadasVencidasJogador2) {
                return Resultado.VITORIA_JOGADOR_1;
            } else if (contRodadasVencidasJogador1 < contRodadasVencidasJogador2) {
                return Resultado.VITORIA_JOGADOR_2;
            }
            return Resultado.EMPATE;
        }
    }
}
