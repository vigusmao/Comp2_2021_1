import java.util.ArrayList;

public class Correntista extends PessoaFisica {

    private static final int LIMITE_DEFAULT = 100;

    private float limiteChequeEspecial;
    private ArrayList<ContaCorrente> contas;
    private ArrayList<AplicacaoFinanceira> investimentos;

    public Correntista(String nome, long cpf) {
        super(nome, cpf);
        this.contas = new ArrayList<>();
        this.investimentos = new ArrayList<>();
        this.limiteChequeEspecial = LIMITE_DEFAULT;
    }

    public float getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(float limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public float getTotalInvestido() {
        return 0;  // ToDo IMPLEMENT ME!!
    }


}
