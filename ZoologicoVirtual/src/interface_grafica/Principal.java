package interface_grafica;

import dominio.bicho.Cachorro;
import dominio.bicho.Gato;
import controle.Zoologico;

public class Principal {

    public static void main(String[] args) {

        Zoologico zoo = new Zoologico();

        Cachorro caozinho = new Cachorro("Poodle");
        Gato gatinho = new Gato();

//        dominio.Animal meuAnimalGenerico = new dominio.Animal();
//              não conseguimos instanciar um dominio.Animal,
//              pois a classe é abstract

        zoo.receberAnimal(caozinho);
        zoo.receberAnimal(gatinho);
    }
}
