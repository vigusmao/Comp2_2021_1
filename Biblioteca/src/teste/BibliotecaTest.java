package teste;

import excecao.DevolucaoInvalidaException;
import excecao.LimiteEmprestimosExcedidoException;
import excecao.UsuarioNaoCadastradoException;
import controle.Biblioteca;
import modelo.Livro;
import modelo.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BibliotecaTest {

    private Biblioteca biblioteca;

    private long cpfUsuario1;
    private Usuario usuario1;
    private long cpfUsuarioFantasma;
    private Usuario usuarioFantasma;

    private Livro livroRaro;
    private Livro livroAbundante;
    private Livro livroInexistente;

    @Before
    public void setUp() {
        cpfUsuario1 = 1111L;
        usuario1 = new Usuario("Fulano", cpfUsuario1);

        cpfUsuarioFantasma = 9876L;
        usuarioFantasma = new Usuario("Usuário Fantasma", cpfUsuarioFantasma);

        livroRaro = new Livro("Algum modelo.Livro", "Algum autor", 1900);
        livroAbundante = new Livro("Outro livro", "Outro autor", 1980);
        livroInexistente = new Livro("modelo.Livro Que Nunca Foi Adquirido", "Autor Desconhecido", 2000);

        biblioteca = new Biblioteca();
        biblioteca.cadastrarUsuario(usuario1);
        biblioteca.incluirLivroNoAcervo(livroRaro, 1);  // importante incluir uma única unidade deste livro --- não mudar!
        biblioteca.incluirLivroNoAcervo(livroAbundante, 5);
    }

    @Test
    public void testeAdquirirLivros() {
        Livro novoLivro = new Livro("Um modelo.Livro Recém-Adquirido", "Algum autor", 2018);
        biblioteca.incluirLivroNoAcervo(novoLivro, 10);
        assertEquals("A aquisição de um livro deve atualizar a quantidade daquele livro na estante",
                10, biblioteca.getQuantidadeDeLivrosNaEstante(novoLivro));
        assertEquals("A aquisição de um livro deve atualizar a quantidade total de livros na estante",
                16, biblioteca.getQuantidadeDeLivrosNaEstante());
    }

    @Test
    public void testeGetQuantidadeDeLivrosNaEstanteParaLivroInexistente() {
        assertEquals("A quantidade na estante de um livro inexistente deve ser reteornada como zero",
                0, biblioteca.getQuantidadeDeLivrosNaEstante(livroInexistente));
    }

    @Test
    public void testeGetUsuario() {
        assertEquals("Um usuário cadastrado deve ser encontrável a partir de seu CPF",
                usuario1, biblioteca.getUsuario(cpfUsuario1));
    }

    @Test
    public void testeChavePrimariaUsuario() {
        Usuario novaInstanciaDoMesmoUsuario = new Usuario("Qualquer Nome", cpfUsuario1);
        assertEquals("Um usuário deve ser identificado exclusivamente pelo seu CPF",
                novaInstanciaDoMesmoUsuario, usuario1);
    }

    @Test
    public void testeCadastrarNovoUsuario() {
        long cpfUsuario2 = 2222L;
        Usuario usuario2 = new Usuario("Beltrano", cpfUsuario2);
        biblioteca.cadastrarUsuario(usuario2);
        assertEquals("O cadastro de um novo usuario deve atualizar o total de usuários cadastrados",
                2, biblioteca.getTotalDeUsuariosCadastrados());
    }

    @Test
    public void testeCadastrarUsuarioJaCadastrado() {
        String novoNome = "Fulano de Tal";
        String novoEndereco = "Novo endereco";
        Usuario usuarioRepetido = new Usuario(novoNome, cpfUsuario1);
        usuarioRepetido.setEndereco(novoEndereco);
        biblioteca.cadastrarUsuario(usuarioRepetido);
        assertEquals("O cadastro de um usuario já cadastrado não deve atualizar o total de usuários cadastrados",
                1, biblioteca.getTotalDeUsuariosCadastrados());
        Usuario usuarioRetornadoPelaBiblioteca = biblioteca.getUsuario(cpfUsuario1);
        assertEquals("O cadastro de um usuário já cadastrado deve atualizar seu nome",
                novoNome, usuarioRetornadoPelaBiblioteca.getNome());
        assertEquals("O cadastro de um usuário já cadastrado deve atualizar seu endereço",
                novoEndereco, usuarioRetornadoPelaBiblioteca.getEndereco());
    }

    @Test
    public void testeEmprestarLivro()
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException, DevolucaoInvalidaException {
        assertTrue("A biblioteca deve poder emprestar livros (que estejam disponíveis) para usuários que não tenham" +
                " excedido o limite de empréstimos", biblioteca.emprestarLivro(livroAbundante, usuario1));
        assertEquals("O empréstimo de um livro deve atualizar a quantidade de livros devidos por aquele usuário",
                1, biblioteca.getQuantidadeDeLivrosDevidos(usuario1));
        assertEquals("O empréstimo de um livro deve atualizar a quantidade daquele livro na estante",
                4, biblioteca.getQuantidadeDeLivrosNaEstante(livroAbundante));
    }

    @Test
    public void testeDevolverLivro()
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException, DevolucaoInvalidaException {
        biblioteca.emprestarLivro(livroAbundante, usuario1);
        biblioteca.receberDevolucaoLivro(livroAbundante, usuario1);
        assertEquals("A devolução de um livro deve atualizar a quantidade de livros devidos por aquele usuário",
                0, biblioteca.getQuantidadeDeLivrosDevidos(usuario1));
        assertEquals("A devolução de um livro deve atualizar a quantidade daquele livro na estante",
                5, biblioteca.getQuantidadeDeLivrosNaEstante(livroAbundante));
    }

    @Test
    public void testeEmprestarLivroIndisponivel()
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {
        assertFalse("A tentativa de emprestar um livro com menos de " +
                Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR +
                " cópias na estante deve retornar false, sem lançar exceções",
                biblioteca.emprestarLivro(livroRaro, usuario1));
    }

    @Test
    public void testeEmprestarLivroInexistente()
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {
        assertFalse("A tentativa de emprestar um livro não presente no acervo deve retornar false, sem lançar exceções",
                biblioteca.emprestarLivro(livroInexistente, usuario1));
    }

    @Test
    public void testeEmprestarLivroParaUsuarioNaoCadastrado()
            throws LimiteEmprestimosExcedidoException {
        try {
            biblioteca.emprestarLivro(livroAbundante, usuarioFantasma);
            fail("A tentativa de empréstimo para usuário não cadastrado deve lançar uma excecao.UsuarioNaoCadastradoException");
        } catch (UsuarioNaoCadastradoException e) {
            // ok, teste passou
        }
    }

    @Test
    public void testeEmprestarLivroParaUsuarioComMuitosLivrosDevidos()
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {

        // garante que haverá cópias do livro suficientes (para este teste) nas estantes da biblioteca
        int copiasRequeridasParaOTeste = Biblioteca.MAX_LIVROS_DEVIDOS + Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR;
        biblioteca.incluirLivroNoAcervo(livroAbundante,
                copiasRequeridasParaOTeste - biblioteca.getQuantidadeDeLivrosNaEstante(livroAbundante));

        // vamos pegar emprestado o máximo possível de livros
        for(int i = 0; i < Biblioteca.MAX_LIVROS_DEVIDOS; i++) {
            biblioteca.emprestarLivro(livroAbundante, usuario1);
        }

        // agora vamos tentar pegar emprestado ainda mais um livro --- não deve ser permitido!
        try {
            biblioteca.emprestarLivro(livroAbundante, usuario1);
            fail("Um mesmo usuario nao pode pegar emprestado mais do que " +
                    Biblioteca.MAX_LIVROS_DEVIDOS +
                    " livros --- uma excecao.LimiteEmprestimosExcedidoException deve ser lançada nesse caso");
        } catch (LimiteEmprestimosExcedidoException e) {
            // ok, teste passou
        }
    }

    @Test
    public void testeDevolucaoLivroNaoEmprestado() {
        try {
            biblioteca.receberDevolucaoLivro(livroRaro, usuario1);
            fail("A tentativa de devolver um livro que não foi emprestado (àquele usuário) deve " +
                    "resultar em uma excecao.DevolucaoInvalidaException");
        } catch (DevolucaoInvalidaException e) {
            // ok, teste passou
        }
    }

    @Test
    public void testeGetQuantidadeDeLivrosDevidosPorUsuarioNaoCadastrado() {
        assertEquals("Usuários desconhecidos nunca estão devendo livros, e o método deve simplesmente retornar zero, " +
                "sem lançar exceção",
                0, biblioteca.getQuantidadeDeLivrosDevidos(usuarioFantasma));
    }
}