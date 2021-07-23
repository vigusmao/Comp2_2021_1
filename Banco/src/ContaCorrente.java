import java.util.ArrayList;
import java.util.Date;


public class ContaCorrente {

    private final int numero;

    private Correntista correntista;

    private float saldoEmReais = 0;

    private ArrayList<String> transacoes;

    public static final float SALDO_INICIAL_DA_CONTA = 10;  // "constante"

    private static int quantidadeDeTransacoesDeTodasAsContas = 0;


    // CONSTRUTOR: método especial que roda quando chamamos o "new" para instanciar
    public ContaCorrente(int numeroDaConta, Correntista correntista) {
        this.numero = numeroDaConta;
        this.saldoEmReais = SALDO_INICIAL_DA_CONTA;  // saldo inicial doado pelo banco
        this.transacoes = new ArrayList<>();
        this.transacoes.add("Conta criada com saldo de " + this.saldoEmReais);
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

        String registroTransacao = "recebido depósito em dinheiro: " + valor;

        this.transacoes.add(registroTransacao);
        quantidadeDeTransacoesDeTodasAsContas++;
    }

    public long getCpfDoCorrentista() {
        return 0;
        // ToDo IMPLEMENT ME!!!!!!
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
        // ToDo IMPLEMENT ME!!!
    }

    public void efetuarTransferecia(ContaCorrente contaDestino, float valor) {
        // ToDo IMPLEMENT ME!!!
    }
}
