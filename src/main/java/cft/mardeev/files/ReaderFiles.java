package cft.mardeev.files;

import java.util.List;
import java.util.function.Supplier;

public interface ReaderFiles  {
    void inputFiles(List<String> files);

    public List<String> get();
}
