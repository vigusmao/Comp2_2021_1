import java.util.ArrayList;

public class Correntista extends PessoaFisica {

    private float limiteChequeEspecial;
    private ArrayList<ContaCorrente> contas;
    private ArrayList<AplicacaoFinanceira> investimentos;

    public float getLimiteChequeEspecial() {
        if (this.dataNascimento != null) {

        }

        return limiteChequeEspecial;



    }

    public void setLimiteChequeEspecial(float limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public float getTotalInvestido() {
        return 0;  // ToDo IMPLEMENT ME!!
    }
}
