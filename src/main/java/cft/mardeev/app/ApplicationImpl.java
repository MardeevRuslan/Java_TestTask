package cft.mardeev.app;

import cft.mardeev.domain.Arguments;
import cft.mardeev.parser.ParserArgs;
import cft.mardeev.parser.ParserArgsImpl;
import cft.mardeev.services.ServicesFiles;
import cft.mardeev.services.ServicesFilesImpl;
import cft.mardeev.statistic.StatisticImpl;
import cft.mardeev.statistic.Statistic;

public class ApplicationImpl implements Application{

    private Arguments arguments;
    private ParserArgs parserArgs;
    private ServicesFiles servicesFiles;
    private Statistic statistic;

    public ApplicationImpl() {
        parserArgs = new ParserArgsImpl();
        arguments = new Arguments();
        servicesFiles = new ServicesFilesImpl();
        statistic = new StatisticImpl<>();
    }
    @Override
    public void run(String [] args) {
        arguments = parserArgs.parse(args);
        servicesFiles.work(arguments);
        statistic.printStatistic(arguments.getOption(), servicesFiles.getOutputFiles());
    }
}
