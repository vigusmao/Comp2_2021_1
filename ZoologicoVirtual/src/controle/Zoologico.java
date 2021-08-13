package controle;

import dominio.Animal;

import java.util.ArrayList;

public class Zoologico {

    private ArrayList<Animal> bichos;

    public Zoologico() {
        this.bichos = new ArrayList<>();
    }

    public void receberAnimal(Animal novoBicho) {
        System.out.println("Recebi um novo dominio.bicho do tipo " +
                novoBicho.getClass().getName() + ".");

        this.bichos.add(novoBicho);

        novoBicho.seAlimentar();

        novoBicho.emitirSom();

        System.out.println(novoBicho.getNome());
    }
}
