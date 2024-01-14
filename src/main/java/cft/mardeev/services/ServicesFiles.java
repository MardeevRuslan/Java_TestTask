package cft.mardeev.services;

import cft.mardeev.data.Arguments;

import java.util.Map;


public interface ServicesFiles {
    void work(Arguments arguments);

    Map<String, String> getOutputFiles();
}
