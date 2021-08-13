public class Cachorro extends Mamifero {

    private String raca;

    public Cachorro(String raca) {
        this.raca = raca;
    }

    @Override
    public void emitirSom() {
        System.out.println("Latindo...");  // emitindo latidos!!!
    }
}
