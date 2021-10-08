import java.util.*;

public class JogoOnline {

    static final int PONTOS_VITORIA = 1;
    static final int PONTOS_EMPATE = 0;
    static final int PONTOS_DERROTA = -1;

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

    /**
     * Retorna uma lista ordenada de jogadores, do jogador de maior pontuação
     * até o jogador de menor pontuação.
     *
     * @return a lista ordenada desejada
     */
    public List<Jogador> obterRanking() {

        ArrayList<Jogador> listaDeJogadores = new ArrayList<>();
        listaDeJogadores.addAll(this.jogadorByUsername.values());

        // um jeito de ordenar
        ComparadorDeJogadoresPorPontuacao comparador = new ComparadorDeJogadoresPorPontuacao();
        listaDeJogadores.sort(comparador);  // precisamos passar um Comparator<Jogador>

        // queremos em ordem DECRESCENTE!!! Por isso vou inverter a lista!
        Collections.reverse(listaDeJogadores);

        // segundo jeito de ordenar uma lista
//        Collections.sort(listaDeJogadores);  aqui Jogador teria que implementar a interface Comparable<Jogador>

        return listaDeJogadores;
    }

    /**
     * Retorna uma lista de jogadores em ordem lexicográfica de username.
     *
     * @return a lista ordenada desejada
     */
    public List<Jogador> obterJogadoresEmOrdemAlfabetica() {

        ArrayList<Jogador> listaDeJogadores = new ArrayList<>();
        listaDeJogadores.addAll(this.jogadorByUsername.values());

        // um jeito de ordenar
        ComparadorDeJogadoresLexicografico comparador = new ComparadorDeJogadoresLexicografico();
        listaDeJogadores.sort(comparador);  // precisamos passar um Comparator<Jogador>

        return listaDeJogadores;
    }

    private Jogador encontrarJogador(String username) {
        return this.jogadorByUsername.get(username);
    }

    /**
     * Faz o login do usuário.
     * @param username o nome do usuário
     * @param senha a senha
     *
     * @throws UsuarioInexistenteException se usuário não cadastrado
     * @throws SenhaInvalidaException se senha incorreta
     */
    public void fazerLogin(String username, String senha)
            throws UsuarioInexistenteException, SenhaInvalidaException {

        Jogador jogador = encontrarJogador(username);
        if (jogador == null) {
            throw new UsuarioInexistenteException();
        }

        if (!jogador.getSenha().equals(senha)) {
            throw new SenhaInvalidaException();
        }

        jogador.setOnline(true);
    }

    /**
     * Desloga o usuário
     *
     * @param jogador um Jogador que esteja online
     *                (caso não esteja, uma RuntimeException será lançada)
     */
    public void fazerLogout(Jogador jogador) {
        if (!jogador.isOnline()) {
            throw new RuntimeException("Usuário não logado");
        }
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

        novaPartida.setResultado(Partida.Resultado.PARTIDA_EM_ANDAMENTO);

        return novaPartida;
    }

    /**
     * Encerra uma partida em andamento.
     *
     * @param partida Uma partida em andamento
     * @param resultado O resultado da partida que será encerrada:
     *                  0 (empate), 1 (vitória do jogador 1) ou 2 (vitória do jogador 2)
     */
    public void encerrarPartida(Partida partida, Partida.Resultado resultado) {
        if (partida.getResultado() != Partida.Resultado.PARTIDA_EM_ANDAMENTO) {
            // a partida não está em andamento, não posso atribuir resultado!
            throw new RuntimeException("Partida já encerrada!");
        }

        if (resultado == Partida.Resultado.PARTIDA_NAO_INICIADA ||
                resultado == Partida.Resultado.PARTIDA_EM_ANDAMENTO) {
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
