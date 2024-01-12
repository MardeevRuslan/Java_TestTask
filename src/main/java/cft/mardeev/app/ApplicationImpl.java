package cft.mardeev.app;

import cft.mardeev.domain.Arguments;
import cft.mardeev.parser.ParserArgs;
import cft.mardeev.services.ServicesFiles;
import cft.mardeev.statistic.Statistic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationImpl implements Application {

    private Arguments arguments;
    private ParserArgs parserArgs;
    private ServicesFiles servicesFiles;
    private Statistic statistic;


    @Override
    public void run(String[] args) {
        arguments = parserArgs.parse(args);
        servicesFiles.work(arguments);
        statistic.printStatistic(arguments.getOption(), servicesFiles.getOutputFiles());
    }
}
