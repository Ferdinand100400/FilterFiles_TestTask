package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReadInputData implements InputData {
    private String[] inputData;
    private List<String> listNamesFiles = new ArrayList<>();

    public ReadInputData(String[] inputData) {
        this.inputData = inputData;
    }

    @Override
    public int searchArgument(String argument) {
        for (int i = 0; i < inputData.length; i++) {
            if (inputData[i].equals(argument)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public List<String> searchFile() throws NullNameFileException {
        for (int i = 0; i < inputData.length; i++) {
            if (inputData[i].lastIndexOf(".txt") != -1) {
                listNamesFiles.add(inputData[i]);
            }
        }
        if (listNamesFiles.size() == 0) {
            throw new NullNameFileException("Вы не ввели ни одного файла");
        }
        return listNamesFiles;
    }
    @Override
    public String[] getInputData() {
        return inputData;
    }
    @Override
    public List<String> getListNamesFiles() {
        return listNamesFiles;
    }
}
