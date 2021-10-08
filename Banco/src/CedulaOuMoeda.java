public enum CedulaOuMoeda {

    MOEDA_DE_CINCO_CENTAVOS(0.05f),
    MOEDA_DE_DEZ_CENTAVOS(0.1f),
    MOEDA_DE_VINTE_E_CINCO_CENTAVOS(0.25f),
    MOEDA_DE_CINQUENTA_CENTAVOS(0.5f),
    MOEDA_DE_UM_REAL(1),
    CEDULA_DE_DOIS_REAIS(2),
    CEDULA_DE_CINCO_REAIS(5),
    CEDULA_DE_DEZ_REAIS(10),
    CEDULA_DE_VINTE_REAIS(20),
    CEDULA_DE_CINQUENTA_REAIS(50),
    CEDULA_DE_CEM_REAIS(100),
    CEDULA_DE_DUZENTOS_REAIS(200);

    private float valorMonetario;

    CedulaOuMoeda(float valorMonetario) {
        this.valorMonetario = valorMonetario;
    }

    public float getValorMonetario() {
        return this.valorMonetario;
    }
}
