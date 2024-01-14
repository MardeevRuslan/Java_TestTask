package cft.mardeev.filteringutility;

import cft.mardeev.data.Arguments;
import cft.mardeev.parser.ParserArgs;
import cft.mardeev.services.ServicesFiles;
import cft.mardeev.statistic.Statistic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilterFilesUtilityImpl implements FilterFilesUtility {

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
