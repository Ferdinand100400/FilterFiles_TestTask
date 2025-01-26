package org.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProcessingFileImpl implements ProcessingFile {
    private static String dirFiles = "src\\main\\resources\\";


    public ProcessingFileImpl(String dirFiles) {
        ProcessingFileImpl.dirFiles = dirFiles;
    }

    @Override
    public boolean CheckAvailableFile(String nameFile) throws NotFoundFileException, DifferentMyException {
        Path pathFile;
        try {
            pathFile = Path.of(dirFiles + nameFile).toAbsolutePath();
        }
        catch (InvalidPathException e) {
            throw new DifferentMyException("Недопустимое имя входного файла: " + nameFile);
        }
        System.out.println(pathFile);
        boolean checkAvailableFile;
        if (!Files.exists(pathFile)) {
            throw new NotFoundFileException("Файл " + nameFile + " не найден в директории: " + pathFile.getParent());
        } else {
            checkAvailableFile = true;
        }
        return checkAvailableFile;
    }

    @Override
    public List<String> fileRead(String nameFile) throws NotFoundFileException, DifferentMyException {
        List<String> textFile = new ArrayList<>();
        if (CheckAvailableFile(nameFile)) {
            Path pathFile = Path.of(dirFiles + nameFile).toAbsolutePath();
            try {
                textFile = Files.readAllLines(pathFile);
            } catch (IOException e) {
                throw new DifferentMyException("Ошибка чтения файла");
            }
        }
        return textFile;
    }

    @Override
    public List<String> filterLongFromString(List<String> textFile) {
        List<String> LongIntegerList = new ArrayList<>();
        for (int i = 0; i < textFile.size(); i++) {
            try {
                Long l = Long.parseLong(textFile.get(i));
                LongIntegerList.add(textFile.get(i));
            } catch (NumberFormatException e) {

            }
        }
        return LongIntegerList;
    }

    @Override
    public List<String> filterDoubleFromString(List<String> textFile) {
        List<String> doubleList = new ArrayList<>();
        for (int i = 0; i < textFile.size(); i++) {
            try {
                long l = Long.parseLong(textFile.get(i));
            } catch (NumberFormatException e1) {
                try {
                    if (!checkExceedLimitLong(textFile.get(i), false)) {
                        Double d = Double.parseDouble(textFile.get(i));
                        doubleList.add(textFile.get(i));
                    }
                } catch (NumberFormatException e2) {

                }
            }

        }
        return doubleList;
    }

    @Override
    public List<String> filterStringFromString(List<String> textFile) throws DifferentMyException {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < textFile.size(); i++) {
            try {
                long l = Long.parseLong(textFile.get(i));
            } catch (NumberFormatException e1) {
                try {
                    double d = Double.parseDouble(textFile.get(i));
                    if (checkExceedLimitLong(textFile.get(i), true)) {
                        stringList.add(textFile.get(i));
                    }
                } catch (NumberFormatException e2) {
                    try {
                        checkExceedLimitDouble(textFile.get(i));
                        stringList.add(textFile.get(i));
                    } catch (Exception e) {
                        throw new DifferentMyException("Ошибка преобразования строки: " + textFile.get(i));
                    }
                }
            }
        }
        return stringList;
    }

    public static String getDirFiles() {
        return dirFiles;
    }

    public boolean checkExceedLimitLong(String textFileIndex, boolean infoOutPrint) {
        Double.parseDouble(textFileIndex);
        try {
            BigInteger bigInteger = new BigInteger(textFileIndex);
            if (infoOutPrint) {
                System.out.println("Превышен предел значений для типа Long, поэтому строка " + textFileIndex + " будет записана в строковый файл");
            }
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public boolean checkExceedLimitDouble(String textFileIndex) {
        try {
            BigDecimal bigDecimal = new BigDecimal(textFileIndex);
            System.out.println("Превышен предел значений для типа Double, поэтому строка " + textFileIndex + " будет записана в строковый файл");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
