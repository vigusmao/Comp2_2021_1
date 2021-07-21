import java.util.ArrayList;

public class Principal {


    public static void main(String[] args) {

        ContaCorrente conta1, conta2, conta3;

        conta1 = new ContaCorrente(1111, 80060049921L);
        conta2 = new ContaCorrente(2222, 30012345644L);
        conta3 = new ContaCorrente(3333, 32765236326L);

        System.out.println(conta1.getSaldoEmReais());

        conta1.receberDepositoEmDinheiro(500);
        System.out.println(conta1.getSaldoEmReais());

        conta1.receberDepositoEmDinheiro(200);
        System.out.println(conta1.getSaldoEmReais());

        conta1.receberDepositoEmDinheiro(-500);
        System.out.println(conta1.getSaldoEmReais());

        float saldo;
        //  |||  0  |||      o valor default

        saldo = conta1.getSaldoEmReais();
//  |||  710  |||

        saldo = 1000000;
//  |||  1000000  |||



//        ArrayList<String> transacoesDaConta;
//        //               |||      null     |||
//
//        transacoesDaConta = conta1.getTransacoes();
////          |||    $3265326     |||
////             {"Recibo depósito..."}    {"Saque de ..."}    {R$1.000.000}
//             transacoesDaConta.add("Depótideo de R$1.000.000,00");

        String extrato = conta1.getExtrato();

        System.out.println(extrato);

    }
}
