import java.util.Scanner;

public class Principal {

    public static int obterArmariosAbertosViaSimulacao(int n) {
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

        int contAbertos = 0;
        for (int armario = 1; armario <= n; armario++) {
            if (armarios[armario]) {
                contAbertos++;
            }
        }

        return contAbertos;
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
            int contArmariosAbertos = obterArmariosAbertosViaSimulacao(contArmarios);
            long duracao = System.currentTimeMillis() - inicio;

            System.out.println("Quantidade de armários abertos: " + contArmariosAbertos);
            System.out.printf("Duração: %.3f segundos\n", duracao / 1000f);
        }
    }
}
