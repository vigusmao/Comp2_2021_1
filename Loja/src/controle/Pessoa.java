package controle;

public class Pessoa implements Transportador, Transportavel {

    private final long cpf;

    private String nome;

    public Pessoa(long cpf, String nome) {
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

    public void transportar(Transportavel transportavel, String endereco) {

    }

    @Override
    public Dimensoes getDimensoes() {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public int getPesoEmGramas() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }
}
