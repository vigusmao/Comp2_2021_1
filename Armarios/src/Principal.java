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

        int[] armariosAbertos = new int[10];
        /*
                 armariosAbertos --------> |||  $0FC6A6  |||
                                                    {1}        {4}
         */


        int contAbertos = 0;
        for (int armario = 1; armario <= n; armario++) {
            if (armarios[armario]) {

                if (contAbertos >= armariosAbertos.length) {  // OVERFLOW!!!!

                    // cria um novo array
                    int[] novoArray = new int[armariosAbertos.length * 2];

                    /*
                         novoArray   --------> |||  $43210B  |||
                                                        {1}      {4}    { }     { }
                    */
                    // copia o array corrente inteiro para o novo array
                    for (int i = 0; i < armariosAbertos.length; i++) {
                        novoArray[i] = armariosAbertos[i];
                    }

                    armariosAbertos = novoArray;

                    /*

                       SEM REFERÊNCIAS --------> |||  $0FC6A6  |||
                                                        {1}        {4}

                       armariosAbertos --------> |||  $43210B  |||
                       novoArray   -------->     |||  $43210B  |||
                                                        {1}        {4}    { }     { }
                    */
                }

                armariosAbertos[contAbertos] = armario;
                contAbertos++;
            }
        }

        int[] resultado = new int[contAbertos];
        for (int i = 0; i < contAbertos; i++) {
            resultado[i] = armariosAbertos[i];
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
