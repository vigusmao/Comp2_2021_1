import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ContaTest {

    // para cobrir pequenos erros de precisão do tipo float
    private float FLOAT_DELTA = 0.00001f;

    private long cpfDoJoao = 12345;
    private Correntista joao;
    private Conta contaDoJoao;

    private long cpfDaMaria = 22222;
    private Correntista maria;
    private Conta contaDaMaria;

    private float saldoInicial;

    @Before
    public void setUp() {
        joao = new Correntista("João", cpfDoJoao);
        contaDoJoao = new Conta(1, joao);

        maria = new Correntista("Maria", cpfDaMaria);
        contaDaMaria = new Conta(2, maria);

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
        int transacoesAnteriores = Conta.getQuantidadeDeTransacoesDeTodasAsContas();

        contaDoJoao.receberDepositoEmDinheiro(1000);
        assertEquals(transacoesAnteriores + 1, Conta.getQuantidadeDeTransacoesDeTodasAsContas());

        Conta outraConta = new Conta(22222, joao);
        outraConta.sacar(5);
        assertEquals(transacoesAnteriores + 2, Conta.getQuantidadeDeTransacoesDeTodasAsContas());
    }

    @Test
    public void testarGetCpfDoCorrentista() {
        assertEquals("A conta corrente deve informar corretamente o CPF de seu correntista",
                cpfDoJoao, contaDoJoao.getCpfDoCorrentista());
    }

    @Test
    public void testarContaEncerradaPeloGerenteDeContaErrado() {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos Roberto", 999, 20);

        gerenteDeContaCarlos.encerrarConta(contaDoJoao);

        assertTrue("Uma conta não pode ser encerrada por gerentes de conta " +
                "que não gerenciem aquela conta", contaDoJoao.isAtiva());
    }

    @Test
    public void testarContaEncerradaPeloGerenteDeContaCorreto() {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos Roberto", 999, 20);

        gerenteDeContaCarlos.gerenciarConta(contaDoJoao);

        // sanity check
        assertTrue(gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));

        gerenteDeContaCarlos.encerrarConta(contaDoJoao);

        assertFalse("Uma conta deve ficar com status de inativa após ser encerrada " +
                "pelo seu gerente", contaDoJoao.isAtiva());

        assertFalse("Contas encerradas não devem constar da lista de contas " +
                "gerenciadas por gerente algum", gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));
    }

    @Test
    public void testarContaEncerradaPorUmGerenteGeral() {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos Roberto", 999, 20);

        gerenteDeContaCarlos.gerenciarConta(contaDoJoao);

        GerenteGeral gerenteGeralMariza = new GerenteGeral("Mariza Silva", 888, 2);

        // sanity check
        assertTrue(gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));

        gerenteGeralMariza.encerrarConta(contaDoJoao);

        assertFalse("Uma conta deve ficar com status de inativa após ser encerrada " +
                "por um gerente geral", contaDoJoao.isAtiva());

        assertFalse("Uma conta encerrada não deve constar da lista de contas " +
                "gerenciadas pelo gerente geral que a encerrou", gerenteGeralMariza.ehGerenteDaConta(contaDoJoao));

        assertFalse("Contas encerradas não devem constar da lista de contas " +
                "gerenciadas por gerente algum", gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));
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