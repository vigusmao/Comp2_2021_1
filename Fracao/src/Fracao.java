import java.util.Objects;

public class Fracao {

    private int numerador;
    private int denominador;
    private boolean sinal;

    private Fracao minhaFracaoIrredutivel;

    /**
     * Cria uma fração. O sinal da fração será inferido a partir dos sinais
     * do numerador e denominador.
     *
     * Obs.: Caso a fração seja igual a zero, o denominador será sempre
     *       armazenado como 1.
     *
     * @param numerador um inteiro qualquer
     * @param denominador um inteiro diferente de zero
     */
    public Fracao(int numerador, int denominador) {
        this(Math.abs(numerador),
                Math.abs(denominador),
                numerador / (float) denominador >= 0);
    }

    /**
     * Cria uma fração. O sinal da fração é informado explicitamente.
     *
     * Obs.: Caso a fração seja igual a zero, o denominador será sempre
     *       armazenado como 1.
     *
     * @param numerador um inteiro qualquer não-negativo
     * @param denominador um inteiro positivo
     * @param sinal true, se positiva (ou zero); false, se negativa
     */
    public Fracao(int numerador, int denominador, boolean sinal) {  // overload do construtor
        if (numerador < 0) {
            throw new RuntimeException("O numerador precisa ser não-negativo!");
        }
        if (denominador <= 0) {
            throw new RuntimeException("O denominador precisa ser positivo!");
        }

        this.numerador = numerador;
        this.denominador = numerador != 0 ? denominador : 1;
        this.sinal = sinal;
        this.minhaFracaoIrredutivel = null;
    }

    /**
     * Retorna o (valor absoluto do) numerador da fração
     *
     * @return um inteiro não-negativo
     */
    public int getNumerador() {
        return this.numerador;
    }

    /**
     * Retorna o (valor absoluto do) denominador da fração
     *
     * @return um inteiro positivo
     */
    public int getDenominador() {
        return this.denominador;
    }

    /**
     * Retorna um boolean indicando o sinal da fração
     *
     * @return true, se a fração for não-negativa; false, se for negativa
     */
    public boolean getSinal() {
        return this.sinal;
    }

    public double getValorNumerico() {
        return (numerador / (double) denominador);
        // se não fizer o cast o resultado seria um int, porque int / int == int (truncado)
    }

    /**
     * Retorna uma Fracao que seja equivalente à fração original e irredutível
     * (numerador e denominador primos entre si).
     *
     * @return um outro objeto Fracao, caso esta fracao não seja irredutível;
     *         ou esta própria fração (this), caso ela própria já seja irredutível
     */
    public Fracao getFracaoIrredutivel() {
        if (this.minhaFracaoIrredutivel == null) {  // lazy instantiation
            int mdc = AritmeticaBasica.calcularMaximoDivisorComum(
                    this.numerador, this.denominador);

            if (mdc == 1) {
                this.minhaFracaoIrredutivel = this;

            } else {
                this.minhaFracaoIrredutivel = new Fracao(
                        this.numerador / mdc, this.denominador / mdc, this.sinal);
            }
        }

        return this.minhaFracaoIrredutivel;
    }

    /**
     * Efetua uma adição
     * @param outra o segundo operando da adição (o primeiro é esta própria fração)
     *
     * @return uma TERCEIRA fração, criada agora, com o resultado da operação
     */
    public Fracao somar(Fracao outra) {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    public Fracao somar(int numero) {
        return null;  // ToDo IMPLEMENT ME!!!!
    }


    public Fracao multiplicar(Fracao outra) {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    public Fracao multiplicar(int outra) {   // sobrecarga (overload) de método: tem o mesmo nome e assinatura diferente
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    /**
     * Modifica, possivelmente, o numerador e o denominador desta fração,
     * tornando-a irredutível (e equivalente à fração original)
     */
    public void simplificar() {
        // ToDo IMPLEMENT ME!!!!
    }

    @Override
    public String toString() {
        return (this.sinal ? "" : "-") +
                this.numerador +
                (this.denominador != 1
                        ? "/" + this.denominador
                        : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fracao fracao = (Fracao) o;

        Fracao minhaFracaoIrredutivel = getFracaoIrredutivel();
        Fracao outraFracaoIrredutivel = fracao.getFracaoIrredutivel();

        return minhaFracaoIrredutivel.numerador == outraFracaoIrredutivel.numerador &&
                minhaFracaoIrredutivel.denominador == outraFracaoIrredutivel.denominador &&
                this.sinal == fracao.sinal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerador, denominador, sinal);
    }
}
