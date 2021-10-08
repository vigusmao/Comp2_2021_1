import com.oracle.tools.packager.mac.MacAppBundler;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class ContaTest {

    // para cobrir pequenos erros de precisão do tipo float
    private float FLOAT_DELTA = 0.00001f;

    private long cpfDoJoao = 12345;
    private Correntista joao;
    private Conta contaDoJoao;
    private int senhaDoJoao = 1234;

    private long cpfDaMaria = 22222;
    private Correntista maria;
    private Conta contaDaMaria;

    private float saldoInicial;

    @Before
    public void setUp() {
        joao = new Correntista("João", cpfDoJoao);
        contaDoJoao = new Conta(1, joao);
        contaDoJoao.setSenha(senhaDoJoao);

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
    public void testarDepositoEmCashComCedulasEMoedasInformadas() {
        Map<CedulaOuMoeda, Integer> quantidadeByCedulaOuMoeda = new HashMap<>();

        quantidadeByCedulaOuMoeda.put(CedulaOuMoeda.MOEDA_DE_DEZ_CENTAVOS, 3);
        quantidadeByCedulaOuMoeda.put(CedulaOuMoeda.CEDULA_DE_CINQUENTA_REAIS, 2);
        quantidadeByCedulaOuMoeda.put(CedulaOuMoeda.CEDULA_DE_DEZ_REAIS, 1);
//        quantidadeByCedulaOuMoeda.put(CedulaOuMoeda.CEDULA_DE_TRES_REAIS, 1);  // precisaríamos ignorar

        contaDoJoao.receberDepositoEmDinheiro(quantidadeByCedulaOuMoeda);
        assertEquals("O saldo da conta deve ser atualizado após receber um depósito",
                saldoInicial + 110.30f,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarSaqueComFundos() throws ContaInativaException, SenhaInvalidaException, SaldoInsuficienteException {
        contaDoJoao.sacar(2, senhaDoJoao);
        assertEquals("O valor sacado deve ser descontado do saldo da conta",
                saldoInicial - 2,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test(expected = SenhaInvalidaException.class)
    public void testarSaqueComSenhaIncorreta()
            throws ContaInativaException, SaldoInsuficienteException, SenhaInvalidaException {

            contaDoJoao.sacar(2, 897987);
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
    public void testarSaqueSemFundos() throws ContaInativaException, SenhaInvalidaException, SaldoInsuficienteException {
        contaDoJoao.sacar(100000, senhaDoJoao);
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
    public void testarTotalDeTransacoesEmTodasAsContas()
            throws ContaInativaException, SenhaInvalidaException, SaldoInsuficienteException {

        // testes anteriores podem ter incrementado esse atributo static
        int transacoesAnteriores = Conta.getQuantidadeDeTransacoesDeTodasAsContas();

        contaDoJoao.receberDepositoEmDinheiro(1000);
        assertEquals(transacoesAnteriores + 1, Conta.getQuantidadeDeTransacoesDeTodasAsContas());

        Conta outraConta = new Conta(22222, joao);
        outraConta.sacar(5, senhaDoJoao);
        assertEquals(transacoesAnteriores + 2, Conta.getQuantidadeDeTransacoesDeTodasAsContas());
    }

    @Test
    public void testarGetCpfDoCorrentista() {
        assertEquals("A conta corrente deve informar corretamente o CPF de seu correntista",
                cpfDoJoao, contaDoJoao.getCpfDoCorrentista());
    }

    @Test
    public void testarContaEncerradaPeloGerenteDeContaErrado() throws ContaNaoGerenciadaException, SenhaInvalidaException, SaldoInsuficienteException {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos Roberto", 999, 20);

        gerenteDeContaCarlos.encerrarConta(contaDoJoao);

        assertTrue("Uma conta não pode ser encerrada por gerentes de conta " +
                "que não gerenciem aquela conta", contaDoJoao.isAtiva());
    }

    @Test
    public void testarContaEncerradaPeloGerenteDeContaCorreto()
            throws ContaNaoGerenciadaException, SenhaInvalidaException, SaldoInsuficienteException {

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
    public void testarContaEncerradaPorUmGerenteGeral()
            throws ContaNaoGerenciadaException, SenhaInvalidaException, SaldoInsuficienteException {

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

    @Test
    public void testarContaInvestimento() {
        ContaInvestimento conta = new ContaInvestimento(
                12345, joao, TipoInvestimento.FUNDOS_IMOBILIARIOS, 0.06f, 2000);
        System.out.println(conta.getTipo());
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