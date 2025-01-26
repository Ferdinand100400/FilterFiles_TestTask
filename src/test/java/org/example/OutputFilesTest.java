package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutputFilesTest {

    private OutputFile outputFile;
    private Path expectedPath;
    private List<String> textList;
    private ProcessingFile file;
    @BeforeEach
    void setUp() {
        outputFile = new OutputFiles("/some/path", "simple_");
        expectedPath = Path.of("src/test/some/path/simple_integers.txt").toAbsolutePath();

        file = new ProcessingFileImpl("src\\test\\");
    }

    @Test
    void createOutputPathFile() throws DifferentMyException {
        assertEquals(expectedPath, outputFile.createOutputPathFile("integers"));
    }

    @Test
    void createOutputFile() throws IOException, NotFoundFileException, DifferentMyException {
        textList = new ArrayList<>();
        textList.add("345");
        textList.add("123");
        textList.add("345");
        textList.add("3");
        textList.add("-4");
        textList.add("1234567890123456789");
        outputFile.createOutputFile(expectedPath, textList, false);
        file = new ProcessingFileImpl("src\\test\\some\\path\\");
        List<String> result = file.fileRead("simple_integers.txt");
        assertEquals(textList, result);
    }

    @Test
    void createOutputFileNullListText() throws IOException, NotFoundFileException, DifferentMyException {
        textList = new ArrayList<>();
        outputFile.createOutputFile(expectedPath, textList, false);
        file = new ProcessingFileImpl("src\\test\\some\\path\\");
        List<String> result = file.fileRead("simple_strings.txt");
        boolean res = false;
        if (result.size() == 1) {
            res = true;
        }
        assertEquals(true, res);
    }
}