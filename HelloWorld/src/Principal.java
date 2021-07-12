import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        System.out.println("Oi gente!!!");

        int x = 7;
        float y = 4.5f;

        System.out.println("Qual o seu nome?");
        Scanner meuScanner = new Scanner(System.in);
        String nome = meuScanner.nextLine();
        System.out.println("Qual sua idade?");
        int idade = meuScanner.nextInt();
        System.out.printf("Bom dia, %s, tudo bem? Você tem %d anos",
                nome, idade);


    }
}
