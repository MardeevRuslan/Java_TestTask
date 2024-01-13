package cft.mardeev.files;

import cft.mardeev.utils.Literals;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class ReaderFilesImpl implements ReaderFiles {

    private  final List<BufferedReader> bufferedReaderList = new ArrayList<>();
    private Logger logger;

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
                logger.info(Literals.FILE_NOT + file);
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
                logger.info(Literals.FILE_NOT_READ);
            }
        }
        return stringList;
    }
}