import java.util.*;

public class Dynamic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean stop = false;
        String answer = "a";
        while(!answer.equals("y") && !answer.equals("n")) {
            System.out.print("Neustart?(y/n) ");
            answer = sc.nextLine();
        }
        if(answer.equals("n")){
            stop = true;
        }
        while(!stop) {
            System.out.print("Betrag: ");
            int betrag = parseStringToInt(sc.nextLine());
            System.out.println();
            System.out.print("Stueckelung von Muenzen: ");
            int[] intArray = parseArrayToInt(sc.nextLine().split(" "));
            System.out.println();

            // Arrays sortieren in aufsteigender Reihenfolge
            Arrays.sort(intArray);

            int[] dp = new int[betrag + 1];

            minimumCoins(intArray, betrag);
            answer = "a";
            while(!answer.equals("y") && !answer.equals("n")) {
                System.out.print("Neustart?(y/n) ");
                answer = sc.nextLine();
            }
            if(answer.equals("n")){
                stop = true;
            }
        }
    }

    private static void minimumCoins(int[] coins, int amount) {
        Map<Integer, Integer> result = new HashMap<>();
        int[] dp = new int[amount + 1];
        int[] coinUsed = new int[amount + 1];

        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    coinUsed[i] = coin;
                }
            }
        }

        int currentAmount = amount;

        if (coinUsed[amount] == 0)
            System.out.println("Keine Antwort.");
        else {
            while (currentAmount > 0) {
                int coin = coinUsed[currentAmount];
                int count = result.getOrDefault(coin, 0);
                result.put(coin, count + 1);
                currentAmount -= coin;
            }
            System.out.println("Antwort mit dynamische Programmierung: ");
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(value + " * " + key);
            }
        }
    }

    private static int parseStringToInt(String line){
        int result = 0;
        try {
            result = Integer.parseInt(line);
        }
        catch (NumberFormatException e){
            System.out.println("Error converting \"" + line + "\" to an integer");
        }
        return result;
    }

    private static int[] parseArrayToInt(String[] arrayString){
        int[] result = new int[arrayString.length];
        for(int i = 0; i < result.length; i++){
            result[i] = parseStringToInt(arrayString[i]);
        }
        return result;
    }
}
