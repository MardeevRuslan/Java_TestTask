package cft.mardeev.parser;

import cft.mardeev.domain.Argument;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ParserFile {

    private Argument argument;

    public ParserFile(Argument argument) {
        this.argument = argument;
    }

    public void parser() {
        for (String file : argument.getFiles()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String str;
                System.out.println(file);
                while ((str = bufferedReader.readLine()) != null) {
                    Float.parseFloat(str);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден: " + file);
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + file);
            }
        }
    }
}
