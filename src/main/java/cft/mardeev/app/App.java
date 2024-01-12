package cft.mardeev.app;

import cft.mardeev.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )  {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Application application = context.getBean(ApplicationImpl.class);
        application.run(args);
    }
}
