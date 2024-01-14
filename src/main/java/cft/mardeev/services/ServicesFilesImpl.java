package cft.mardeev.services;


import cft.mardeev.data.Arguments;
import cft.mardeev.files.*;
import cft.mardeev.utils.Value;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class ServicesFilesImpl implements ServicesFiles {

    private ReaderFiles readerFiles;
    private WriterFiles writerFiles;
    private  CreatorFiles creatorFiles;
    private boolean shouldStop;
    private Logger logger;
    private Level warnLevel;


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
                Thread.sleep(Value.SLEEP_THREAD);
            } catch (InterruptedException e) {
                logger.log(warnLevel, "Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        }
    }



    @Override
    public Map<String, String> getOutputFiles() {
        return creatorFiles.getCreatesFiles();
    }


}


