package cft.mardeev.files;

import java.util.List;
import java.util.Map;


public interface WriterFiles {
    void inputOption(Map<String, String> options);

    public void accept(List<String> readerResult);
}
