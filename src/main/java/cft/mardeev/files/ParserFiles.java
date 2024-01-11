package cft.mardeev.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserFiles {

    private List<String> files;

    List<BufferedReader> bufferedReaderList;

    public ParserFiles(List<String> files) {
        this.files = files;
        this.bufferedReaderList = new ArrayList<>();
    }

    public List<String> parser() {
        List<String> stringList = new ArrayList<>();
        int size = bufferedReaderList.size();
        for (int i = 0; i < size; i++) {
            BufferedReader bufferedReader = bufferedReaderList.get(i);
            try {
                String string = bufferedReader.readLine();
                if (string == null) {
                    bufferedReader.close();
                    bufferedReaderList.remove(i);
                    size--;
                } else {
                    stringList.add(string);
                }
            } catch (IOException e) {
                System.out.println("Не возможно прочитать файл ");
            }
        }
        return stringList;
    }


    public void completionBufferReader() {
        for (String file : files) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                bufferedReaderList.add(bufferedReader);
            } catch (IOException e) {
                System.out.println("Файл не найден: " + file);
            }
        }
    }
}