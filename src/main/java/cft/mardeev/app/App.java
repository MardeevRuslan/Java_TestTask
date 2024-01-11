package cft.mardeev.app;

import cft.mardeev.domain.Argument;
import cft.mardeev.domain.Result;
import cft.mardeev.files.CreateFile;
import cft.mardeev.files.WriterFiles;
import cft.mardeev.parser.ParserArgs;
import cft.mardeev.files.ServicesFiles;
import cft.mardeev.statistic.Statistic;

import java.io.IOException;


public class App 
{
    public static void main( String[] args ) throws IOException {
        Argument argument = new Argument();
        Result result = new Result();
        ParserArgs parserArgs = new ParserArgs(argument);
        parserArgs.parser(args);
        ServicesFiles servicesFiles = new ServicesFiles(argument, result);
        servicesFiles.work();
    }
}
