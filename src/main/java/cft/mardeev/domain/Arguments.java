package cft.mardeev.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arguments {
    private Map<String, String> option;
    private List<String> files;

    public Arguments() {
        this.option = new HashMap<>();
        this.files = new ArrayList<>();
    }

    public Map<String, String> getOption() {
        return option;
    }

    public List<String> getFiles() {
        return files;
    }
}
