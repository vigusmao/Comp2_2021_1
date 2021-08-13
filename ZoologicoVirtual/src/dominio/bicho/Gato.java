package dominio.bicho;

import dominio.Mamifero;

public class Gato extends Mamifero {

    @Override
    public void emitirSom() {
        System.out.println("Miando...");
    }
}
