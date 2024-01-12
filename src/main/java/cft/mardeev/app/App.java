package cft.mardeev.app;


import java.io.IOException;


public class App 
{
    public static void main( String[] args )  {
        Application application = new ApplicationImpl();
        application.run(args);
    }
}
