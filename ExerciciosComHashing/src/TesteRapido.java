import java.util.HashMap;
import java.util.HashSet;

public class TesteRapido {

    public static void main(String[] args) {

        HashSet<Integer> conjunto = new HashSet<>();

        conjunto.add(5);
        conjunto.add(600);
        conjunto.add(43);
        conjunto.add(-10000000);
        conjunto.add(-78956);
        conjunto.add(44367);
        conjunto.add(454);
        conjunto.add(0);

        for (int elemento : conjunto) {
            System.out.println(elemento);
        }

        HashMap<String, Integer> diasDoMesPorNome = new HashMap<>();
        diasDoMesPorNome.put("janeiro", 31);
        diasDoMesPorNome.put("fevereiro", 28);
        diasDoMesPorNome.put("março", 31);
        diasDoMesPorNome.put("abril", 30);
        diasDoMesPorNome.put("maio", 31);
        diasDoMesPorNome.put("junho", 30);
        diasDoMesPorNome.put("julho", 31);

        for (String mes : diasDoMesPorNome.keySet()) {
            System.out.println(mes);
        }
    }
}
