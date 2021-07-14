import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static int[] obterArmariosAbertosViaSimulacao(int n) {
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

        int[] resultado = new int[armariosAbertos.size()];
        for (int i = 0; i < armariosAbertos.size(); i++) {
            resultado[i] = armariosAbertos.get(i);
        }

        return resultado;
    }

    public static int obterArmariosAbertos(int n) {
        return (int) Math.sqrt(n);
    }

    public static String imprimirArray(int[] array) {
        String resultado = "";
        for (int i = 0; i < array.length; i++) {
            resultado += array[i];
            if (i < array.length - 1) {
                resultado += ", ";
            }
        }
        return resultado;
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
            int[] armariosAbertos = obterArmariosAbertosViaSimulacao(contArmarios);

            long duracao = System.currentTimeMillis() - inicio;

            System.out.println("Armários abertos: " + imprimirArray(armariosAbertos));
            System.out.printf("Duração: %.3f segundos\n", duracao / 1000f);
        }
    }
}
