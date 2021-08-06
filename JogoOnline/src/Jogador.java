import java.util.ArrayList;
import java.util.Objects;

public class Jogador {

    public static final String SENHA_PADRAO = "1234";
    public static final int PONTUACAO_INICIAL = 1000;

    private String username;
    private String senha;
    private int pontuacao;

    private boolean online;
    private boolean jogando;

    private ArrayList<Partida> minhasPartidas;

    public Jogador(String username) {
        this(username, SENHA_PADRAO);
    }

    public Jogador(String username, String senha) {
        this.username = username;
        this.senha = senha;
        this.pontuacao = PONTUACAO_INICIAL;
        this.online = false;
        this.jogando = false;
        this.minhasPartidas = new ArrayList<>();
    }

    public void adicionarPartidaJogada(Partida partida) {
        this.minhasPartidas.add(partida);

        switch (partida.getResultado()) {
            case Partida.VITORIA_JOGADOR_1:
                if (partida.getJogador1().equals(this)) {
                    this.pontuacao += JogoOnline.PONTOS_VITORIA;
                } else {
                    this.pontuacao += JogoOnline.PONTOS_DERROTA;
                }
                break;

            case Partida.VITORIA_JOGADOR_2:
                if (partida.getJogador2().equals(this)) {
                    this.pontuacao += JogoOnline.PONTOS_VITORIA;
                } else {
                    this.pontuacao += JogoOnline.PONTOS_DERROTA;
                }
                break;

            case Partida.EMPATE:
                this.pontuacao += JogoOnline.PONTOS_EMPATE;
        }
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getUsername() {
        return this.username;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        // ToDo: validar senha segunda as regras do sistema para senhas permitidas

        this.senha = senha;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isJogando() {
        return jogando;
    }

    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(username, jogador.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
