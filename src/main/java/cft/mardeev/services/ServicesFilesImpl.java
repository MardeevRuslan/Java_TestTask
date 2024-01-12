package cft.mardeev.services;


import cft.mardeev.domain.Arguments;
import cft.mardeev.files.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;


public class ServicesFilesImpl implements ServicesFiles {


    private ReaderFiles readerFiles;
    private WriterFiles writerFiles;
    private CreatorFiles creatorFiles;

    private boolean shouldStop = false;


    public ServicesFilesImpl() {
        this.readerFiles = new ReaderFilesImpl();
        this.creatorFiles = new CreatorFilesImpl();
        this.writerFiles = new WriterFilesImpl<>(creatorFiles);

    }


    @Override
    public void work(Arguments arguments) {
        readerFiles.inputFiles(arguments.getFiles());
        writerFiles.inputOption(arguments.getOption());
        while (shouldStop == false) {
            CompletableFuture.supplyAsync(producer)
                    .thenAcceptAsync(consumer);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Supplier<List<String>> producer = () -> {
        List<String > stringList =  readerFiles.get();
        return stringList;
    };

    private Consumer<List<String>> consumer = i -> {
        if(i.isEmpty()) {
            shouldStop = true;
        } else {
            writerFiles.accept(i);
        }
    };

//    public void work(Arguments arguments) {
//        readerFiles.inputFiles(arguments.getFiles());
//        writerFiles.inputOption(arguments.getOption());
//        while (shouldStop == false) {
//            CompletableFuture.supplyAsync(producer)
//                    .thenAcceptAsync(consumer);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}


