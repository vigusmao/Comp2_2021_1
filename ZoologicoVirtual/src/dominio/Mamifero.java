package dominio;

public abstract class Mamifero extends Animal {

    public void mamar() {
        System.out.println("Alimentando-se a partir do leite materno...");
    }

    public void comerComida() {

    }

    @Override
    public void seAlimentar() {
        if (getIdadeEmAnos() < 2 || this.nome.equals("Tom")) {
            mamar();
        } else {
            comerComida();
        }
    }

    public void acasalar(Mamifero parceiro) {
        
    }
}
