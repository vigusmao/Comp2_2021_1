package modelo;

public enum TipoUsuario {

    INICIANTE(0, 3),
    EXPERIENTE(10, 5),
    ILIMITADO(100, Integer.MAX_VALUE);

    private int minDevolucoesParaIngressarNoNivel;
    private int maxCopiasDevidas;

    TipoUsuario(int minDevolucoesParaIngressarNoNivel,
                        int maxCopiasDevidas) {
        this.minDevolucoesParaIngressarNoNivel = minDevolucoesParaIngressarNoNivel;
        this.maxCopiasDevidas = maxCopiasDevidas;
    }

    public int getMinDevolucoesParaIngressarNoNivel() {
        return minDevolucoesParaIngressarNoNivel;
    }

    public int getMaxCopiasDevidas() {
        return maxCopiasDevidas;
    }
}
