package modelo;

import java.util.Objects;

public class Livro {

    private String titulo;

    private String autor;

    private int anodePublicacao;

    public Livro(String titulo, String autor, int anoDePublicacao) {

        this.titulo = titulo;

        this.autor = autor;

        this.anodePublicacao = anoDePublicacao;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public int getAnoDePublicacao() {
        return this.anodePublicacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return anodePublicacao == livro.anodePublicacao && Objects.equals(titulo, livro.titulo) && Objects.equals(autor, livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, anodePublicacao);
    }
}
