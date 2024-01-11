package cft.mardeev.parser;

import cft.mardeev.domain.Argument;
import cft.mardeev.domain.Result;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ParserFile {

    private Argument argument;
    private Result result;

    public ParserFile(Argument argument, Result result) {
        this.argument = argument;
        this.result = result;
    }

    public void parser() {
        for (String file : argument.getFiles()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    writeResult(str);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден: " + file);
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + file);
            }
        }
    }

    private void writeResult(String str) {
        try {
            if (str.contains(".")) {
                Double d = Double.parseDouble(str);
                result.getResultDouble().add(d);
            } else {
                Integer i = Integer.parseInt(str);
                result.getResultInt().add(i);
            }
        } catch (NumberFormatException e) {
            result.getResultString().add(str);
        }
    }
}
