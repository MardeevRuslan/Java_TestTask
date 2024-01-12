package cft.mardeev.files;


import cft.mardeev.domain.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class CreatorFilesImpl implements CreatorFiles {

    private final Map<String, String> createsFiles =  new HashMap<>();;
    private Result result;


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
        String prePath = options.get("-o");
        String currentPath = System.getProperty("user.dir");
        String path = prePath != null ? prePath : currentPath;
        try {
            createFiles(path, options);
        } catch (IOException e) {
            System.out.println("Нельзя создать  файлы в директории: " + path);
            if (!path.equals(currentPath)) {
                System.out.println("Файлы будут созданы в корневой директории проекта: " + currentPath);
                try {
                    createFiles(currentPath, options);
                } catch (IOException ex) {
                    System.out.println("Нельзя создать  файлы в директории: " + currentPath);
                }
            }
        }
    }


    private void createFiles(String path, Map<String, String> options) throws IOException {
        String prefix = options.get("-p");
        boolean isAppend = (options.get("-a") != null);
        if (!result.getResultString().isEmpty() && createsFiles.get("strings.txt") == null) {
            String pathName = path + '/' + prefix + "strings.txt";
            createFile(pathName, "strings.txt", isAppend);
        }
        if (!result.getResultInt().isEmpty() && createsFiles.get("integers.txt") == null) {
            String pathName = path + '/' + prefix + "integers.txt";
            createFile(pathName, "integers.txt", isAppend);
        }
        if (!result.getResultDouble().isEmpty() && createsFiles.get("floats.txt") == null) {
            String pathName = path + '/' + prefix + "floats.txt";
            createFile(pathName, "floats.txt", isAppend);
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
