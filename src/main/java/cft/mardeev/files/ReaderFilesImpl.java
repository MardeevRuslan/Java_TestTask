package cft.mardeev.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderFilesImpl implements ReaderFiles {

    List<BufferedReader> bufferedReaderList;

    public ReaderFilesImpl() {
        this.bufferedReaderList = new ArrayList<>();
    }

    @Override
    public void inputFiles(List<String> files) {
        completionBufferReader(files);
    }

    @Override
    public List<String> get() {
        return rider();
    }

    private void completionBufferReader(List<String> files) {
        for (String file : files) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                bufferedReaderList.add(bufferedReader);
            } catch (IOException e) {
                System.out.println("Файл не найден: " + file);
            }
        }
    }

    public List<String> rider() {
        List<String> stringList = new ArrayList<>();
        int size = bufferedReaderList.size();
        for (int i = 0; i < size; i++) {
            BufferedReader bufferedReader = bufferedReaderList.get(i);
            try {
                String string = bufferedReader.readLine();
                if (string == null) {
                    bufferedReader.close();
                    bufferedReaderList.remove(i);
                } else {
                    stringList.add(string);
                }
            } catch (IOException e) {
                System.out.println("Не возможно прочитать файл ");
            }
        }
        return stringList;
    }
}