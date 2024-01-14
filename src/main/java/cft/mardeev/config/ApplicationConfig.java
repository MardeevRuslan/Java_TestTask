package cft.mardeev.config;


import cft.mardeev.data.Arguments;
import cft.mardeev.data.Result;
import cft.mardeev.statistic.Statistic;
import cft.mardeev.statistic.StatisticImpl;
import cft.mardeev.utils.Literals;
import org.springframework.context.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@ComponentScan(basePackages = {Literals.PACKAGES})
public class ApplicationConfig {

    @Bean
    public Arguments arguments() {
       return new Arguments();
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
    @Bean
    public Level warnLevel() {
        return  Level.WARNING;
    }

}
