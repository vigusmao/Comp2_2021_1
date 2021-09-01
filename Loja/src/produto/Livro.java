package produto;

import controle.Vendavel;

public class Livro extends Produto {

    private final static float PRECO_DEFAULT_POR_PAGINA = 0.01f;

    private int numeroDePaginas;

    private String titulo;

    private String autor;

    private String editora;

    private int anoPublicacao;

    public Livro(long codigoISBN, String titulo, String autor, String editora) {
        super(codigoISBN,
                String.format("Livro %s, de %s (editora %s)",
                        titulo, autor, editora));
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    @Override
    protected float getPrecoDefault() {
        return PRECO_DEFAULT_POR_PAGINA * this.numeroDePaginas;
    }
}
