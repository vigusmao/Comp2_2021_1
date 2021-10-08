public class ContaInvestimento extends Conta {

    private TipoInvestimento tipo;

    private float taxaJuros;

    public ContaInvestimento(int numeroDaConta, Correntista correntista,
                             TipoInvestimento tipoInvestimento, float taxaJuros,
                             float aporteInicial) {

        super(numeroDaConta, correntista, 0);

        Conta contaCorrente = correntista.getContaCorrente();
        if (contaCorrente == null) {
            throw new RuntimeException("Correntista sem conta corrente!");
        }

        if (aporteInicial < tipoInvestimento.getSaldoMinimoParaAbertura()) {
            throw new RuntimeException("Aporte inicial insuficiente!");
        }

        setTipoInvestimento(tipoInvestimento);
        this.taxaJuros = taxaJuros;
    }

    public void setTipoInvestimento(TipoInvestimento tipo) {
        this.tipo = tipo;
    }

    void aplicarJuros() {
        this.saldoEmReais *= (1 + taxaJuros);

        switch (this.tipo) {
            case TESOURO_DIRETO:
                // dsfksdfksdfkl
                break;
            case CDB:
                //dsflksdflksdjf
                break;
            // etc. etc.
            default:
                //dklsjdkflslkds
        }
    }

    public void resgatarTotal() {
        Conta contaCorrente = getCorrentista().getContaCorrente();


//        efetuarTransferecia(contaCorrente, this.saldoEmReais);

        contaCorrente.saldoEmReais += this.saldoEmReais;
        this.saldoEmReais = 0;

        registrarTransacao(
                String.format("Resgate total efetuado no valor de R$.2f",
                        this.saldoEmReais));

        contaCorrente.registrarTransacao(
                String.format("Recebido resgate total da conta %d no valor de R$.2f",
                        getNumero(), this.saldoEmReais));

    }

    public TipoInvestimento getTipo() {
        return tipo;
    }

    @Override
    protected boolean isContaCorrente() {
        return false;
    }
}
