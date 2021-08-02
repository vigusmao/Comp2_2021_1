public class Banco {

    public static void main(String[] args) {

        Correntista correntista = new Correntista();

        System.out.println(correntista.getNome());
        System.out.println(correntista.getLimiteChequeEspecial());

        PessoaFisica fulano = new PessoaFisica();

        System.out.println(fulano.getNome());
        System.out.println(fulano.getLimiteChequeEspecial());

    }
}
