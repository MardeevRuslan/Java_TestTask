package cft.mardeev.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Argument {
    private Map<Character, String> options ;
    private List<String> files ;

    public Argument() {
        this.options = new HashMap<>();
        this.files = new ArrayList<>();
    }

    public Map<Character, String> getOptions() {
        return options;
    }

    public void setOptions(Map<Character, String> options) {
        this.options = options;
    }


    public List<String> getFiles() {
        return files;
    }
}
