import java.util.ArrayList;

public class TestUtils {

    static Documento criarDocumento(int numeroDePaginas, String nome, boolean emCores) {
        ArrayList<String> paginas = new ArrayList<>();
        for (int i = 1; i <= numeroDePaginas; i++) {
            String pagina = String.format("Página %d do documento %s", i, nome);
            paginas.add(pagina);
        }
        Documento doc = new Documento(paginas, emCores);
        return doc;
    }

    static Impressora criarImpressora(String tipo) {
        switch (tipo.toLowerCase()) {
            case "jato":
            case "jato de tinta":
            case "jatodetinta":
                return new ImpressoraJatoDeTinta();
            case "matricial":
                return new ImpressoraMatricial();
            case "laser":
            default:
                return new ImpressoraLaser();
        }
    }
}
