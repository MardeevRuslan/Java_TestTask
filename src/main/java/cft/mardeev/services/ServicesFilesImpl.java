package cft.mardeev.services;


import cft.mardeev.domain.Arguments;
import cft.mardeev.files.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ServicesFilesImpl implements ServicesFiles {
    private final int SLEEP_THREAD = 5;
    @Autowired
    private ReaderFiles readerFiles;
    @Autowired
    private WriterFiles writerFiles;
    @Autowired
    private  CreatorFiles creatorFiles;
    private boolean shouldStop = false;


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

    @Override
    public Map<String, String> getOutputFiles() {
        return creatorFiles.getCreatesFiles();
    }


}


