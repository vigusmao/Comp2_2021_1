import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    // O(n^2)
    public static int obterArmariosAbertosN2(int n) {
        /* false: armário fechado;
           true: armário aberto */
        boolean[] armarios ;  // é null neste momento

        armarios = new boolean[n + 1];  // descartaremos a posição 0;
        // O array contém apenas false neste momento (armários todos fechados)

        for (int crianca = 1; crianca <= n; crianca++) {
            for (int armario = 1; armario <= n; armario++) {
                if (armario % crianca == 0) {
                    armarios[armario] = !armarios[armario];
                }
            }
        }

        ArrayList<Integer> armariosAbertos = new ArrayList<>();

        for (int armario = 1; armario <= n; armario++) {
            if (armarios[armario]) {
                armariosAbertos.add(armario);
            }
        }

        return armariosAbertos.size();
    }

    // O(n log n)
    public static int obterArmariosAbertosNLogN(int n) {
        /* false: armário fechado;
           true: armário aberto */
        boolean[] armarios ;  // é null neste momento

        armarios = new boolean[n + 1];  // descartaremos a posição 0;
        // O array contém apenas false neste momento (armários todos fechados)

        for (int crianca = 1; crianca <= n; crianca++) {
            for (int armario = crianca; armario <= n; armario += crianca) {
                armarios[armario] = !armarios[armario];
            }
        }

        ArrayList<Integer> armariosAbertos = new ArrayList<>();

        for (int armario = 1; armario <= n; armario++) {
            if (armarios[armario]) {
                armariosAbertos.add(armario);
            }
        }

        return armariosAbertos.size();
    }

    // O(n)
    public static int obterArmariosAbertosN(int n) {

        ArrayList<Integer> armariosAbertos = new ArrayList<>();

        for (int armario = 1; armario <= n; armario++) {
            int raiz = (int) Math.sqrt(armario);
            if (raiz * raiz == armario) {
                armariosAbertos.add(armario);
            }
        }

        return armariosAbertos.size();
    }

    // O(n^0.5)
    public static int obterArmariosAbertosSqrtN(int n) {

        ArrayList<Integer> armariosAbertos = new ArrayList<>();

        int base = 1;
        int armario = 1;
        while (armario <= n) {
            armariosAbertos.add(armario);  // armario é quadrado perfeito!
            base++;
            armario = base * base;
        }

        return armariosAbertos.size();
    }

    // O(1)  -- tempo constante
    public static int obterArmariosAbertosConstante(int n) {
        return (int) Math.sqrt(n);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.print("\n\nQuantos armários? ");
            Scanner scanner = new Scanner(System.in);
            int contArmarios = scanner.nextInt();
            if (contArmarios < 1) {
                return;
            }

            long inicio, duracao;
            int abertos;

            inicio = System.currentTimeMillis();
            abertos = obterArmariosAbertosN2(contArmarios);
            duracao = System.currentTimeMillis() - inicio;
            System.out.println("\nO(n^2)");
            System.out.println("Número de armários abertos: " + abertos);
            System.out.printf("Duração: %.3f segundos\n", duracao / 1000f);

            inicio = System.currentTimeMillis();
            abertos = obterArmariosAbertosNLogN(contArmarios);
            duracao = System.currentTimeMillis() - inicio;
            System.out.println("\nO(n log n)");
            System.out.println("Número de armários abertos: " + abertos);
            System.out.printf("Duração: %.3f segundos\n", duracao / 1000f);

            inicio = System.currentTimeMillis();
            abertos = obterArmariosAbertosN(contArmarios);
            duracao = System.currentTimeMillis() - inicio;
            System.out.println("\nO(n)");
            System.out.println("Número de armários abertos: " + abertos);
            System.out.printf("Duração: %.3f segundos\n", duracao / 1000f);

            inicio = System.currentTimeMillis();
            abertos = obterArmariosAbertosSqrtN(contArmarios);
            duracao = System.currentTimeMillis() - inicio;
            System.out.println("\nO(n^0.5)");
            System.out.println("Número de armários abertos: " + abertos);
            System.out.printf("Duração: %.3f segundos\n", duracao / 1000f);

            inicio = System.currentTimeMillis();
            abertos = obterArmariosAbertosConstante(contArmarios);
            duracao = System.currentTimeMillis() - inicio;
            System.out.println("\nO(1)");
            System.out.println("Número de armários abertos: " + abertos);
            System.out.printf("Duração: %.3f segundos\n", duracao / 1000f);
        }
    }
}
