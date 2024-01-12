package cft.mardeev.config;


import cft.mardeev.domain.Arguments;
import cft.mardeev.domain.Result;
import cft.mardeev.files.*;
import cft.mardeev.parser.ParserArgs;
import cft.mardeev.parser.ParserArgsImpl;
import cft.mardeev.services.ServicesFiles;
import cft.mardeev.services.ServicesFilesImpl;
import cft.mardeev.statistic.Statistic;
import cft.mardeev.statistic.StatisticImpl;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"cft.mardeev/*"})
public class ApplicationConfig {

    @Bean
    public Arguments arguments() {
       return new Arguments();
    }

    @Bean
    public Statistic statistic() {
        return new StatisticImpl();
    }

    @Bean
    public Result result() {
        return new Result();
    }
    
    @Bean
    public ReaderFiles readerFiles() {
        return new ReaderFilesImpl();
    }


}
