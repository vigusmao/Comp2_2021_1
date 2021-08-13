import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ImpressoraTest {

    private ImpressoraJatoDeTinta impressoraJatoDeTinta;
    private ImpressoraLaser impressoraLaser;
    private ImpressoraMatricial impressoraMatricial;

    private Documento docPequenoPB;
    private Documento docGrandeColorido;

    @Before
    public void setUp() {
        impressoraJatoDeTinta = new ImpressoraJatoDeTinta();
        impressoraLaser = new ImpressoraLaser();
        impressoraMatricial = new ImpressoraMatricial();

        docPequenoPB = TestUtils.criarDocumento(5, "Doc Pequeno", false);
        docGrandeColorido = TestUtils.criarDocumento(200, "Doc Grande", true);
    }

    @Test
    public void testarImpressaoComPapelSuficiente() {
        executarTesteImpressaoComPapelSuficiente(impressoraLaser);
        executarTesteImpressaoComPapelSuficiente(impressoraJatoDeTinta);
        executarTesteImpressaoComPapelSuficiente(impressoraMatricial);
    }

    private void executarTesteImpressaoComPapelSuficiente(Impressora impressora) {
        impressora.carregarPapel(100);
        assertTrue(impressora.imprimirDocumento(docPequenoPB));
        assertTrue(impressora.imprimirDocumento(docPequenoPB));
        assertEquals(2, impressora.getQuantidadeDocumentosImpressos());
        assertEquals(90, impressora.getQuantidadeFolhasRestantes());
    }

    @Test
    public void testarImpressaoComPapelInsuficiente() {
        executarTesteImpressaoComPapelInsuficiente(impressoraLaser);
        executarTesteImpressaoComPapelInsuficiente(impressoraJatoDeTinta);
        executarTesteImpressaoComPapelInsuficiente(impressoraMatricial);
    }

    private void executarTesteImpressaoComPapelInsuficiente(Impressora impressora) {
        impressora.carregarPapel(202);
        assertTrue(impressora.imprimirDocumento(docGrandeColorido));

        assertFalse("Não devemos sequer iniciar a impressão se " +
                        "não houver papel suficiente para o documento inteiro",
                impressora.imprimirDocumento(docPequenoPB));

        assertEquals(1, impressora.getQuantidadeDocumentosImpressos());
        assertEquals(2, impressora.getQuantidadeFolhasRestantes());

        impressora.carregarPapel(60);
        assertEquals(62, impressora.getQuantidadeFolhasRestantes());

        assertTrue(impressora.imprimirDocumento(docPequenoPB));

        assertEquals(2, impressora.getQuantidadeDocumentosImpressos());
        assertEquals(57, impressora.getQuantidadeFolhasRestantes());
    }
}