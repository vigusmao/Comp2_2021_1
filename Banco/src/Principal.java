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

        conta2.receberDepositoEmDinheiro(35.80f);

        System.out.println(conta1.getExtrato());
        System.out.println("\n\n");
        System.out.println(conta2.getExtrato());


        System.out.println(ContaCorrente.SALDO_INICIAL_DA_CONTA);
        System.out.println(ContaCorrente.getQuantidadeDeTransacoesDeTodasAsContas());
    }
}
