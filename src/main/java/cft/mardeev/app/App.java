package cft.mardeev.app;

import cft.mardeev.domain.Argument;
import cft.mardeev.parser.ParserArgs;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Argument argument = new Argument();
        ParserArgs parserArgs = new ParserArgs(argument);
        parserArgs.parser(args);
        System.out.println(argument.getFiles());

    }
}
