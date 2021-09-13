package teste;

import controle.*;
import exception.EnderecoInvalidoException;
import exception.EstoqueInsuficienteException;
import exception.PagamentoException;
import produto.Livro;
import org.junit.Before;
import org.junit.Test;
import servico.Servico;
import veiculo.Bicicleta;
import veiculo.Caminhao;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LojaTest {

    private Loja<Livro> livraria;
    private Bicicleta bicicleta;

    private Livro livro;
    private Servico servico;

    private Usuario usuario;

    @Before
    public void setUp() {
        bicicleta = new Bicicleta();

        livraria = new Loja<>(bicicleta);  // quero vender APENAS livros!!!!

        livro = new Livro(1234, "O Pequeno Príncipe",
                "Saint-Exupéry", "Editora Qualquer");
        servico = new Servico(121232, "bombeiro hidráulico");
    }

    @Test
    public void testarFuncionamentoBasico()
            throws PagamentoException, EnderecoInvalidoException, EstoqueInsuficienteException {

        usuario = new Usuario(123446, "Vinicius");
        usuario.setEndereco("Rua Blah, 122");

        livraria.incluirVendavel(livro, 5);
        assertEquals(5, livraria.getQuantidadeEmEstoque(livro));

        livraria.incluirVendavel(livro, 3);
        assertEquals(8, livraria.getQuantidadeEmEstoque(livro));

        // #1: gostaríamos que as três linhas abaixo sequer compilassem!!!
//        livraria.incluirVendavel(servico, 1);
//        livraria.incluirVendavel(bicicleta, 1);
//        livraria.efetuarVenda(servico, 1, usuario);

        livraria.efetuarVenda(livro, 1, usuario);

        // #2: gostaríamos de não ter que usar esse typecast!!!!!!!
//        Livro livroDesejado = (Livro) livraria.encontrarVendavel(1234);
        Livro livroDesejado = livraria.encontrarVendavel(1234);
        assertEquals(livro, livroDesejado);

        System.out.println(livroDesejado.getAnoPublicacao());
        System.out.println(livroDesejado.getNumeroDePaginas());
    }
}