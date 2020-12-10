package com.epam.rd.java.basic.practice3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("Result of SHA-256:");
        System.out.println(hash("input", "SHA-256"));

        System.out.println();

        System.out.println("Result of MD5:");
        System.out.println(hash("input", "MD5"));
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes(StandardCharsets.UTF_8));

        byte[] hash = digest.digest();
        char[] chars = new char[hash.length * 2];

        for (int i = 0; i < hash.length; i++) {
            toChars(hash[i], chars, i);
        }

        return new String(chars);
    }

    private static void toChars(byte b, char[] chars, int i) {
        int upper = b & 0b1111_0000;
        upper = Math.abs(upper / 16);
        chars[i * 2] = toHex(upper);

        int lower = b & 0b0000_1111;
        chars[i * 2 + 1] = toHex(lower);
    }

    private static char toHex(int a) {
        return (a < 10) ? (char) (a + '0') : (char) (a + 'A' - 10);
    }

}
