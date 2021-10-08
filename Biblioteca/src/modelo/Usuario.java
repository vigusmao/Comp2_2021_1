package modelo;

import java.util.*;

public class Usuario {

    private String nome;

    private long cpf;

    private String endereco;

    private TipoUsuario nivel;

    private Map<Livro, Integer> copiasDevidasByLivro;

    private int totalDevolucoes;

    public Usuario(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.nivel = TipoUsuario.INICIANTE;
        this.totalDevolucoes = 0;
        this.copiasDevidasByLivro = new HashMap<>();
    }

    public long getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        return;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
        return;
    }

    public void receberLivroEmprestado(Livro livro) {
        this.copiasDevidasByLivro.put(livro,
                this.copiasDevidasByLivro.getOrDefault(livro, 0) + 1);
    }

    public void devolverLivro(Livro livro) {
        Integer numeroCopiasDoLivroComOUsuario =
                this.copiasDevidasByLivro.get(livro);

        if (numeroCopiasDoLivroComOUsuario == null) {
            return;  // nada a fazer
        }

        // devolve o livro
        if (numeroCopiasDoLivroComOUsuario == 1) {
            this.copiasDevidasByLivro.remove(livro);
        } else {
            this.copiasDevidasByLivro.put(livro,
                    numeroCopiasDoLivroComOUsuario - 1);
        }
        this.totalDevolucoes++;
        checarNivelUsuario();
    }

    private void checarNivelUsuario() {
        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
            final int minDevolucoesParaIngressarNoNivel = tipoUsuario.getMinDevolucoesParaIngressarNoNivel();
            if (this.totalDevolucoes >= minDevolucoesParaIngressarNoNivel &&
                 minDevolucoesParaIngressarNoNivel > this.nivel.getMinDevolucoesParaIngressarNoNivel()) {
                this.nivel = tipoUsuario;
            }
        }

        if (this.totalDevolucoes >= TipoUsuario.ILIMITADO.getMinDevolucoesParaIngressarNoNivel()) {
            this.nivel = TipoUsuario.ILIMITADO;
        } else if (this.totalDevolucoes >= TipoUsuario.EXPERIENTE.getMinDevolucoesParaIngressarNoNivel()) {
            this.nivel = TipoUsuario.EXPERIENTE;
        }
    }

    public TipoUsuario getNivel() {
        return this.nivel;
    }

    public int getCopiasEmprestadas(Livro livro) {
        return this.copiasDevidasByLivro.getOrDefault(livro, 0);
    }

    public int getTotalCopiasEmprestadas() {
        int total = 0;
        for (int copiasEmprestadas : this.copiasDevidasByLivro.values()) {
            total += copiasEmprestadas;
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return cpf == usuario.cpf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
