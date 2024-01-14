package cft.mardeev;

import cft.mardeev.data.Arguments;
import cft.mardeev.parser.ParserArgsImpl;
import cft.mardeev.utils.Value;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ParserArgsImplTest {

    @Test
    public void testParse() {

        Arguments arguments = new Arguments();
        Logger logger = mock(Logger.class);
        ParserArgsImpl parserArgs = new ParserArgsImpl(arguments, logger);


        String[] args1 = new String[]{"-s", "file1.txt", "-o", "output.txt"};
        Arguments result1 = parserArgs.parse(args1);
        assertEquals("file1.txt", result1.getFiles().get(0));


        String[] args2 = new String[]{"file1.txt", "-f"};
        Arguments result2 = parserArgs.parse(args2);
        assertEquals("file1.txt", result2.getFiles().get(0));
        assertEquals(Value.MOCK_VALUE, result2.getOption().get("-f"));

    }

}
