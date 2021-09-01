package teste;

import controle.Loja;
import produto.Livro;
import produto.Produto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LojaTest {

    private Loja loja;
    private Produto produto;

    @Before
    public void setUp() {
        loja = new Loja();
    }

    @Test
    public void testarCadastroDeProduto() {

        produto = new Livro(1234, "O Pequeno Príncipe",
                "Saint-Exupéry", "Editora Qualquer");

        loja.incluirVendavelNoEstoque(produto, 5);
        assertEquals(5, loja.getQuantidadeEmEstoque(produto));

        loja.incluirVendavelNoEstoque(produto, 3);
        assertEquals(8, loja.getQuantidadeEmEstoque(produto));
    }

}