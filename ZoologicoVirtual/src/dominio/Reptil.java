package dominio;

public abstract class Reptil extends Animal {

    @Override
    public float getTemperatura() {
        return 21.5f;
    }

    public void seArrastar() {
        System.out.println("Me arrastando...");
    }
}
