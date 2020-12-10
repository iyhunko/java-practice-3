package com.epam.rd.java.basic.practice3;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class Part5Test {
    @Test
    public void shouldConvertDecimalToRoman() {
        assertEquals("I", Part5.decimal2Roman(1));
    }

    @Test
    public void shouldConvertRomanToDecimal() {
        assertEquals(1, Part5.roman2Decimal("I"));
    }
}