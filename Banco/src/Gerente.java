import java.util.ArrayList;
import java.util.Date;

public class Gerente extends PessoaFisica {

    private final long numeroMatricula;

    private Date dataDeAdmissao;

    private ArrayList<ContaCorrente> contasGerenciadas;

    public Gerente(String nome, long cpf, long numeroMatricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroMatricula = numeroMatricula;
        this.contasGerenciadas = new ArrayList<>();
        this.dataDeAdmissao = new Date();  // data corrente
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
