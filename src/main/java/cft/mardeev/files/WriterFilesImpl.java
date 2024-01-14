package cft.mardeev.files;


import cft.mardeev.data.Result;
import cft.mardeev.utils.Literals;
import cft.mardeev.utils.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class WriterFilesImpl<T> implements WriterFiles {

    private final CreatorFiles creatorFiles;
    private final Result result;
    private Map<String, String> options;
    private Logger logger;


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
            logger.info(Literals.FILE_NOT_WRITE);
        }
    }

    private void writeInt(Map<String, String> outputFiles) throws IOException {
        List<T> listResult = (List<T>) result.getResultInt();
        String filePath = outputFiles.get(Type.INTEGER_TYPE);
        writeFile(filePath, listResult);
        result.getResultInt().clear();
    }

    private void writeString(Map<String, String> outputFiles) throws IOException {
        List<T> listResult = (List<T>) result.getResultString();
        String filePath = outputFiles.get(Type.STRING_TYPE);
        writeFile(filePath, listResult);
        result.getResultString().clear();
    }

    private void writeFloat(Map<String, String> outputFiles) throws IOException {
        List<T> listResult = (List<T>) result.getResultDouble();
        String filePath = outputFiles.get(Type.FLOAT_TYPE);
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
