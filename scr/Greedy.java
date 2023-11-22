
import java.util.*;

public class Greedy {
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
            ArrayList<Integer> integerList = parseArrayToIntList(sc.nextLine().split(" "));
            System.out.println();

            // Arrays sortieren in aufsteigender Reihenfolge
            Collections.sort(integerList);

            // Array umkehren, um absteigende Reihenfolge zu erhalten
            Collections.reverse(integerList);

            Map<Integer, Integer> result = new HashMap<>();

            while (betrag > 0 && !integerList.isEmpty()) {
                while (!integerList.isEmpty() && integerList.get(0) > betrag)
                    integerList.remove(0);
                if (!integerList.isEmpty()) {
                    betrag -= integerList.get(0);
                    int count = result.getOrDefault(integerList.get(0), 0);
                    result.put(integerList.get(0), count + 1);
                }
            }
            if (betrag == 0) {
                System.out.println("Antwort von Greedy: ");
                for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                    Integer key = entry.getKey();
                    Integer value = entry.getValue();
                    System.out.println(value + " * " + key);
                }
            } else {
                System.out.println("Keine Antwort.");
            }
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

    private static ArrayList<Integer> parseArrayToIntList(String[] arrayString){
        ArrayList<Integer> result = new ArrayList<>();
        for(String line: arrayString){
            result.add(parseStringToInt(line));
        }
        return result;
    }
}
