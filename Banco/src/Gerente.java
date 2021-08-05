import java.util.ArrayList;
import java.util.Date;

public class Gerente extends PessoaFisica {

    private long numeroMatricula;

    private Date dataDeAdmissao;

    private ArrayList<ContaCorrente> contasGerenciadas;

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

    public void gerenciarConta(ContaCorrente conta) {
        this.contasGerenciadas.add(conta);
        conta.setGerente(this);
    }

    public void deixarDeGerenciarConta(ContaCorrente conta) {
        this.contasGerenciadas.remove(conta);
    }

    public boolean ehGerenteDaConta(ContaCorrente conta) {
        return this.contasGerenciadas.contains(conta);
    }

    public void encerrarConta(ContaCorrente conta) {
        if (!this.contasGerenciadas.contains(conta)) {
            return;  // ToDo lançar exceção
            // não vai encerrar se não é conta gerenciada por ele
        }

        conta.sacar(conta.getSaldoEmReais());  // sacar valor restante na conta
        conta.encerrar();

        deixarDeGerenciarConta(conta);
    }

    @Override
    public String toString() {
        return String.format("Gerente %s (matrícula %d)",
                getNome(), this.numeroMatricula);
    }
}
