package cft.mardeev.main;

import cft.mardeev.config.ApplicationConfig;
import cft.mardeev.filteringutility.FilterFilesUtility;
import cft.mardeev.filteringutility.FilterFilesUtilityImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        FilterFilesUtility filterFilesUtility = context.getBean(FilterFilesUtilityImpl.class);
        filterFilesUtility.run(args);
    }
}
