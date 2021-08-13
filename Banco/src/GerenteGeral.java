public class GerenteGeral extends Gerente {

    public GerenteGeral(String nome, long cpf, int matricula) {
        super(nome, cpf, matricula);
    }

    /**
     * Encaminha email ao correntista informado,
     * oferecendo empréstimo no valor igual a 10x o limite do cheque especial.
     *
     * @param correntista O correntista alvo
     */
    public void oferecerEmprestimo(Correntista correntista) {

    }

    @Override
    public void encerrarConta(Conta conta) {
        gerenciarConta(conta);
        super.encerrarConta(conta);
    }
}
