import java.util.Scanner;

public class TestCalc {
    public static void main(String[] args) { //for testing
        for (int i =1;i<=100;i++) // test rome <-> arab conversions
            System.out.println(i + "\t" +
                    (i <= 10 ? Main.rome2num(Main.num2rome(i)) : "-") +
                    "\t"  + Main.num2rome(i)
            );
        Scanner myInput = new Scanner(System.in);
        while (true) { // test calc() from input
            System.out.println("Enter Expression:");
            String expr = myInput.nextLine().toUpperCase();
            if (expr.isEmpty()) return;
            System.out.println("Result: " + Main.calc(expr));
        }
    }
}
