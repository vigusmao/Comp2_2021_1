public class Banco {






    public static void main(String[] args) {

        Correntista cicrano = new Correntista("Cicrano", 231232);

        System.out.println(cicrano.getNome());
        System.out.println(cicrano.getLimiteChequeEspecial());

        PessoaFisica fulano = new PessoaFisica("Beltrana", 432212);

        System.out.println(fulano.getNome());


        Conta contaDoCicrano = new Conta(111, cicrano);

        ContaInvestimento contaInvestimentoDoCicrano = new ContaInvestimento(
            222, cicrano, "RF", 0.01f);
        System.out.printf("\nSaldo da conta investimento: R$%.2f",
                contaInvestimentoDoCicrano.getSaldoEmReais());


        Gerente meuGerente = new Gerente("João", 12345678, 1111);
        meuGerente.setEndereco("Novo endereço, numero tal");

        System.out.println(meuGerente.getNome());
        System.out.println(meuGerente.getDataDeAdmissao());




        GerenteGeral gerentona = new GerenteGeral("Mariza Silva", 12333, 1);

//        gerentona.gerenciarConta(contaDoCicrano);
//        gerentona.encerrarConta(contaDoCicrano);


    }
}
