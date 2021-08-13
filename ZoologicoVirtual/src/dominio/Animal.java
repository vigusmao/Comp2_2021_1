import java.time.LocalDate;

public abstract class Animal {

    private String nome;

    private String especie;

    private LocalDate dataNascimento;

    public void respirar() {
        System.out.println("Respirando...");
    }

    public void seAlimentar() {
        System.out.println("Se alimentando...");
    }

    public abstract void emitirSom();

    public void morrer() {
        System.out.println("Morri.");
    }

    public void dormir() {
        System.out.println("Dormindo...");
    }

    public int getIdadeEmAnos() {
        return 0;  // ToDo ???
    }

    public float getTemperatura() {
        return 0;  // ToDo ???
    }
}
