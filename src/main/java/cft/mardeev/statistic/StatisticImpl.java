package cft.mardeev.statistic;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class StatisticImpl implements Statistic {


    @Override
    public void printStatistic(Map<String, String> option, Map<String, String> outputFiles) {
        if (option.get("-f") != null) {
            printShortStatistic(outputFiles);
            printFullStatistic(outputFiles);
        } else if (option.get("-s") != null) {
            printShortStatistic(outputFiles);
        }
    }

    private void printFullStatistic(Map<String, String> outputFiles) {
        printFullStatisticFloat(outputFiles.get("floats.txt"));
        printFullStatisticInteger(outputFiles.get("integers.txt"));
        printFullStatisticString(outputFiles.get("strings.txt"));
    }

    private void printShortStatistic(Map<String, String> outputFiles) {
        for (String type : outputFiles.keySet()) {
            if (outputFiles.get(type) != null) {
                System.out.println("В файл " + outputFiles.get(type) +
                        " записано " + getCountLine(outputFiles.get(type)) + " значения(ий)");
            }
        }
    }

    private int getCountLine(String path) {
        int count = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while (bufferedReader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Ошибка в статистике");
            e.printStackTrace();
        }
        return count;
    }

    private void printFullStatisticFloat(String path) {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        double sum = 0;
        int count = 0;
        double coverage = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                max = Math.max(max, Double.parseDouble(str));
                min = Math.min(min, Double.parseDouble(str));
                count++;
                sum += Double.parseDouble(str);
            }
            coverage = sum / count;
        } catch (IOException e) {
            System.out.println("Ошибка в статистике");
            e.printStackTrace();
        }
        System.out.println("Для чисел типа " + "float");
        System.out.println("   Максимум " + max);
        System.out.println("   Минимум " + min);
        System.out.println("   Сумма " + sum);
        System.out.println("   Среднее " + coverage);
    }


    private void printFullStatisticInteger(String path) {
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        long sum = 0;
        int count = 0;
        long coverage = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                max = Math.max(max, Long.parseLong(str));
                min = Math.min(min, Long.parseLong(str));
                count++;
                sum += Long.parseLong(str);
            }
            coverage = sum / count;
        } catch (IOException e) {
            System.out.println("Ошибка в статистике");
            e.printStackTrace();
        }
        System.out.println("Для чисел типа " + "integer");
        System.out.println("   Максимум " + max);
        System.out.println("   Минимум " + min);
        System.out.println("   Сумма " + sum);
        System.out.println("   Среднее " + coverage);
    }


    private void printFullStatisticString(String path) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                max = Math.max(max, str.length());
                min = Math.min(min, str.length());
            }
        } catch (IOException e) {
            System.out.println("Ошибка в статистике");
            e.printStackTrace();
        }
        System.out.println("Самая длинная строка " +
                max);
        System.out.println("Самая короткая строка " +
                min);
    }
}
