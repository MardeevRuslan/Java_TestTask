package cft.mardeev.config;


import cft.mardeev.domain.Arguments;
import cft.mardeev.domain.Result;
import cft.mardeev.statistic.Statistic;
import cft.mardeev.statistic.StatisticImpl;
import cft.mardeev.utils.Literals;
import org.springframework.context.annotation.*;

import java.util.logging.Logger;

@Configuration
@ComponentScan(basePackages = {Literals.PACKAGES})
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
    @Scope("prototype")
    public Logger logger () {
        return  Logger.getLogger(getClass().getName());
    }

    @Bean
    public boolean shouldStop() {
        return false;
    }

}
