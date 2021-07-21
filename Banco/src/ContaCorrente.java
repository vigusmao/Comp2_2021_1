import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ContaCorrente {

    private int numero;

    private long cpfDoCorrentista;

    private float saldoEmReais;
    //          |||   710     |||

    private ArrayList<String> transacoes;
            //               |||    $3265326     |||
            //                    {"Recibo depósito..."}    {"Saque de ..."}


    // CONSTRUTOR: método especial que roda quando chamamos o "new" para instanciar
    public ContaCorrente(int numeroDaConta, long cpfDoCorrentista) {
        this.numero = numeroDaConta;
        this.cpfDoCorrentista = cpfDoCorrentista;
        this.saldoEmReais = 10;  // saldo inicial doado pelo banco
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

////        Date agora = new Date();  // now
//        long agora = System.currentTimeMillis();

        String registroTransacao = "Recebido depósito em dinheiro: " +
                valor;

        this.transacoes.add(registroTransacao);
    }

    public String getExtrato() {
        String resultado = "";
        for (int i = 0; i < this.transacoes.size(); i++) {
            resultado += this.transacoes.get(i) + "\n";
        }
        return resultado;
    }
}
