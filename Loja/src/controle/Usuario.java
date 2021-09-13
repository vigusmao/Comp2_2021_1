package controle;

public class Usuario extends Pessoa {

    private long numeroCartaoDeCredito;

    public Usuario(long cpf, String nome) {
        super(cpf, nome);
    }

    public long getNumeroCartaoDeCredito() {
        return numeroCartaoDeCredito;
    }

    public void setNumeroCartaoDeCredito(long numeroCartaoDeCredito) {
        this.numeroCartaoDeCredito = numeroCartaoDeCredito;
    }
}
