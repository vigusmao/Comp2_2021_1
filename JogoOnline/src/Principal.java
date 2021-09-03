import java.util.Random;

public class Principal {

    public static void main(String[] args) {


        Random random = new Random();
        final int TOTAL_JOGADORES = 400_000;
        final int LOGINS = 100_000;

        JogoOnline jogo = new JogoOnline();

        long inicio = System.currentTimeMillis();

        for (int i = 0; i < TOTAL_JOGADORES; i++) {
            final int numero = random.nextInt();
            String username = "Jogador" + numero;
            jogo.cadastrarJogador(username, "" + numero);
        }

        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("\n%d jogadores cadastrados (tempo: %.3f segundos)",
                TOTAL_JOGADORES, duracao / 1000.0);

        inicio = System.currentTimeMillis();

        int loginsBemSucedidos = 0;
        for (int i = 1; i <= LOGINS; i++) {
            String username = "Jogador" + i;
            try {
                jogo.fazerLogin(username, "" + i);
                loginsBemSucedidos++;
            } catch (Exception e) {
                // login falhou... tudo bem, vamos tentar outro usuário
            }
        }

        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("\nDe %d logins, %d foram bem sucedidos (tempo: %.3f segundos)",
                LOGINS, loginsBemSucedidos, duracao / 1000.0);
    }
}
