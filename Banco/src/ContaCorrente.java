import java.util.ArrayList;
import java.util.Date;


public class ContaCorrente {

    private final int numero;

    private Correntista correntista;

    private float saldoEmReais = 0;

    private ArrayList<String> transacoes;

    private Gerente gerente;

    public static final float SALDO_INICIAL_DA_CONTA = 10;  // "constante"

    private static int quantidadeDeTransacoesDeTodasAsContas = 0;


    // CONSTRUTOR: método especial que roda quando chamamos o "new" para instanciar
    public ContaCorrente(int numeroDaConta, Correntista correntista) {
        this.numero = numeroDaConta;
        this.correntista = correntista;
        this.saldoEmReais = SALDO_INICIAL_DA_CONTA;  // saldo inicial doado pelo banco
        this.transacoes = new ArrayList<>();
        this.transacoes.add(String.format(
                "Conta criada com saldo de R$%.2f", this.saldoEmReais));
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

    public String getExtrato() {
        String resultado = "";
        for (int i = 0; i < transacoes.size(); i++) {
            resultado += transacoes.get(i) + "\n";
        }
        return resultado;
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

    public void efetuarTransferecia(ContaCorrente contaDestino, float valor) {
        if (valor > this.saldoEmReais) {
            return;  // não tem fundos, então não faço nada
        }

        this.saldoEmReais -= valor;
        contaDestino.saldoEmReais += valor;

        registrarTransacao(
                String.format(
                "Transferência para a conta %d no valor de R$%.2f",
                contaDestino.numero, valor));
    }

    private void registrarTransacao(String registro) {
        String dataAtual = obterDataAtualAsString();
        this.transacoes.add(dataAtual + ": " + registro);
        quantidadeDeTransacoesDeTodasAsContas++;
    }

    String obterDataAtualAsString() {
        return String.format("%s", new Date());
    }
}
