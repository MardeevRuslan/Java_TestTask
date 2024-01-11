package cft.mardeev.files;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterFiles<T> {
    private List<T> result;

    public WriterFiles(List<T> list) {
        this.result = list;
    }
    public void write(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (T value : result) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        }
    }
}
