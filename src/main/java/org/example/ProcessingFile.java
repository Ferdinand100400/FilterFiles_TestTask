package org.example;

import java.io.IOException;
import java.util.List;

public interface ProcessingFile {

    boolean CheckAvailableFile(String NameFile) throws NotFoundFileException, DifferentMyException;
    List<String> fileRead(String NameFile) throws NotFoundFileException, IOException, DifferentMyException;
    List<String> filterStringFromString(List<String> textFile) throws DifferentMyException;
    List<String> filterLongFromString(List<String> textFile) throws DifferentMyException;
    List<String> filterDoubleFromString(List<String> textFile);
}
