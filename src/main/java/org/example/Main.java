package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, DifferentMyException {
        InputData inputData = new ReadInputData(args);
        List<String> listNamesFiles = new ArrayList<>();
        ProcessingFile processingFile = new ProcessingFileImpl("");

        try {
            listNamesFiles = inputData.searchFile();
        } catch (NullNameFileException e) {
            System.out.println(e.getMessage());
        }


        List<String> integers = new ArrayList<>();
        List<String> doubles = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        for (String NameFile : listNamesFiles) {
            try {
                List<String> longFromFile = processingFile.filterLongFromString(processingFile.fileRead(NameFile));
                integers.addAll(longFromFile);

                List<String> doubleFromFile = processingFile.filterDoubleFromString(processingFile.fileRead(NameFile));
                doubles.addAll(doubleFromFile);

                List<String> stringFromFile = processingFile.filterStringFromString(processingFile.fileRead(NameFile));
                strings.addAll(stringFromFile);
            } catch (NotFoundFileException | DifferentMyException e) {
                System.out.println(e.getMessage());
            }
        }

        String path = "";
        String prefixName = "";
        if (inputData.searchArgument("-o") != -1) {
            String[] str = inputData.getInputData();
            path = str[inputData.searchArgument("-o") + 1];
        }
        if (inputData.searchArgument("-p") != -1) {
            String[] str = inputData.getInputData();
            prefixName = str[inputData.searchArgument("-p") + 1];
        }
        OutputFiles outputFile = new OutputFiles(path, prefixName);

        boolean flagRewrite = false;
        if (inputData.searchArgument("-a") != -1) {
            flagRewrite = true;
        }
        Path pathInt = null;
        Path pathDouble = null;
        Path pathString = null;
        try {
            pathInt = outputFile.createOutputPathFile("integers");
            pathDouble = outputFile.createOutputPathFile("floats");
            pathString = outputFile.createOutputPathFile("strings");
        } catch (DifferentMyException e) {
            outputFile.setOutputPrefix("");
            outputFile.setOutputPath("");
            System.out.println(e.getMessage());
            try {
                pathInt = outputFile.createOutputPathFile("integers");
                pathDouble = outputFile.createOutputPathFile("floats");
                pathString = outputFile.createOutputPathFile("strings");
            } catch (Exception e1) {
                System.out.println("Ошибка создания директории");
            }
        }

        try {
            outputFile.createOutputFile(pathInt, integers, flagRewrite);
            outputFile.createOutputFile(pathDouble, doubles, flagRewrite);
            outputFile.createOutputFile(pathString, strings, flagRewrite);
        } catch (DifferentMyException e) {
            System.out.println(e.getMessage());
        }

        boolean flag = false;
        if ((inputData.searchArgument("-s") == -1) && (inputData.searchArgument("-f") == -1)) {
            System.out.println("Вы не ввели характеристику статистики, поэтому краткая статистика:");
            flag = true;
        } else if ((inputData.searchArgument("-s") != -1) && (inputData.searchArgument("-f") != -1)) {
            System.out.println("Вы ввели обе характеристики статистики, поэтому краткая статистика:");
            flag = true;
        }

        if (((inputData.searchArgument("-s") != -1) && (inputData.searchArgument("-f") == -1)) || (flag)) {
            System.out.println("Кол-во целых чисел записанных в выходной файл: " + integers.size());
            System.out.println("Кол-во дробных чисел записанных в выходной файл: " + doubles.size());
            System.out.println("Кол-во строк записанных в выходной файл: " + strings.size());
        } else if ((inputData.searchArgument("-s") == -1) && (inputData.searchArgument("-f") != -1)) {
            try {
                Statistics.checkNull(integers);
                System.out.println("Статистика для целых чисел записанных в выходной файл: ");
                System.out.println("Кол-вол: " + integers.size());
                System.out.println("Минимальное значение: " + Statistics.minValueLong(integers));
                System.out.println("Максимальное значение: " + Statistics.maxValueLong(integers));
                try {
                    System.out.println("Сумма чисел: " + Statistics.sumLong(integers));
                    System.out.println("Среднее значение: " + Statistics.AverageLong(integers));
                } catch (DifferentMyException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(" ");
            } catch (DifferentMyException e) {
                System.out.println(e.getMessage());
            }

            try {
                Statistics.checkNull(doubles);
                System.out.println("Статистика для дробных чисел записанных в выходной файл: ");
                System.out.println("Кол-вол: " + doubles.size());
                System.out.println("Минимальное значение: " + Statistics.minValueDouble(doubles));
                System.out.println("Максимальное значение: " + Statistics.maxValueDouble(doubles));
                System.out.println("Сумма чисел: " + Statistics.sumDouble(doubles));
                System.out.println("Среднее значение: " + Statistics.AverageDouble(doubles));
                System.out.println(" ");
            } catch (DifferentMyException e) {
                System.out.println(e.getMessage());
            }

            try {
                Statistics.checkNull(strings);
                System.out.println("Статистика для строк записанных в выходной файл: ");
                System.out.println("Кол-вол: " + strings.size());
                System.out.println("Размер самой короткой строки: " + Statistics.sizeOfShortString(strings));
                System.out.println("Размер самой длинной строки: " + Statistics.sizeOfLengthString(strings));
            } catch (DifferentMyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}