package cft.mardeev.files;

import cft.mardeev.domain.Arguments;
import cft.mardeev.domain.Result;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CreatorFiles {
    Map<String, String> createFiles(Map<String, String> options, Result result);
}
