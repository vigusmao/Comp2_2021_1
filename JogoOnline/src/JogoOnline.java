import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class JogoOnline {

    public static final int PONTOS_VITORIA = 1;
    public static final int PONTOS_EMPATE = 0;
    public static final int PONTOS_DERROTA = -1;

    private Random random;

//    private ArrayList<Jogador> jogadores;
    private HashMap<String, Jogador> jogadorByUsername;  // (nome convencional: valorByChave)


    public JogoOnline() {
        this.random = new Random();
//        this.jogadores = new ArrayList<>();
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

//        this.jogadores.add(novoJogador);
        this.jogadorByUsername.put(username, novoJogador);

        return novoJogador;
    }

    private Jogador encontrarJogador(String username) {
//        for (Jogador jogador : this.jogadores) {
//            if (jogador.getUsername().equals(username)) {
//                return jogador;
//            }
//        }
//        return null;
        return this.jogadorByUsername.get(username);
    }

    public boolean fazerLogin(String username, String senha) {
//        for (int i = 0; i < this.jogadores.size(); i++) {
//            Jogador jogador = this.jogadores.get(i);
//            if (jogador.getUsername().equals(username)) {
//                if (jogador.getSenha().equals(senha)) {
//                    jogador.setOnline(true);
//                    return true;
//                }
//                break;
//            }
//        }
//        return false;
//
//        // outro jeito...
//
//        for (Jogador jogador : this.jogadores) {      // for each
//            if (jogador.getUsername().equals(username)) {
//                if (jogador.getSenha().equals(senha)) {
//                    jogador.setOnline(true);
//                    return true;
//                }
//                break;
//            }
//        }
//        return false;

        // melhor jeito (uma vez que já temos um método para localizar o jogador)...

        Jogador jogador = encontrarJogador(username);
        if (jogador != null) {
            if (jogador.getSenha().equals(senha)) {
                jogador.setOnline(true);
                return true;
            }
        }
        return false;

    }

    public void fazerLogout(String username) {
        Jogador jogador = encontrarJogador(username);
        if (jogador != null) {
            jogador.setOnline(false);
        }
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

    public void encerrarPartida(Partida partida, int resultado) {
        if (partida.getResultado() != Partida.PARTIDA_EM_ANDAMENTO) {
            // a partida não está em andamento, não posso atribuir resultado!
            return;  // ToDo lançar uma exceção!!!
        }

        if (resultado != Partida.EMPATE &&
                resultado != Partida.VITORIA_JOGADOR_1 &&
                resultado != Partida.VITORIA_JOGADOR_2) {
            // resultado inválido, não faz nada!
            return;  // ToDo lançar uma excecão!!!
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

//        do {
//            Jogador adversario = this.jogadores.get(posicaoCorrente);
//            if (adversario.isOnline() &&
//                    !adversario.isJogando() &&
//                    !adversario.equals(solicitante)) {
//                return adversario;
//            }
//            posicaoCorrente++;
//            if (posicaoCorrente == this.jogadores.size()) {
//                posicaoCorrente = 0;
//            }
//        } while (posicaoCorrente != posicaoInicial);

        return null;
    }
}
