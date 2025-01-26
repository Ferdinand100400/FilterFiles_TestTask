package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    private List<String> strLong;
    private List<String> strDouble;
    private List<String> strString;
    @BeforeEach
    void setUp() {
        strLong = new ArrayList<>();
        strLong.add("345");
        strLong.add("123");
        strLong.add("345");
        strLong.add("3");
        strLong.add("-4");
        strLong.add("1234567890123456789");

        strDouble = new ArrayList<>();
        strDouble.add("567.2");
        strDouble.add("-6.78");
        strDouble.add("1.528535047E-25");
        strDouble.add("-0.001");

        strString = new ArrayList<>();
        strString.add("test");
        strString.add("Hello test");
        strString.add("12 years");
        strString.add("new file is test 1!");
        strString.add("Long");
        strString.add("test ok");

    }

    @Test
    void minValueLong() throws DifferentMyException {
        Long expected = -4L;
        assertEquals(expected, Statistics.minValueLong(strLong));
    }

    @Test
    void minValueDouble() throws DifferentMyException {
        Double expected = -6.78;
        assertEquals(expected, Statistics.minValueDouble(strDouble));
    }

    @Test
    void maxValueLong() throws DifferentMyException {
        Long expected = 1234567890123456789L;
        assertEquals(expected, Statistics.maxValueLong(strLong));
    }

    @Test
    void maxValueDouble() throws DifferentMyException {
        Double expected = 567.2;
        assertEquals(expected, Statistics.maxValueDouble(strDouble));
    }

    @Test
    void sumLong() throws DifferentMyException {
        Long expected = 1234567890123457601L;
        assertEquals(expected, Statistics.sumLong(strLong));
    }

    @Test
    void sumDouble() throws DifferentMyException {
        Double expected = 560.4190000000001;
        assertEquals(expected, Statistics.sumDouble(strDouble));
    }

    @Test
    void averageLong() throws DifferentMyException {
        Long expected = 205761315020576266L;
        assertEquals(expected, Statistics.AverageLong(strLong));
    }

    @Test
    void averageDouble() throws DifferentMyException {
        strDouble.remove(2);
        Double expected = 186.80633333333336;
        assertEquals(expected, Statistics.AverageDouble(strDouble));
    }

    @Test
    void sizeOfShortString() throws DifferentMyException {
        int expected = 4;
        assertEquals(expected, Statistics.sizeOfShortString(strString));
    }

    @Test
    void sizeOfLengthString() throws DifferentMyException {
        int expected = 19;
        assertEquals(expected, Statistics.sizeOfLengthString(strString));
    }
}