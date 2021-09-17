public class Principal {

    public static void main(String[] args) {

        DadosDeGamao dadosDeGamao = new DadosDeGamao();
        DadosTriplos dadosTriplos = new DadosTriplos();

        for (int numeroDeRodadas = 1; numeroDeRodadas <= 100000; numeroDeRodadas *= 2) {

            JogoMalucoComSorteadores jogoMaluco = new JogoMalucoComSorteadores(
                    "JogadorDosDadosDeGamao", "JogadorDosDadosTriplos",
                    numeroDeRodadas, dadosDeGamao, dadosTriplos);

            jogoMaluco.jogar();

            System.out.println(jogoMaluco.obterResultadoUltimoJogo());
        }
    }
}
