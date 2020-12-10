package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {

    private static final String[] NUMBERS = new String[]{
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
    };

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i + " -> " + decimal2Roman(i) + " -> " + roman2Decimal(decimal2Roman(i)));
        }
    }

    public static String decimal2Roman(int dec) {
        StringBuilder number = new StringBuilder();

        int j = 0;
        if (dec >= 10 && dec % 10 == 0) {
            j = 1;
        }

        for (int i = 0; i < dec / 10 + 1 - j; i++) {
            if ((i != dec / 10 || dec % 10 == 0) && dec >= 10) {
                number.append(NUMBERS[9]);

            } else {
                number.append(NUMBERS[dec % 10 - 1]);
            }

        }

        int loc = 0;
        if (dec >= 100) {
            Pattern p = Pattern.compile("X{10,}?");
            Matcher m = p.matcher(number);
            while (m.find()) {
                number.replace(m.start(), m.end(), "C");
                m = p.matcher(number);
                loc = dec % 100;
            }
        }

        int compareTo = (loc == 0) ? dec : loc;

        if (compareTo >= 90 && compareTo < 100) {
            Pattern p = Pattern.compile("(.+)(X{8})");
            Matcher m = p.matcher(number);
            m.find();
            number.replace(m.start(2), m.end(2), "C");
        }
        if (compareTo >= 40 && compareTo < 50) {
            Pattern p = Pattern.compile("(.+)(X{3})");
            Matcher m = p.matcher(number);
            m.find();
            number.replace(m.start(2), m.end(2), "L");
        }
        if (compareTo >= 50 && compareTo < 90) {
            Pattern p = Pattern.compile("(X{5})");
            Matcher m = p.matcher(number);
            m.find();
            number.replace(m.start(1), m.end(1), "L");
        }

        return number.toString();
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
}
