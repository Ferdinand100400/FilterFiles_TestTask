package org.example;

import java.util.List;

public interface InputData {

    int searchArgument(String argument);
    List<String> searchFile() throws NullNameFileException;
    public String[] getInputData();
    public List<String> getListNamesFiles();

}
