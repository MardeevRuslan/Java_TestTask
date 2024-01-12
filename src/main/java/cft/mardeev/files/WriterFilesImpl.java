package cft.mardeev.files;


import cft.mardeev.domain.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class WriterFilesImpl<T> implements WriterFiles {

    private final CreatorFiles creatorFiles;
    private final Result result;
    private Map<String, String> options;


    @Override
    public void accept(List<String> readerResult) {
        writeResult(readerResult);
        write();
    }


    @Override
    public void inputOption(Map<String, String> options) {
        this.options = options;
    }


    public void writeResult(List<String> readerResult) {
        for (String str : readerResult) {
            try {
                if (str.contains(".")) {
                    Double d = Double.parseDouble(str);
                    result.getResultDouble().add(d);
                } else {
                    Long i = Long.parseLong(str);
                    result.getResultInt().add(i);
                }
            } catch (NumberFormatException e) {
                result.getResultString().add(str);
            }
        }
    }

    public void write() {
        Map<String, String> outputFiles = creatorFiles.createFiles(options, result);
        try {
            writeInt(outputFiles);
            writeFloat(outputFiles);
            writeString(outputFiles);
        } catch (IOException e) {
            System.out.println("Запись в файл не удалась");
        }
    }

    private void writeInt(Map<String, String> outputFiles) throws IOException {
        List<T> listResult = (List<T>) result.getResultInt();
        String filePath = outputFiles.get("integers.txt");
        writeFile(filePath, listResult);
        result.getResultInt().clear();
    }

    private void writeString(Map<String, String> outputFiles) throws IOException {
        List<T> listResult = (List<T>) result.getResultString();
        String filePath = outputFiles.get("strings.txt");
        writeFile(filePath, listResult);
        result.getResultString().clear();
    }

    private void writeFloat(Map<String, String> outputFiles) throws IOException {
        List<T> listResult = (List<T>) result.getResultDouble();
        String filePath = outputFiles.get("floats.txt");
        writeFile(filePath, listResult);
        result.getResultDouble().clear();
    }


    private void writeFile(String filePath, List<T> resultList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (T value : resultList) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        }
    }
}
