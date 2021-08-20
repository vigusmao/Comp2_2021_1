import java.util.ArrayList;

public class Documento {

    private ArrayList<String> paginas;
    private boolean emCores;

    public Documento(ArrayList<String> paginas, boolean emCores) {
//        this.paginas = paginas;  // apontará para o mesmo objeto
        this.paginas = new ArrayList<>(paginas);  // cria outro objeto, copiando o conteúdo
        this.emCores = emCores;
    }

    public boolean isEmCores() {
        return this.emCores;
    }

    public int getQuantPaginas() {
        return this.paginas.size();
    }

    public String getPagina(int numeroDaPagina) {
        return this.paginas.get(numeroDaPagina - 1);
    }
}
