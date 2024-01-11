package cft.mardeev.app;

import cft.mardeev.domain.Argument;
import cft.mardeev.domain.Result;
import cft.mardeev.files.CreateFile;
import cft.mardeev.files.WriterFiles;
import cft.mardeev.parser.ParserArgs;
import cft.mardeev.parser.ParserFile;

import java.io.IOException;


public class App 
{
    public static void main( String[] args ) throws IOException {
        Argument argument = new Argument();
        Result result = new Result();
        ParserArgs parserArgs = new ParserArgs(argument);
        parserArgs.parser(args);
        ParserFile parserFile = new ParserFile(argument, result);
        parserFile.parser();
        CreateFile createFile = new CreateFile(argument, result);
        createFile.create();
    }
}
