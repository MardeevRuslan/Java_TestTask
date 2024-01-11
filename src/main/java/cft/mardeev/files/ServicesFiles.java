package cft.mardeev.files;

import cft.mardeev.domain.Argument;
import cft.mardeev.domain.Result;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ServicesFiles {

    private Argument argument;
    private Result result;
    private ParserFiles parserFiles;
    private WriterFiles writerFiles;

    private CreateFile createFile;

    private final int MAX_SIZE_LINE = 1000;

    public ServicesFiles(Argument argument, Result result) {
        this.argument = argument;
        this.result = result;
        this.parserFiles = new ParserFiles(argument.getFiles());
        this.writerFiles = new WriterFiles<>(result, argument);
        this.createFile =new CreateFile(argument, result);
    }

    public void work() {
        parserFiles.completionBufferReader();
        boolean isEmptyFiles = false;
        while (isEmptyFiles == false) {
            List<String> resultParser = parserFiles.parser();
            isEmptyFiles = resultParser.isEmpty();
            writerFiles.writeResult(resultParser);
            if(result.getSize() >= MAX_SIZE_LINE || isEmptyFiles == true) {
                createFile.create();
                writerFiles.write();
            }
        }
    }
}
