public enum TipoInvestimento {

    FUNDOS_IMOBILIARIOS("Fundos imobiliários", 1000),
    RENDA_FIXA("RF", 500),
    CDB("CDB", 100),
    TESOURO_DIRETO("Tesouro Direto", 0);

    private String nome;

    private float saldoMinimoParaAbertura;

    TipoInvestimento(String nome, float saldoMinimoParaAbertura) {
        this.nome = nome;
        this.saldoMinimoParaAbertura = saldoMinimoParaAbertura;
    }

    public float getSaldoMinimoParaAbertura() {
        return saldoMinimoParaAbertura;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
