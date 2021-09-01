package controle;

public class Usuario {

    private final long cpf;

    private String nome;

    private String endereco;

    private long numeroCartaoDeCredito;

    public Usuario(long cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public long getNumeroCartaoDeCredito() {
        return numeroCartaoDeCredito;
    }

    public void setNumeroCartaoDeCredito(long numeroCartaoDeCredito) {
        this.numeroCartaoDeCredito = numeroCartaoDeCredito;
    }
}
