package cft.mardeev.files;

import cft.mardeev.data.Result;

import java.util.Map;

public interface CreatorFiles {
    Map<String, String> createFiles(Map<String, String> options, Result result);

    Map<String, String> getCreatesFiles();
}
