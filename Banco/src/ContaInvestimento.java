public class ContaInvestimento extends Conta {

    private String tipoInvestimento;

    private float taxaJuros;

    public ContaInvestimento(int numeroDaConta, Correntista correntista,
                             String tipoInvestimento, float taxaJuros) {

        super(numeroDaConta, correntista, 0);

        Conta contaCorrente = correntista.getContaCorrente();
        if (contaCorrente == null) {
            throw new RuntimeException("Correntista sem conta corrente!");
        }

        this.tipoInvestimento = tipoInvestimento;
        this.taxaJuros = taxaJuros;
    }

    void aplicarJuros() {
        this.saldoEmReais *= (1 + taxaJuros);
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

    @Override
    protected boolean isContaCorrente() {
        return false;
    }
}
