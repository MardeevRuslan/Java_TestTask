package cft.mardeev.app;

import cft.mardeev.domain.Arguments;
import cft.mardeev.parser.ParserArgs;
import cft.mardeev.parser.ParserArgsImpl;
import cft.mardeev.services.ServicesFiles;
import cft.mardeev.services.ServicesFilesImpl;

import java.util.HashMap;
import java.util.Map;

public class ApplicationImpl implements Application{

    private Arguments arguments;
    private ParserArgs parserArgs;
    private ServicesFiles servicesFiles;

    public ApplicationImpl() {
        parserArgs = new ParserArgsImpl();
        arguments = new Arguments();
        servicesFiles = new ServicesFilesImpl();
    }
    @Override
    public void run(String [] args) {
        arguments = parserArgs.parse(args);
        servicesFiles.work(arguments);
    }
}
