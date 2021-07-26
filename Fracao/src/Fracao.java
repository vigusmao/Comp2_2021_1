public class Fracao {

    private int numerador;
    private int denominador;
    private boolean sinal;

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
                numerador / denominador >= 0);
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
        this.denominador = denominador;
        this.sinal = sinal;
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
        return null;  // ToDo IMPLEMENT ME!!!!
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


}
