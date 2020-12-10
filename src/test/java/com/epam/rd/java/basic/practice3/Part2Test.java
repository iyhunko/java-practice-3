package com.epam.rd.java.basic.practice3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Part2Test {
    @Test
    public void main() {
        assertEquals("min", Part2.getMinLengthWords("min maxmax"));
        assertEquals("maxmax", Part2.getMaxLengthWords("min maxmax"));
    }
}