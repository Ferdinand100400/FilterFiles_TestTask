package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class ProcessingFileTest {


    private ProcessingFile processingFile;
    private List<String> inputFile;

    @BeforeEach
    void setUp() {
        processingFile = new ProcessingFileImpl("src\\test\\resources\\");

        inputFile = new ArrayList<>();
        inputFile.add("test");
        inputFile.add("Hello test");
        inputFile.add("345");
        inputFile.add("567.2");
        inputFile.add("123");
        inputFile.add("-6.78");
        inputFile.add("345");
        inputFile.add("12 years");
        inputFile.add("3");
        inputFile.add("-4");
        inputFile.add("new file is test 1!");
        inputFile.add("1.528535047E-25");
        inputFile.add("Long");
        inputFile.add("1234567890123456789");
        inputFile.add("-0.001");
        inputFile.add("test ok");
    }

    @Test
    void NotFoundFile() throws NotFoundFileException {
        try {
            assertEquals(false, processingFile.CheckAvailableFile("test.txt"));
        } catch (NotFoundFileException | DifferentMyException e) {
            e.printStackTrace();
        }
    }

    @Test
    void FoundedFile() throws NotFoundFileException {
        try {
            assertEquals(true, processingFile.CheckAvailableFile("in1.txt"));
        } catch (NotFoundFileException | DifferentMyException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fileRead() throws NotFoundFileException, IOException, DifferentMyException {
        assertEquals(inputFile, processingFile.fileRead("in1.txt"));
    }

    @Test
    void filterLongFromString() throws DifferentMyException {
        List<String> expected = new ArrayList<>();
        expected.add("345");
        expected.add("123");
        expected.add("345");
        expected.add("3");
        expected.add("-4");
        expected.add("1234567890123456789");
        assertEquals(expected, processingFile.filterLongFromString(inputFile));
    }

    @Test
    void filterDoubleFromString() {
        List<String> expected = new ArrayList<>();
        expected.add("567.2");
        expected.add("-6.78");
        expected.add("1.528535047E-25");
        expected.add("-0.001");
        assertEquals(expected, processingFile.filterDoubleFromString(inputFile));
    }

    @Test
    void filterStringFromString() throws DifferentMyException {
        List<String> expected = new ArrayList<>();
        expected.add("test");
        expected.add("Hello test");
        expected.add("12 years");
        expected.add("new file is test 1!");
        expected.add("Long");
        expected.add("test ok");
        assertEquals(expected, processingFile.filterStringFromString(inputFile));
    }
}