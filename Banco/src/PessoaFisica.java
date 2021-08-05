import java.util.Date;

public class PessoaFisica {

    // private: acessível apenas de dentro da própria classe
    // sem modificador de acesso (default): disponível de dentro domesmo package
    // protected: disponível de dentro do mesmo package, e de dentro de qualquer subclasse
    // public: acessível  de qualquer lugar do seu código

    private final long cpf;
    private String nome;
    private Date dataNascimento;
    private String endereco;

//    // construtor DEFAULT: se não o escrevermos, será injetado automaticamente
//    public PessoaFisica() {
//        super();
//    }

    public PessoaFisica(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = "Nao informado";
        // etc.
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 30) {
            return;  // ToDo lançaria uma exceção
        }
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
