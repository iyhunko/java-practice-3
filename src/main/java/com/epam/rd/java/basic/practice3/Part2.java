package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static final String FILE_PATH = System.getProperty("user.dir") + "/part2.txt";

    private static final String REGEX = "[a-zA-Zа-яА-ЯіІїЇґҐ]+";

    public static void main(String[] args) {
        String input = Util.getInput(FILE_PATH);

        System.out.println("Result of convert:");
        System.out.println();
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        StringBuilder result = new StringBuilder();

        result.append("Min: ").append(getMinLengthWords(input)).append(System.lineSeparator());
        result.append("Max: ").append(getMaxLengthWords(input));

        return result.toString();
    }

    public static String getMinLengthWords(String input) {
        StringBuilder builder = new StringBuilder();
        int count = Integer.MAX_VALUE;

        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(input);

        while (m.find()) {
            Pattern p2 = Pattern.compile(m.group());
            Matcher m2 = p2.matcher(builder);
            if (count > m.group().length()) {
                StringBuilder builder2 = new StringBuilder();
                count = m.group().length();
                builder2.append(m.group());
                builder = builder2;
            } else if (count == m.group().length() && !(m2.find())) {
                builder.append(", ").append(m.group());
            }
        }

        return builder.toString();
    }

    public static String getMaxLengthWords(String input) {
        StringBuilder builder = new StringBuilder();
        int count = 0;

        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(input);

        while (m.find()) {
            Pattern p2 = Pattern.compile(m.group());
            Matcher m2 = p2.matcher(builder);
            if (count < m.group().length()) {
                StringBuilder builder2 = new StringBuilder();
                count = m.group().length();
                builder2.append(m.group());
                builder = builder2;
            } else if (count == m.group().length() && !(m2.find())) {
                builder.append(", ").append(m.group());
            }
        }

        return builder.toString();
    }
}
