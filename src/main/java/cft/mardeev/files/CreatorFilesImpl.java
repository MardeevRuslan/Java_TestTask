package cft.mardeev.files;


import cft.mardeev.domain.Result;
import cft.mardeev.utils.Literals;
import cft.mardeev.utils.Option;
import cft.mardeev.utils.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class CreatorFilesImpl implements CreatorFiles {

    private final Map<String, String> createsFiles = new HashMap<>();
    private Result result;
    private Logger logger;


    @Override
    public Map<String, String> getCreatesFiles() {
        return createsFiles;
    }

    @Override
    public Map<String, String> createFiles(Map<String, String> options, Result result) {
        this.result = result;
        create(options);
        return this.createsFiles;
    }


    private void create(Map<String, String> options) {
        String prePath = options.get(Option.OPTION_O);
        String currentPath = System.getProperty(Literals.USER_DIR);
        String path = prePath != null ? prePath : currentPath;
        try {
            createFiles(path, options);
        } catch (IOException e) {
            logger.info(Literals.NOT_CREATE_FILE + path);
            if (!path.equals(currentPath)) {
                logger.info(Literals.CREATE_FILE + currentPath);
                try {
                    createFiles(currentPath, options);
                } catch (IOException ex) {
                    logger.info(Literals.NOT_CREATE_FILE + currentPath);
                }
            }
        }
    }


    private void createFiles(String path, Map<String, String> options) throws IOException {
        String prefix = options.get(Option.OPTION_P);
        boolean isAppend = (options.get(Option.OPTION_A) != null);
        if (!result.getResultString().isEmpty() && createsFiles.get(Type.STRING_TYPE) == null) {
            String pathName = path + Literals.SLASH + prefix + Type.STRING_TYPE;
            createFile(pathName, Type.STRING_TYPE, isAppend);
        }
        if (!result.getResultInt().isEmpty() && createsFiles.get(Type.INTEGER_TYPE) == null) {
            String pathName = path + Literals.SLASH + prefix + Type.INTEGER_TYPE;
            createFile(pathName, Type.INTEGER_TYPE, isAppend);
        }
        if (!result.getResultDouble().isEmpty() && createsFiles.get(Type.FLOAT_TYPE) == null) {
            String pathName = path + Literals.SLASH + prefix + Type.FLOAT_TYPE;
            createFile(pathName, Type.FLOAT_TYPE, isAppend);
        }
    }

    private void createFile(String name, String key, boolean isAppend) throws IOException {
        File file = new File(name);
        if (!file.exists()) {
            file.createNewFile();
        } else if (!isAppend) {
            file.delete();
            file.createNewFile();
        }
        createsFiles.put(key, file.getPath());
    }
}
