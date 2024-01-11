package cft.mardeev.statistic;

import cft.mardeev.domain.Argument;
import cft.mardeev.domain.Result;

import java.util.*;

public class Statistic<T extends Number> {
    private Argument argument;
    private Result result;

    public Statistic(Argument argument, Result result) {
        this.argument = argument;
        this.result = result;
    }

    public void printStatistic() {
        if (argument.getOptions().get('f') != null) {
            printShortStatistic();
            printFullStatistic();
        } else if (argument.getOptions().get('s') != null) {
            printShortStatistic();
        }
    }

    private void printFullStatistic() {
        printFullStatisticNumber("float", (List<T>) result.getResultDouble());
        printFullStatisticNumber("int", (List<T>) result.getResultInt());
        printFullStatisticString(result.getResultString());
    }

    private void printShortStatistic() {
        System.out.println("В файл " + result.getFiles().get("float").getPath() +
                " записано " + result.getResultDouble().size() + " значения(ий)");
        System.out.println("В файл " + result.getFiles().get("string").getPath() +
                " записано " + result.getResultString().size() + " значения(ий)");
        System.out.println("В файл " + result.getFiles().get("int").getPath() +
                " записано " + result.getResultInt().size() + " значения(ий)");
    }

    private void printFullStatisticNumber(String type, List<T> listResult) {
        System.out.println("Для чисел типа " + type);
        System.out.println("   Максимум " + Collections.max(listResult, Comparator.comparing(Number::doubleValue)));
        System.out.println("   Минимум " + Collections.min(listResult, Comparator.comparing(Number::doubleValue)));
        if (listResult.get(0) instanceof Long) {
            System.out.println("   Сумма " +
                    listResult.stream()
                            .mapToLong(Number::longValue)
                            .sum());
            System.out.println("   Среднее значение " + (long)
                    listResult.stream()
                            .mapToLong(Number::longValue)
                            .average().
                            orElse(0));
        } else {
            System.out.println("   Сумма " +
                    listResult.stream()
                            .mapToDouble(Number::doubleValue)
                            .sum());
            System.out.println("   Среднее значение " +
                    listResult.stream()
                            .mapToDouble(Number::doubleValue)
                            .average().
                            orElse(0));
        }
    }

    private void printFullStatisticString(List<String> listResult) {
        System.out.println("Самая длинная строка " +
                listResult.stream()
                        .mapToInt(String::length)
                        .max()
                        .orElse(0) +
                " символа(ов)");
        System.out.println("Самая короткая строка " +
                listResult.stream()
                        .mapToInt(String::length)
                        .min()
                        .orElse(0) +
                " символа(ов)");
    }
}
