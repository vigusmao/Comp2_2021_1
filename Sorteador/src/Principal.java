import java.util.HashMap;
import java.util.Map;

public class Principal {

    private static final int CONT_REPETICOES_POR_SIMULACAO = 100_000;

    private static DadosDeGamao dadosDeGamao = new DadosDeGamao();
    private static DadosTriplos dadosTriplos = new DadosTriplos();

    private static DadoGenerico<Integer> dadoGenerico = construirDadoGenerico();

    private static SorteadorViaDoisParesConsecutivos sorteadorPares =
            new SorteadorViaDoisParesConsecutivos(dadoGenerico);

    private static SorteadorViaTrio sorteadorTrio =
            new SorteadorViaTrio(dadoGenerico);

//    PRIMEIRA SIMULAÇÃO: usando dado comum, honesto
//
//    private static DadoGenerico<Integer> construirDadoGenerico() {
//        return new DadoComum();
//    }


//    SEGUNDA SIMULAÇÃO: usando um dado viciado
//
//    private static DadoGenerico<Integer> construirDadoGenerico() {
//
//        Map<Integer, Integer> mapinha = new HashMap<>();
//        mapinha.put(1, 2);
//        mapinha.put(2, 2);
//        mapinha.put(3, 2);
//        mapinha.put(4, 2);
//        mapinha.put(5, 2);
//        mapinha.put(6, 90);  // dado viciado, quase sempre cai "6"
//
//        return new DadoGenerico<>(mapinha);
//    }


//    TERCEIRA SIMULAÇÃO: usando uma moeda
//
    private static DadoGenerico<Integer> construirDadoGenerico() {

        Map<Integer, Integer> mapinha = new HashMap<>();
        mapinha.put(1, 15);
        mapinha.put(2, 85);   // moeda desonesta

        return new DadoGenerico<>(mapinha);
    }

    private static void obterPercentuaisEmpiricos(int numeroDeRodadasPorPartida) {

        JogoMalucoComSorteadores<
                SorteadorViaDoisParesConsecutivos,
                SorteadorViaTrio> jogoMaluco;

        jogoMaluco = new JogoMalucoComSorteadores<>(
                "JogadorDosParesConsecutivos", "JogadorDosTrios",
                numeroDeRodadasPorPartida,
                sorteadorPares,
                sorteadorTrio);

        for (int i = 0; i < CONT_REPETICOES_POR_SIMULACAO; i++) {
            jogoMaluco.jogar();
        }

        System.out.println(String.format("\n\n" +
                "      Para partidas com %d rodada(s):\n" +
                "      Vitórias do %s: %f%%\n" +
                "      Vitórias do %s: %f%%\n" +
                "      Empates: %f%%",
                numeroDeRodadasPorPartida,
                jogoMaluco.getNomeJogador1(),
                jogoMaluco.getPercentualVitoriasJogador1(),
                jogoMaluco.getNomeJogador2(),
                jogoMaluco.getPercentualVitoriasJogador2(),
                jogoMaluco.getPercentualEmpates()));
    }

    public static void main(String[] args) {

        for (int numeroDeRodadas = 1; numeroDeRodadas <= 1000000; numeroDeRodadas++) {
            obterPercentuaisEmpiricos(numeroDeRodadas);
        }
    }
}
