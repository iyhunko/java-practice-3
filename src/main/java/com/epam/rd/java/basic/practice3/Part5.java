package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {

    private static final String[] builderS = new String[]{
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
    };

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i + " -> " + decimal2Roman(i) + " -> " + roman2Decimal(decimal2Roman(i)));
        }
    }

    public static String decimal2Roman(int dec) {
        StringBuilder builder = new StringBuilder();

        int j = 0;
        if (dec >= 10 && dec % 10 == 0) {
            j = 1;
        }
        for (int i = 0; i < dec / 10 + 1 - j; i++) {
            if ((i != dec / 10 || dec % 10 == 0) && dec >= 10) {
                builder.append(builderS[9]);
            } else {
                builder.append(builderS[dec % 10 - 1]);
            }
        }

        int loc = handleBiggerThan100(dec, builder);

        int compareTo = (loc == 0) ? dec : loc;

        if (compareTo >= 90 && compareTo < 100) {
            handleFrom90To100(builder);
        }

        if (compareTo >= 40 && compareTo < 50) {
            handleFrom40To50(builder);
        }
        if (compareTo >= 50 && compareTo < 90) {
            handleFrom50To100(builder);
        }

        return builder.toString();
    }

    public static int roman2Decimal(String roman) {
        int value = 0;
        for (int i = 0; i < roman.length(); i++) {
            char s = roman.charAt(i);
            switch (s) {
                case 'I':
                    if (i + 1 < roman.length()
                            && (roman.charAt(i + 1) == 'V' || roman.charAt(i + 1) == 'X')) {
                        value -= 1;
                        break;
                    }
                    value += 1;
                    break;
                case 'V':
                    value += 5;
                    break;
                case 'X':
                    value += 10;
                    break;
                case 'L':
                    if (i > 0 && roman.charAt(i - 1) == 'X') {
                        value += 30;
                        break;
                    }
                    value += 50;
                    break;
                default:
                    if (i > 0 && roman.charAt(i - 1) == 'X') {
                        value += 80;
                        break;
                    }
                    value += 100;
            }
        }

        return value;
    }


    private static int handleBiggerThan100(int dec, StringBuilder builder) {
        int loc = 0;
        if (dec >= 100) {
            Pattern p = Pattern.compile("X{10,}?");
            Matcher m = p.matcher(builder);
            while (m.find()) {
                builder.replace(m.start(), m.end(), "C");
                m = p.matcher(builder);
                loc = dec % 100;
            }
        }

        return loc;
    }

    private static void handleFrom90To100(StringBuilder builder) {
        Pattern p = Pattern.compile("(.+)(X{8})");
        Matcher m = p.matcher(builder);
        m.find();
        builder.replace(m.start(2), m.end(2), "C");
    }

    private static void handleFrom40To50(StringBuilder builder) {
        Pattern p = Pattern.compile("(.+)(X{3})");
        Matcher m = p.matcher(builder);
        m.find();
        builder.replace(m.start(2), m.end(2), "L");
    }

    private static void handleFrom50To100(StringBuilder builder) {
        Pattern p = Pattern.compile("(X{5})");
        Matcher m = p.matcher(builder);
        m.find();
        builder.replace(m.start(1), m.end(1), "L");
    }
}
