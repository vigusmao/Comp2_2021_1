package controle;

import produto.Livro;

public class Livraria extends Loja<Livro> {

    private Pessoa livreiroResponsavel;

    public Livraria(Transportador transportador) {
        super(transportador);
    }

    public Pessoa getLivreiroResponsavel() {
        return livreiroResponsavel;
    }

    // ......
}
