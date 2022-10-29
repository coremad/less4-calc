import java.util.List;
import java.util.regex.*;
import java.util.Arrays;

class Main {

    private static final List<String> rNumbs = Arrays.asList("error", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

    static int rome2num(String rNum) { // 1 to 10
        return rNumbs.indexOf(rNum);
    }

    static String num2rome(int num) { // 1 to 399
        if (num <= 0) throw new NullPointerException("Invalid Rome Number Result!");
        StringBuilder res = new StringBuilder();
        while (num >= 100) {
            res.append("C"); num -= 100;
        }
        if (num >= 90) {
            res.append("XC"); num -= 90;
        } else if (num >= 50) {
            res.append("L"); num -= 50;
        } else if (num >= 40) {
            res.append("XL"); num -= 40;
        }
        while (num >= 10) {
            res.append("X"); num -= 10;
        }
        if (num > 0) res.append(rNumbs.get(num));
        return res.toString();
    }

    private static int calcExpr(int a, int b, String op) {
        return switch (op) {
            case ("*") -> a * b;
            case ("/") -> a / b;
            case ("-") -> a - b;
            default -> a + b; // op can be passed only +-/*
        };
    }

    private static final Pattern patternA = Pattern.compile("^([1-9]|10)\\s*([*+-/])\\s*([1-9]|10)$"),
                                patternR = Pattern.compile("^(X|IX|IV|V?I{0,3})\\s*([*+-/])\\s*(X|IX|IV|V?I{0,3})$");

    public static String calc (String input) {
        Matcher matcherA = patternA.matcher(input),
                matcherR = patternR.matcher(input);
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
        } else throw new NullPointerException("Invalid Expression!");
    }
}
