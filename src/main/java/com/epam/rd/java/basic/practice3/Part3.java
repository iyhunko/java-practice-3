package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static final String FILE_PATH = System.getProperty("user.dir") + "/part3.txt";

    public static final String REGEX = "[a-zA-Zа-яА-ЯіІїЇґҐ]+";

    public static void main(String[] args) {
        String input = Util.getInput(FILE_PATH);

        System.out.println("Result of convert:");
        System.out.println();
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        StringBuilder builder = new StringBuilder(input);
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(builder);

        while (m.find()) {
            if (m.group().length() > 1) {
                String firstChar = m.group().substring(0, 1);
                if (Character.isUpperCase(firstChar.charAt(0))) {
                    builder.replace(m.start(), m.start() + 1, m.group().substring(0, 1).toLowerCase());
                } else {
                    builder.replace(m.start(), m.start() + 1, m.group().substring(0, 1).toUpperCase());
                }
            }
        }

        return builder.toString();
    }
}
