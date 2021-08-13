public class Principal {

    public static void main(String[] args) {

        Zoologico zoo = new Zoologico();

        Cachorro caozinho = new Cachorro("Poodle");
        Gato gatinho = new Gato();

//        Animal meuAnimalGenerico = new Animal();
//              não conseguimos instanciar um Animal,
//              pois a classe é abstract

        zoo.receberAnimal(caozinho);
        zoo.receberAnimal(gatinho);
    }
}
