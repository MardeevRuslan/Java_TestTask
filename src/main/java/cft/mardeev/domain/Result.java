package cft.mardeev.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<Integer> resultInt;
    private List<String> resultString;
    private List<Double> resultDouble;
;

    public Result() {
        this.resultDouble = new ArrayList<>();
        this.resultInt = new ArrayList<>();
        this.resultString = new ArrayList<>();

    }

    public List<Integer> getResultInt() {
        return resultInt;
    }

    public List<String> getResultString() {
        return resultString;
    }

    public List<Double> getResultDouble() {
        return resultDouble;
    }

}
