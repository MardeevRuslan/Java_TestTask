package cft.mardeev.services;


import cft.mardeev.domain.Arguments;
import cft.mardeev.files.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class ServicesFilesImpl implements ServicesFiles {
    private final int SLEEP_THREAD = 5;
    private ReaderFiles readerFiles;
    private WriterFiles writerFiles;
    private  CreatorFiles creatorFiles;
    private boolean shouldStop;


    @Override
    public void work(Arguments arguments) {
        readerFiles.inputFiles(arguments.getFiles());
        writerFiles.inputOption(arguments.getOption());
        while (!shouldStop) {
            CompletableFuture.supplyAsync(() -> readerFiles.get())
                    .thenAcceptAsync(i -> {
                        if(i.isEmpty()) {
                            shouldStop = true;
                        } else {
                            writerFiles.accept(i);
                        }
                    });
            try {
                Thread.sleep(SLEEP_THREAD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public Map<String, String> getOutputFiles() {
        return creatorFiles.getCreatesFiles();
    }


}


