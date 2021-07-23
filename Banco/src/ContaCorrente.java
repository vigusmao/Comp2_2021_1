import java.util.ArrayList;
import java.util.Date;


public class ContaCorrente {

    private final int numero;

    private long cpfDoCorrentista;

    private float saldoEmReais = 0;

    private ArrayList<String> transacoes;

    public static final float SALDO_INICIAL_DA_CONTA = 10;  // "constante"

    private static int quantidadeDeTransacoesDeTodasAsContas = 0;


    // CONSTRUTOR: método especial que roda quando chamamos o "new" para instanciar
    public ContaCorrente(int numeroDaConta, long cpfDoCorrentista) {
        this.numero = numeroDaConta;
        this.cpfDoCorrentista = cpfDoCorrentista;
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

        Date agora = new Date();  // now

        String registroTransacao = agora + ": recebido depósito em dinheiro: " + valor;

        this.transacoes.add(registroTransacao);
        quantidadeDeTransacoesDeTodasAsContas++;
    }

    public String getExtrato() {
        String resultado = "";
        for (int i = 0; i < transacoes.size(); i++) {
            resultado += transacoes.get(i) + "\n";
        }
        return resultado;
    }

    public static int getQuantidadeDeTransacoesDeTodasAsContas() {
        return quantidadeDeTransacoesDeTodasAsContas;
    }
}
