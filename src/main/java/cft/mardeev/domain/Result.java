package cft.mardeev.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private List<Long> resultInt;
    private List<String> resultString;
    private List<Double> resultDouble;
    private Map<String, File> files;
;

    public Result() {
        this.resultDouble = new ArrayList<>();
        this.resultInt = new ArrayList<>();
        this.resultString = new ArrayList<>();
        this.files = new HashMap<>();
        files.put("string", null);
        files.put("int", null);
        files.put("float", null);
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public List<Long> getResultInt() {
        return resultInt;
    }

    public List<String> getResultString() {
        return resultString;
    }

    public List<Double> getResultDouble() {
        return resultDouble;
    }

    public int getSize() {
        return resultDouble.size() + resultString.size() + resultInt.size();
    }

}
