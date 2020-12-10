package com.epam.rd.java.basic.practice3;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class Part1Test {
    @Test
    public void main() {
        assertEquals("", Part1.convert1("input1"));
        assertEquals("", Part1.convert2("input2"));
        assertEquals("", Part1.convert3("input3"));
        assertThat(Part1.convert4(" some fake text"), CoreMatchers.containsString("Password"));
    }
}