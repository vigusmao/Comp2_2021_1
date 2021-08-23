import java.util.ArrayList;
import java.util.Date;

public class Gerente extends PessoaFisica {

    private long numeroMatricula;

    private Date dataDeAdmissao;

    private ArrayList<Conta> contasGerenciadas;

    public Gerente(String nome, long cpf, long numeroMatricula) {
        super(nome, cpf);

        this.numeroMatricula = numeroMatricula;
        this.dataDeAdmissao = new Date();  // data corrente
        this.contasGerenciadas = new ArrayList<>();
    }

    public long getNumeroMatricula() {
        return numeroMatricula;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void gerenciarConta(Conta conta) {
        this.contasGerenciadas.add(conta);
        conta.setGerente(this);
    }

    public void deixarDeGerenciarConta(Conta conta) {
        this.contasGerenciadas.remove(conta);
    }

    public boolean ehGerenteDaConta(Conta conta) {
        return this.contasGerenciadas.contains(conta);
    }

    public void encerrarConta(Conta conta)
            throws ContaNaoGerenciadaException, SenhaInvalidaException, SaldoInsuficienteException {

        if (!this.contasGerenciadas.contains(conta)) {
            throw new ContaNaoGerenciadaException();
            // não vai encerrar se não é conta gerenciada por ele
        }

        try {
            conta.sacar(conta.getSaldoEmReais(), conta.getSenha());  // sacar valor restante na conta
            conta.encerrar();
        } catch (ContaInativaException e) {
            // a conta já está inativa mesmo, não preciso fazer nada
        }

        deixarDeGerenciarConta(conta);
    }

    @Override
    public String toString() {
        return String.format("Gerente %s (matrícula %d)",
                getNome(), this.numeroMatricula);
    }
}
