import java.util.regex.*;
import java.util.Arrays;
import java.io.Console;

public class Main {

    private static final String[] rNumbs = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    private static int rome2num(String rNum) { // 1 to 10
        return (Arrays.asList(rNumbs).indexOf(rNum)+1);
    }

    private static String num2rome(int num) {
        if (num <= 0) throw new NullPointerException("Invalid Rome Number Result");
        StringBuilder res = new StringBuilder();
        while (num >= 100) {
            res.append("C");
            num -= 100;
        }
        if (num >= 90) {
            res.append("XC");
            num -= 90;
        } else if (num >= 50) {
            res.append("L");
            num -= 50;
        } else if (num >= 40) {
            res.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            res.append("X");
            num -= 10;
        }
        if (num > 0) res.append(rNumbs[num - 1]);
        return res.toString();
    }

    private static int calcExpr(int a, int b, String op) {
        return switch (op) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> -1;
        };
    }

    private static final Pattern patternA = Pattern.compile("^([1-9]|10)\\s*([*+-/])\\s*([1-9]|10)$");
    private static final Pattern patternR = Pattern.compile("^(X|IX|IV|V?I{0,3})\\s*([*+-/])\\s*(X|IX|IV|V?I{0,3})$");

    public static String calc (String expr) {
        Matcher matcherA = patternA.matcher(expr);
        Matcher matcherR = patternR.matcher(expr);
        if (matcherA.find()) {
            return Integer.toString(calcExpr(
                    Integer.parseInt(matcherA.group(1)),
                    Integer.parseInt(matcherA.group(3)),
                        matcherA.group(2)
                    ));
        } else if (matcherR.find()) {

            return num2rome(calcExpr(
                        rome2num(matcherR.group(1)),
                        rome2num(matcherR.group(3)),
                        matcherR.group(2)
                    ));
        } else {
            throw new NullPointerException("Invalid Expression");
        }
    }
}
