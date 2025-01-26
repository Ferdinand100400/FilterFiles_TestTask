package org.example;

import java.util.List;

public class Statistics {


    public static long minValueLong(List<String> longList) throws DifferentMyException {
        checkNull(longList);
        long minValue = Long.parseLong(longList.get(0));
        for (int i = 0; i < longList.size(); i++) {
            if (Long.parseLong(longList.get(i)) < minValue) {
                minValue = Long.parseLong(longList.get(i));
            }
        }
        return minValue;
    }

    public static Double minValueDouble(List<String> doubleList) throws DifferentMyException {
        checkNull(doubleList);
        Double minValue = Double.parseDouble(doubleList.get(0));
        for (int i = 0; i < doubleList.size(); i++) {
            if (Double.parseDouble(doubleList.get(i)) < minValue) {
                minValue = Double.parseDouble(doubleList.get(i));
            }
        }
        return minValue;
    }

    public static Long maxValueLong(List<String> longList) throws DifferentMyException {
        checkNull(longList);
        Long maxValue = Long.parseLong(longList.get(0));
        for (int i = 0; i < longList.size(); i++) {
            if (Long.parseLong(longList.get(i)) > maxValue) {
                maxValue = Long.parseLong(longList.get(i));
            }
        }
        return maxValue;
    }

    public static Double maxValueDouble(List<String> doubleList) throws DifferentMyException {
        checkNull(doubleList);
        Double maxValue = Double.parseDouble(doubleList.get(0));
        for (int i = 0; i < doubleList.size(); i++) {
            if (Double.parseDouble(doubleList.get(i)) > maxValue) {
                maxValue = Double.parseDouble(doubleList.get(i));
            }
        }
        return maxValue;
    }

    public static Long sumLong(List<String> longList) throws DifferentMyException {
        checkNull(longList);
        Long sum = 0L;
        for (int i = 0; i < longList.size(); i++) {
            if (((Long.parseLong(longList.get(i)) >= 0) && ((sum + Long.parseLong(longList.get(i))) < sum)) ||
                    ((Long.parseLong(longList.get(i)) <= 0) && ((sum + Long.parseLong(longList.get(i))) > sum))) {
                throw new DifferentMyException("Сумму и среднее невозможно вывести, т.к. превышен диапазон максимального значения");
            }
            sum += Long.parseLong(longList.get(i));
        }
        return sum;
    }

    public static double sumDouble(List<String> doubleList) throws DifferentMyException {
        checkNull(doubleList);
        double sum = 0;
        for (int i = 0; i < doubleList.size(); i++) {
            sum += Double.parseDouble(doubleList.get(i));
        }
        return sum;
    }

    public static Long AverageLong(List<String> longList) throws DifferentMyException {
        checkNull(longList);
        return sumLong(longList) / longList.size();
    }

    public static Double AverageDouble(List<String> doubleList) throws DifferentMyException {
        checkNull(doubleList);
        return sumDouble(doubleList) / doubleList.size();
    }

    public static int sizeOfShortString(List<String> list) throws DifferentMyException {
        checkNull(list);
        int sizeStr = list.get(0).length();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() < sizeStr) {
                sizeStr = list.get(i).length();
            }
        }
        return sizeStr;
    }

    public static int sizeOfLengthString(List<String> list) throws DifferentMyException {
        checkNull(list);
        int sizeStr = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() > sizeStr) {
                sizeStr = list.get(i).length();
            }
        }
        return sizeStr;
    }
    public static void checkNull(List<String> list) throws DifferentMyException {
        if (list.size() == 0) {
            throw new DifferentMyException("");
        }
    }
}
