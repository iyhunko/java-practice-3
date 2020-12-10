package com.epam.rd.java.basic.practice3;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class Part4Test {
    @Test
    public void main() throws NoSuchAlgorithmException {
        assertEquals("A43C1B0AA53A0C908810C06AB1FF3967", Part4.hash("input", "MD5"));
    }
}