package cft.mardeev.data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Result {
    private final List<Long> resultInt  = new ArrayList<>();
    private final List<String> resultString = new ArrayList<>();
    private final List<Double> resultDouble = new ArrayList<>();

}
