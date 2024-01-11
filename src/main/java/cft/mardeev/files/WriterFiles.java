package cft.mardeev.files;


import cft.mardeev.domain.Argument;
import cft.mardeev.domain.Result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class WriterFiles<T> {
    private Result result;
    private Argument argument;

    public WriterFiles(Result result, Argument argument) {
        this.result = result;
        this.argument = argument;
    }

    public void write()  {
        try {
            writeInt();
            writeFloat();
            writeString();
        } catch (IOException e) {
            System.out.println("Запись в файл не удалась");
        }
    }

    private void writeInt() throws IOException {
        List<T> listResult = (List<T>) result.getResultInt();
        String filePath = result.getFiles().get("int").getPath();
        writeFile(filePath, listResult);
    }

    private void writeString() throws IOException {
        List<T> listResult = (List<T>) result.getResultString();
        String filePath = result.getFiles().get("string").getPath();
        writeFile(filePath, listResult);
    }

    private void writeFloat() throws IOException {
        List<T> listResult = (List<T>) result.getResultDouble();
        String filePath = result.getFiles().get("float").getPath();
        writeFile(filePath, listResult);
    }


    private void writeFile(String filePath, List<T> resultList) throws IOException {
        boolean isAppend = (argument.getOptions().get('a') != null);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, isAppend))) {
            for (T value : resultList) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        }
    }
}
