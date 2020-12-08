package com.epam.rd.java.basic.practice3;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String filePath = "/home/ivan/projects/epam/uwfeuzcd-task3/part1.txt";

        String input = Util.getInput(filePath);

        String convertedResult1 = convert1(input);
        System.out.println("Result of convert1:");
        System.out.println(convertedResult1);

        String convertedResult2 = convert2(input);
        System.out.println("Result of convert2:");
        System.out.println(convertedResult2);

        String convertedResult3 = convert3(input);
        System.out.println("Result of convert3:");
        System.out.println(convertedResult3);
    }
    
    public static String[] parseListOfEmails(String input) {
        String[] lines = input.split("\\n");

        String[] emailProviders = new String[lines.length-1];

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

    public static String[] removeDuplicates(String[] items) {
        //Create set from array elements
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>( Arrays.asList(items) );

        return linkedHashSet.toArray(new String[] {});
    }

    /**
     * Format: `Login: email`
     */
    public static String convert1(String input) {
        String[] lines = input.split("\\n");

        StringBuilder result = new StringBuilder();

        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(";");

            result.append(parts[0]).append(": ").append(parts[2]).append(System.getProperty("line.separator"));
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

            result.append(lastname).append(" ").append(firstname).append(" (email: ").append(parts[2]).append(")").append(System.getProperty("line.separator"));
        }

        return result.toString();
    }

    public static String convert3(String input) {
        String[] emails = parseListOfEmails(input);

        String[] lines = input.split("\\n");

        StringBuilder result = new StringBuilder();

        for(int j = 0; j < emails.length; j++) {
            if (emails[j] != null ) {
                StringBuilder emailLine = new StringBuilder();

                emailLine.append(emails[j]).append(" ==> ");

                for (int i = 1; i < lines.length; i++) {
                    String[] parts = lines[i].split(";");

                    Pattern regex = Pattern.compile("^.*"+emails[j]+".*$");
                    Matcher matcher = regex.matcher(parts[2]);
                    if (matcher.find()) {
                        emailLine.append(parts[0]).append(", ");
                    }
                }

                String filledLine = emailLine.toString().replaceAll(", $", "");

                result.append(filledLine).append(System.getProperty("line.separator"));
            }
        }

        return result.toString();
    }

    public static String convert4(String input) {
        return null;
    }
}