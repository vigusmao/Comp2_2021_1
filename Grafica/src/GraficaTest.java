import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraficaTest {

    private static final float FLOAT_DELTA = 0.000001f;

    Grafica grafica;
    Impressora impressora1;
    Impressora impressora2;
    Impressora impressora3;
    Documento docColorido;
    Documento docPB;

    @Before
    public void setUp() {
        grafica = new Grafica();
        grafica.setPrecoPorPagina(0.10f, false);
        grafica.setPrecoPorPagina(0.30f, true);

        impressora1 = TestUtils.criarImpressora("jatodetinta");
        impressora2 = TestUtils.criarImpressora("laser");
        impressora3 = TestUtils.criarImpressora("matricial");
        grafica.adicionarImpressora(impressora1);
        grafica.adicionarImpressora(impressora2);
        grafica.adicionarImpressora(impressora3);

        docColorido = TestUtils.criarDocumento(10, "Doc Teste", true);
        docPB = TestUtils.criarDocumento(50, "Doc Teste", false);
    }

    @Test
    public void testarOrcamentoImpressao() {
        assertEquals(3, grafica.orcarImpressao(docColorido), FLOAT_DELTA);
        assertEquals(5, grafica.orcarImpressao(docPB), FLOAT_DELTA);
    }

    @Test
    public void testarRevezamentoImpressoras() {
        grafica.imprimirDocumento(docColorido);
        grafica.imprimirDocumento(docPB);
        grafica.imprimirDocumento(docColorido);

        impressora1.carregarPapel(100000);
        impressora2.carregarPapel(100000);
        impressora3.carregarPapel(100000);

        assertEquals(1, impressora1.getQuantidadeDocumentosImpressos());
        assertEquals(1, impressora2.getQuantidadeDocumentosImpressos());
        assertEquals(1, impressora3.getQuantidadeDocumentosImpressos());

        for (int i = 0; i < 3000; i++) {
            grafica.imprimirDocumento(docColorido);
        }

        String regraDeNegocio = "As impressoras precisar trabalhar em esquema de revezamento";
        assertEquals(regraDeNegocio, 1001, impressora1.getQuantidadeDocumentosImpressos());
        assertEquals(regraDeNegocio, 1001, impressora2.getQuantidadeDocumentosImpressos());
        assertEquals(regraDeNegocio, 1001, impressora3.getQuantidadeDocumentosImpressos());
    }
}