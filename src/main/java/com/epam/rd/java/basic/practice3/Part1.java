package com.epam.rd.java.basic.practice3;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public final static String FILE_PATH = System.getProperty("user.dir") + "/part1.txt";

    public static void main(String[] args) {
        String input = Util.getInput(FILE_PATH);
        System.out.println();


        String convertedResult1 = convert1(input);
        System.out.println("Result of convert1:");
        System.out.println(convertedResult1);

        String convertedResult2 = convert2(input);
        System.out.println("Result of convert2:");
        System.out.println(convertedResult2);

        String convertedResult3 = convert3(input);
        System.out.println("Result of convert3:");
        System.out.println(convertedResult3);

        String convertedResult4 = convert4(input);
        System.out.println("Result of convert4:");
        System.out.println(convertedResult4);
    }

    /**
     * Format: `Login: email`
     */
    public static String convert1(String input) {
        String[] lines = input.split("\\n");

        StringBuilder result = new StringBuilder();

        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(";");

            result.append(parts[0]).append(": ").append(parts[2]).append(System.lineSeparator());
        }

        return result.toString();
    }

    public static String convert2(String input) {
        String[] lines = input.split("\\n");

        StringBuilder result = new StringBuilder();

        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(";");

            String[] names = parts[1].split(" ");
            String firstname = names[0];
            String lastname = names[1];

            result.append(lastname).append(" ").append(firstname).append(" (email: ").append(parts[2]).append(")").append(System.lineSeparator());
        }

        return result.toString();
    }

    public static String convert3(String input) {
        String[] emails = parseListOfEmails(input);

        String[] lines = input.split("\\n");

        StringBuilder result = new StringBuilder();

        for (int j = 0; j < emails.length; j++) {
            if (emails[j] != null) {
                StringBuilder emailLine = new StringBuilder();

                emailLine.append(emails[j]).append(" ==> ");

                for (int i = 1; i < lines.length; i++) {
                    String[] parts = lines[i].split(";");

                    Pattern regex = Pattern.compile("^.*" + emails[j] + ".*$");
                    Matcher matcher = regex.matcher(parts[2]);
                    if (matcher.find()) {
                        emailLine.append(parts[0]).append(", ");
                    }
                }

                String filledLine = emailLine.toString().replaceAll(", $", "");

                result.append(filledLine).append(System.lineSeparator());
            }
        }

        return result.toString();
    }

    public static String convert4(String input) {
        String[] lines = input.split("\\n");

        StringBuilder result4 = new StringBuilder();

        if (lines.length > 0) {
            result4.append(lines[0]).append(";Password").append(System.lineSeparator()).append(System.lineSeparator());
        }

        for (int i = 1; i < lines.length; i++) {
            result4.append(lines[i]).append(";").append(generateRandomInteger(1000, 9999)).append(System.lineSeparator());
        }

        return result4.toString();
    }

    private static int generateRandomInteger(int min, int max) {
        Random r = new Random();

        return r.nextInt((max - min)) + min;
    }

    private static String[] removeDuplicates(String[] items) {
        String[] uniqueItems = new String[items.length];

        int numberOfUniqueElements = 0;

        // Comparing each element with all other elements
        for (int i = 0; i < items.length; i++) {
            boolean isDuplication = false;
            for (int j = 0; j < uniqueItems.length; j++) {
                if (items[i].equals(uniqueItems[j])) {
                    isDuplication = true;
                    break;
                }
            }

            if (!isDuplication) {
                uniqueItems[numberOfUniqueElements] = items[i];
                numberOfUniqueElements++;
            }
        }

        String[] result = new String[numberOfUniqueElements];
        for (int i = 0; i < result.length; i++) {
            result[i] = uniqueItems[i];
        }

        return result;
    }

    private static String[] parseListOfEmails(String input) {
        String[] lines = input.split("\\n");

        String[] emailProviders = new String[lines.length - 1];

        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(";");

            Pattern regex = Pattern.compile("(?<=@)\\S+");
            Matcher matcher = regex.matcher(parts[2]);
            if (matcher.find()) {
                emailProviders[i - 1] = matcher.group();
            }
        }

        return removeDuplicates(emailProviders);
    }
}