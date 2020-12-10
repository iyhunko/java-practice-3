package com.epam.rd.java.basic.practice3;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class Part6Test {
    @Test
    public void main() {
        assertEquals("ok", Part6.convert("OK"));
    }
}