package org.example;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InputDataTest {

    private InputData inputData;

    @Before
    public void setUp() {
        String[] args = {"-o", "/some/path", "-s", "test.txt", "file1.txt", "-p", "simple", "file2.txt"};
        inputData = new ReadInputData(args);
    }

    @Test
    public void searchArgumentOf_oResultIs0() {
        assertEquals(0, inputData.searchArgument("-o"));
    }

    @Test
    public void searchArgumentOf_sResultIs2() {
        assertEquals(2, inputData.searchArgument("-s"));
    }

    @Test
    public void searchArgumentOf_pResultIs5() {
        assertEquals(5, inputData.searchArgument("-p"));
    }

    @Test
    public void searchArgumentOf_aResultIsNotSearch() {
        assertEquals(-1, inputData.searchArgument("-a"));
    }

    @Test
    public void searchFile() throws NullNameFileException {
        List<String> expected = new ArrayList<>();
        expected.add("test.txt");
        expected.add("file1.txt");
        expected.add("file2.txt");
        List<String> result = inputData.searchFile();
        assertEquals(expected, result);

    }

    @Test
    public void searchFileNullNameFileException() throws NullNameFileException {
        List<String> expected = new ArrayList<>();
        String[] args = {"-o", "/some/path", "-s", "-p", "simple"};
        InputData inputData = new ReadInputData(args);
        try {
            List<String> result = inputData.searchFile();
            assertEquals(expected, result);
        } catch (NullNameFileException e) {
            e.printStackTrace();
        }
    }
}