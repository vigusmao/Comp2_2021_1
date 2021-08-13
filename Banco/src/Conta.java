import java.util.ArrayList;
import java.util.Date;


public class Conta {

    private final int numero;

    private Correntista correntista;

    protected float saldoEmReais = 0;

    private ArrayList<String> transacoes;

    private Gerente gerente;

    private boolean ativa;

    public static final float SALDO_INICIAL_DA_CONTA = 10;  // "constante"

    private static int quantidadeDeTransacoesDeTodasAsContas = 0;

    public Conta(int numeroDaConta, Correntista correntista) {
        this(numeroDaConta, correntista, SALDO_INICIAL_DA_CONTA);
    }

    // CONSTRUTOR: método especial que roda quando chamamos o "new" para instanciar
    public Conta(int numeroDaConta, Correntista correntista,
                 float saldoInicial) {
        this.numero = numeroDaConta;
        this.correntista = correntista;
        this.saldoEmReais = saldoInicial;  // saldo inicial doado pelo banco
        this.transacoes = new ArrayList<>();
        this.transacoes.add(String.format(
                "Conta criada com saldo de R$%.2f", this.saldoEmReais));
        this.ativa = true;

        if (isContaCorrente()) {
            this.correntista.setContaCorrente(this);
        }
    }

    protected boolean isContaCorrente() {
        return true;
    }

    public int getNumero() {
        return numero;
    }

    public float getSaldoEmReais() {
        return this.saldoEmReais;
    }

    public void receberDepositoEmDinheiro(float valor) {
        if (valor <= 0) {
            return;  // valor inválido; não faz nada!
            // ToDo lançar uma exceção
        }

        this.saldoEmReais += valor;

//        Date agora = new Date();  // now

        registrarTransacao(
                String.format("Recebido depósito em dinheiro: R$%.2f",
                        valor));
    }

    public long getCpfDoCorrentista() {
        return this.correntista.getCpf();
    }

    public Correntista getCorrentista() {
        return this.correntista;
    }

    public String getExtrato() {
        String resultado = "";
        for (int i = 0; i < transacoes.size(); i++) {
            resultado += transacoes.get(i) + "\n";
        }
        return resultado;
    }

    public boolean isAtiva() {
        return this.ativa;
    }

    /**
     * Retorna a quantidade total de transações do banco, ou seja,
     * de todas as contas correntes que já foram criadas.
     *
     * @return o total de transações
     */
    public static int getQuantidadeDeTransacoesDeTodasAsContas() {
        return quantidadeDeTransacoesDeTodasAsContas;
    }

    public void sacar(float valor) {
        if (valor > this.saldoEmReais) {
            return;  // não tem fundos, então não faço nada
        }

        this.saldoEmReais -= valor;

        registrarTransacao(
                String.format(
                "Saque no valor de R$%.2f",
                valor));
    }

    public void efetuarTransferecia(Conta contaDestino, float valor) {
        if (valor > this.saldoEmReais) {
            return;  // não tem fundos, então não faço nada
        }

        this.saldoEmReais -= valor;
        contaDestino.saldoEmReais += valor;

        registrarTransacao(
                String.format(
                        "Transferência para a conta %d no valor de R$%.2f",
                        contaDestino.numero, valor));

        contaDestino.registrarTransacao(
                String.format(
                        "Transferência recebida da conta %d no valor de R$%.2f",
                        this.numero, valor));
    }

    protected void registrarTransacao(String registro) {
        String dataAtual = obterDataAtualAsString();
        this.transacoes.add(dataAtual + ": " + registro);
        quantidadeDeTransacoesDeTodasAsContas++;
    }

    String obterDataAtualAsString() {
        return String.format("%s", new Date());
    }

    public void encerrar() {
        if (this.saldoEmReais < 0) {
            // ToDo lançar exceção
            // não deixa encerrar conta com saldo negativo
            return;
        }
        this.ativa = false;  // desativou a conta

        System.out.printf("\nConta %d encerrada", this.numero);
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente novaGerente) {
        if (this.gerente != null && !this.gerente.equals(novaGerente)) {
            // avisa ao gerente antigo que ele não é mais gerente
            this.gerente.deixarDeGerenciarConta(this);
        }
        this.gerente = novaGerente;
    }
}
