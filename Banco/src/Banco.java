public class Banco {


    // Apenas testes rápidos

    public static void main(String[] args) {

        Correntista cicrano = new Correntista("Cicrano", 231232);
        Conta contaDoCicrano = new Conta(111, cicrano);

        try {
            contaDoCicrano.sacar(100, 1234);

            System.out.println("Saque bem sucedido!");


        } catch (SenhaInvalidaException e) {

            int contTentativas = e.getNumeroDeTentativasErradas();
            System.out.printf("\nCuidado! Esta foi sua %da tentativa",
                    contTentativas);

        } catch (SaldoInsuficienteException e) {

            float saldoFaltante = e.getSaldoFaltante();
            if (saldoFaltante < 1000) {
                System.out.println("Faltou pouco. Quer um empréstimo?");
            } else {
                System.out.println("Saldo insuficiente.");
            }

        } catch (Exception e) {
            System.out.println("Deu ruim!!! não sei o motivo!!!");
        }

        // continuo depois do try...catch

        // blablabla



    }
}
