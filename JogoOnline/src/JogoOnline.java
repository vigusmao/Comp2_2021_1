import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class JogoOnline {

    public static final int PONTOS_VITORIA = 1;
    public static final int PONTOS_EMPATE = 0;
    public static final int PONTOS_DERROTA = -1;

    private Random random;

    private HashMap<String, Jogador> jogadorByUsername;  // (nome convencional: valorByChave)


    public JogoOnline() {
        this.random = new Random();
        this.jogadorByUsername = new HashMap<>();
    }

    /**
     * Cadastra um novo jogador. Se já existir, não cadastra outro igual.
     *
     * @param username
     * @param senha
     *
     * @return O novo jogador cadastrado.
     *         Ou null, caso o cadastro não tenha sido feito.
     */
    public Jogador cadastrarJogador(String username, String senha) {
        if (encontrarJogador(username) != null) {
            return null;  // nada a fazer, pois o jogador já existe!
        }

        // jogador não existe, será cadastrado
        Jogador novoJogador = new Jogador(username);
        novoJogador.setSenha(senha);

        this.jogadorByUsername.put(username, novoJogador);

        return novoJogador;
    }

    private Jogador encontrarJogador(String username) {
        return this.jogadorByUsername.get(username);
    }

    public boolean fazerLogin(String username, String senha) {
        Jogador jogador = encontrarJogador(username);
        if (jogador != null) {
            if (jogador.getSenha().equals(senha)) {
                jogador.setOnline(true);
                return true;
            }
        }
        return false;
    }

    public void fazerLogout(Jogador jogador) {
        jogador.setOnline(false);
    }

    /**
     * Inicia uma partida.
     *
     * @param jogador1 O primeiro jogador
     * @param jogador2 O segundo jogador
     *
     * @return O novo objeto Partida criado;
     *         ou null caso um dos jogadores esteja offline ou já envolvido em outra partida
     */
    public Partida iniciarPartida(Jogador jogador1, Jogador jogador2) {
        if (jogador1 == null || jogador2 == null ||
                !jogador1.isOnline() || jogador1.isJogando() ||
                !jogador2.isOnline() || jogador2.isJogando()) {
            return null;
        }

        Partida novaPartida = new Partida(jogador1, jogador2);
        jogador1.setJogando(true);
        jogador2.setJogando(true);

        novaPartida.setResultado(Partida.PARTIDA_EM_ANDAMENTO);

        return novaPartida;
    }

    /**
     * Encerra uma partida em andamento.
     *
     * @param partida Uma partida em andamento
     * @param resultado O resultado da partida que será encerrada:
     *                  0 (empate), 1 (vitória do jogador 1) ou 2 (vitória do jogador 2)
     */
    public void encerrarPartida(Partida partida, int resultado) {
        if (partida.getResultado() != Partida.PARTIDA_EM_ANDAMENTO) {
            // a partida não está em andamento, não posso atribuir resultado!
            throw new RuntimeException("Partida já encerrada!");
        }

        if (resultado != Partida.EMPATE &&
                resultado != Partida.VITORIA_JOGADOR_1 &&
                resultado != Partida.VITORIA_JOGADOR_2) {
            throw new IllegalArgumentException("Resultado inválido");
            // a IllegalArgumentException é uma RuntimeException (herança)
        }

        partida.setResultado(resultado);

        Jogador jogador1 = partida.getJogador1();
        Jogador jogador2 = partida.getJogador2();

        jogador1.adicionarPartidaJogada(partida);
        jogador2.adicionarPartidaJogada(partida);

        jogador1.setJogando(false);
        jogador2.setJogando(false);
    }

    /**
     * Escolhe um adversário aleatório que esteja online e não esteja jogando,
     * distinto do jogador solicitante.
     *
     * @param solicitante o jogador solicitante
     *
     * @return O adversário escolhido, se encontrar algum;
     *         ou null, caso não encontre nenhum que atenda às condições
     */
    public Jogador escolherAdversario(Jogador solicitante) {
        int numeroAleatorio = this.random.nextInt(this.jogadorByUsername.size());

        int cont = 0;

        for (Jogador adversario : this.jogadorByUsername.values()) {
            if (cont >= numeroAleatorio) {
                if (adversario.isOnline() &&
                    !adversario.isJogando() &&
                    !adversario.equals(solicitante)) {
                    return adversario;
                }
            }
            cont++;
        }
        return null;
    }
}
