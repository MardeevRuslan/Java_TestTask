package cft.mardeev.services;

import cft.mardeev.domain.Arguments;

import java.util.Map;
import java.util.function.Supplier;

public interface ServicesFiles {
    void work(Arguments arguments);

    Map<String, String> getOutputFiles();
}
