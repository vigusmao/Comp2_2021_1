import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static ArrayList<Integer> obterArmariosAbertosViaSimulacao(int n) {
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

        return armariosAbertos;
    }

    public static int obterArmariosAbertos(int n) {
        return (int) Math.sqrt(n);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.print("Quantos armários? ");
            Scanner scanner = new Scanner(System.in);
            int contArmarios = scanner.nextInt();
            if (contArmarios < 1) {
                return;
            }

            long inicio = System.currentTimeMillis();
            ArrayList<Integer> armariosAbertos = obterArmariosAbertosViaSimulacao(contArmarios);

            long duracao = System.currentTimeMillis() - inicio;

            System.out.println("Armários abertos: " + armariosAbertos);
            System.out.printf("Duração: %.3f segundos\n", duracao / 1000f);
        }
    }
}
