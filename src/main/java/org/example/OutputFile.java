package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface OutputFile {

    Path createOutputPathFile(String nameFile) throws DifferentMyException;
    void createOutputFile(Path path, List<String> textFile, boolean arg_a) throws IOException, DifferentMyException;
}
