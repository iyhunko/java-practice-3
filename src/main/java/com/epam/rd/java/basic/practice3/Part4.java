package com.epam.rd.java.basic.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {

    public static void main(String[] args) throws NoSuchAlgorithmException  {
        System.out.println("Result of SHA-256:");
        System.out.println(hash("passwort", "SHA-256"));

        System.out.println();

        System.out.println("Result of MD5:");
        System.out.println(hash("password", "MD5"));
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException  {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());

        byte[] hash = digest.digest();
        StringBuilder builder = new StringBuilder();

        for (byte b : hash) {
            if (b < 0) {
                String x = Integer.toHexString(b);
                String y = x.substring(x.length() - 2);
                builder.append(y);

                continue;
            }
            builder.append(Integer.toHexString(b));
        }

        return builder.toString().toUpperCase();
    }

}
