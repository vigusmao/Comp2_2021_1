import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImpressoraTest {

    private ImpressoraJatoDeTinta impressoraJatoDeTinta;
    private ImpressoraLaser impressoraLaser;
    private ImpressoraMatricial impressoraMatricial;

    private Documento docPequenoPB;
    private Documento docGrandeColorido;

    @Before
    public void setUp() {
        impressoraJatoDeTinta = new ImpressoraJatoDeTinta("blah", 1111);
        impressoraLaser = new ImpressoraLaser("laser", 2222, 200);
        impressoraMatricial = new ImpressoraMatricial(1234);

        docPequenoPB = TestUtils.criarDocumento(5, "Doc Pequeno", false);
        docGrandeColorido = TestUtils.criarDocumento(200, "Doc Grande", true);
    }

    @Test
    public void testarImpressaoComPapelSuficiente() throws PapelInsuficienteException, TintaEsgotadaException {
        executarTesteImpressaoComPapelSuficiente(impressoraLaser);
        executarTesteImpressaoComPapelSuficiente(impressoraJatoDeTinta);
        executarTesteImpressaoComPapelSuficiente(impressoraMatricial);
    }

    private void executarTesteImpressaoComPapelSuficiente(Impressora impressora)
            throws PapelInsuficienteException, TintaEsgotadaException {

        impressora.carregarPapel(100);
        impressora.imprimirDocumento(docPequenoPB);
        impressora.imprimirDocumento(docPequenoPB);
        assertEquals(2, impressora.getQuantidadeDocumentosImpressos());
        assertEquals(90, impressora.getQuantidadeFolhasRestantes());
    }

    @Test
    public void testarImpressaoComPapelInsuficiente() throws TintaEsgotadaException {
        executarTesteImpressaoComPapelInsuficiente(impressoraLaser);
        executarTesteImpressaoComPapelInsuficiente(impressoraJatoDeTinta);
        executarTesteImpressaoComPapelInsuficiente(impressoraMatricial);
    }

    private void executarTesteImpressaoComPapelInsuficiente(Impressora impressora) throws TintaEsgotadaException {
        impressora.carregarPapel(202);

        try {
            impressora.imprimirDocumento(docGrandeColorido);
        } catch (PapelInsuficienteException e) {
            fail("A impress??o deveria ter acontecido sem problemas, pois n??o falta papel!!!");
        } catch (TintaEsgotadaException e) {
            // ok!! sem problemas... apenas n??o estamos carregando as tintas na impressora jato de tinta
        }

        try {
            impressora.imprimirDocumento(docPequenoPB);

            fail("N??o devemos sequer iniciar a impress??o se " +
                    "n??o houver papel suficiente para o documento inteiro");

        } catch (PapelInsuficienteException e) {
            // ok!!!! era esperado!
        } catch (TintaEsgotadaException e) {
            // ok!! sem problemas... apenas n??o estamos carregando as tintas na impressora jato de tinta
        }

        assertEquals(1, impressora.getQuantidadeDocumentosImpressos());
        assertEquals(2, impressora.getQuantidadeFolhasRestantes());

        impressora.carregarPapel(60);
        assertEquals(62, impressora.getQuantidadeFolhasRestantes());

        try {
            impressora.imprimirDocumento(docPequenoPB);
        } catch (PapelInsuficienteException e) {
            fail("A impress??o deveria ter acontecido sem problemas, pois n??o falta papel!!!");
        } catch (TintaEsgotadaException e) {
            // ok!! sem problemas... apenas n??o estamos carregando as tintas na impressora jato de tinta
        }

        assertEquals(2, impressora.getQuantidadeDocumentosImpressos());
        assertEquals(57, impressora.getQuantidadeFolhasRestantes());
    }
}