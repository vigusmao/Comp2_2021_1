import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContaCorrenteTest {

    // para cobrir pequenos erros de precisão do tipo float
    private float FLOAT_DELTA = 0.00001f;

    private long cpfDoJoao = 12345;
    private Correntista joao;
    private ContaCorrente contaDoJoao;

    private long cpfDaMaria = 22222;
    private Correntista maria = new Correntista(cpfDaMaria, "Maria");
    private ContaCorrente contaDaMaria = new ContaCorrente(2, maria);

    private float saldoInicial;

    @Before
    public void setUp() {
        joao = new Correntista(cpfDoJoao, "João");
        contaDoJoao = new ContaCorrente(1, joao);

        maria = new Correntista(cpfDaMaria, "Maria");
        contaDaMaria = new ContaCorrente(2, maria);

        saldoInicial = contaDoJoao.getSaldoEmReais();
    }

    @Test
    public void testarSaldoInicialDaConta() {
        assertEquals("Toda conta, ao ser criada, deve começar com saldo de R$10,00.",
                10.0,
                saldoInicial,
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresValidos() {
        contaDoJoao.receberDepositoEmDinheiro(50);
        assertEquals("O saldo da conta deve ser atualizado após receber um depósito",
                saldoInicial + 50,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresNegativos() {
        contaDoJoao.receberDepositoEmDinheiro(-200);
        assertEquals("Depósitos de valores negativos devem ser solenemente ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValorZero() {
        String extratoAntes = contaDoJoao.getExtrato();

        contaDoJoao.receberDepositoEmDinheiro(0);
        assertEquals("Depósitos de valor zero devem ser ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);

        String extratoDepois = contaDoJoao.getExtrato();

        assertEquals("Depósitos ignorados não devem sequer constar do extrato",
                extratoAntes, extratoDepois);

    }

    @Test
    public void testarSaqueComFundos() {
        contaDoJoao.sacar(2);
        assertEquals("O valor sacado deve ser descontado do saldo da conta",
                saldoInicial - 2,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarTransferencia() {
        contaDoJoao.efetuarTransferecia(contaDaMaria, 3);

        assertEquals("Transferências devem diminuir o saldo da conta de origem",
                saldoInicial - 3,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );

        assertEquals("Transferências devem aumentar o saldo da conta de destino",
                saldoInicial + 3,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarSaqueSemFundos() {
        contaDoJoao.sacar(100000);
        assertEquals("Saques de valores maiores que o saldo não devem ser permitidos",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarTransferenciaSemFundos() {
        contaDoJoao.efetuarTransferecia(contaDaMaria, 100000);

        assertEquals("Transferências sem fundos não devem alterar o saldo da conta de origem",
                saldoInicial,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA
        );

        assertEquals("Transferências sem fundos não devem alterar o saldo da conta de destino",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarTotalDeTransacoesEmTodasAsContas() {
        // testes anteriores podem ter incrementado esse atributo static
        int transacoesAnteriores = ContaCorrente.getQuantidadeDeTransacoesDeTodasAsContas();

        contaDoJoao.receberDepositoEmDinheiro(1000);
        assertEquals(transacoesAnteriores + 1, ContaCorrente.getQuantidadeDeTransacoesDeTodasAsContas());

        ContaCorrente outraConta = new ContaCorrente(22222, joao);
        outraConta.sacar(5);
        assertEquals(transacoesAnteriores + 2, ContaCorrente.getQuantidadeDeTransacoesDeTodasAsContas());
    }

    @Test
    public void testarGetCpfDoCorrentista() {
        assertEquals("A conta corrente deve informar corretamente o CPF de seu correntista",
                cpfDoJoao, contaDoJoao.getCpfDoCorrentista());
    }

//    @Test
//    public void testarExtrato() {
//        String extratoEsperado = "Conta criada com saldo de R$10,00\n";
//        assertEquals(extratoEsperado, contaDoJoao.getExtrato());
//
//        contaDoJoao.sacar(3);
//
//        extratoEsperado = "Conta criada com saldo de R$10,00\n" +
//                "xxxxxxxx: Saque no valor de R$3,00\n";
//        assertEquals(extratoEsperado, contaDoJoao.getExtrato());
//    }
}