import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlbumTest {

    private Album<Figurinha> albumFigurinhas;

    private static final int TAMANHO_DO_ALBUM = 300;
    private static final int ITENS_POR_PACOTE = 3;

    @Before  // roda antes de cada teste
    public void setUp() {
        this.albumFigurinhas = new Album<>(TAMANHO_DO_ALBUM, ITENS_POR_PACOTE);
    }

    private void popularAlbum(int[] posicoesDesejadas) throws PacotinhoInvalidoException {
        Pacotinho<Figurinha> pacoteFigurinhas = new Pacotinho<>(ITENS_POR_PACOTE);
        adicionarFigurinhasAoPacotinho(pacoteFigurinhas, posicoesDesejadas);
        this.albumFigurinhas.receberNovoPacotinho(pacoteFigurinhas);
    }

    private void adicionarFigurinhasAoPacotinho(Pacotinho<Figurinha> pacote, int[] posicoesDesejadas) {
        for (int posicao : posicoesDesejadas) {
            Figurinha fig = new Figurinha(posicao, "http://meuAlbum.com.br/fig" + posicao + ".png");
            pacote.add(fig);
        }
    }

    @Test
    public void testarPossuiItemColadoParaItensExistentes() throws PacotinhoInvalidoException {
        popularAlbum(new int[] {1, 2, 3});

        for (int i = 1; i <= ITENS_POR_PACOTE; i++) {
            assertTrue("Figurinhas já inseridas devem ser encontradas",
                    this.albumFigurinhas.possuiItemColado(i));
        }
    }

    @Test
    public void testarPossuiItemColadoParaItensAusentes() throws PacotinhoInvalidoException {
        popularAlbum(new int[] {1, 2, 3});

        assertFalse("Não devemos encontrar no álbum figurinhas nunca inseridas",
                this.albumFigurinhas.possuiItemColado(4));
        assertFalse("Não devemos encontrar figurinhas de posições não-positivas",
                this.albumFigurinhas.possuiItemColado(-390));
        assertFalse("Não devemos encontrar figurinhas maiores do que o tamanho",
                this.albumFigurinhas.possuiItemColado(TAMANHO_DO_ALBUM + 1));
    }

    @Test
    public void testarItemRepetido() throws PacotinhoInvalidoException {
        popularAlbum(new int[] {1, 2, 3});

        // sanity check
        assertEquals(0, albumFigurinhas.getQuantItensRepetidos());
        assertFalse(this.albumFigurinhas.possuiItemRepetido(2));

        popularAlbum(new int[] {2, 8, 9});

        assertEquals(1, albumFigurinhas.getQuantItensRepetidos());
        assertTrue(this.albumFigurinhas.possuiItemRepetido(2));
    }

    @Test
    public void testarGetTamanhoAlbum() {
        assertEquals(TAMANHO_DO_ALBUM, this.albumFigurinhas.getTamanho());
    }

    @Test
    public void testarGetQuantItensColados() throws PacotinhoInvalidoException {
        popularAlbum(new int[] {1, 2, 3});
        assertEquals(ITENS_POR_PACOTE, this.albumFigurinhas.getQuantItensColados());

        // vou agora abrir outro pacotinho com as mesmas figurinhas
        // e verificar que o álbum terá ainda 3 figurinhas

        popularAlbum(new int[] {1, 2, 3});
        assertEquals(ITENS_POR_PACOTE, this.albumFigurinhas.getQuantItensColados());
    }

    @Test
    public void testarGetQuantItensFaltantes() throws PacotinhoInvalidoException {
        popularAlbum(new int[] {1, 2, 3});
        assertEquals(TAMANHO_DO_ALBUM - ITENS_POR_PACOTE,
                this.albumFigurinhas.getQuantItensFaltantes());
    }

    @Test
    public void testarGetItemColado() throws PacotinhoInvalidoException {
        popularAlbum(new int[] {1, 2, 3});
        Figurinha figurinha = albumFigurinhas.getItemColado(2);
        assertNotNull(figurinha);
        assertEquals(2, figurinha.getPosicao());
        assertNull(albumFigurinhas.getItemColado(4));
    }

    @Test
    public void testarCriacaoDePacotinhoGrandeDemais() {
        Pacotinho<Figurinha> pacote = new Pacotinho<>(ITENS_POR_PACOTE);
        try {
            adicionarFigurinhasAoPacotinho(pacote, new int[]{10, 20, 30, 40});
            fail("Devemos tomar uma IllegalStateException se tentarmos colocar figurinhas " +
                    "demais no mesmo pacotinho");
        } catch (IllegalStateException e) {
            // ok!
        }
    }

    @Test
    public void testarRecebimentoDePacotinhoPequenoDemais() {
        Pacotinho<Figurinha> pacote = new Pacotinho<>(ITENS_POR_PACOTE);
        try {
            adicionarFigurinhasAoPacotinho(pacote, new int[]{10, 20});
            albumFigurinhas.receberNovoPacotinho(pacote);
            fail("Devemos tomar uma PacotinhoInvalidoException se tentarmos receber um pacotinho " +
                    "com o tamanho errado");
        } catch (PacotinhoInvalidoException e) {
            // ok!
        }

        assertFalse("O pacotinho de tamanho errado deve ser descartado, e nenhuma figurinha " +
                "dele deve ser considerada", albumFigurinhas.possuiItemColado(10));
    }

    @Test
    public void testarRecebimentoDePacotinhoComFigurinhaInvalida() {
        Pacotinho<Figurinha> pacote = new Pacotinho<>(ITENS_POR_PACOTE);
        try {
            adicionarFigurinhasAoPacotinho(pacote, new int[]{10, 20, TAMANHO_DO_ALBUM + 1});
            albumFigurinhas.receberNovoPacotinho(pacote);
            fail("Devemos tomar uma PacotinhoInvalidoException se tentarmos receber um pacotinho " +
                    "com figurinha de posição inválida");
        } catch (PacotinhoInvalidoException e) {
            // ok!
        }

        assertFalse("O pacotinho com figurinha inválida deve ser descartado, e nenhuma figurinha " +
                "dele deve ser considerada", albumFigurinhas.possuiItemColado(10));
    }
}
