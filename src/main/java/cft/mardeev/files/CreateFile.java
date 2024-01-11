package cft.mardeev.files;

import cft.mardeev.domain.Argument;
import cft.mardeev.domain.Result;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CreateFile {
    private Argument argument;
    private Result result;

    public CreateFile(Argument argument, Result result) {
        this.argument = argument;
        this.result = result;
    }

    public void create() {
        String prePath = argument.getOptions().get('o');
        String currentPath = System.getProperty("user.dir");
        String path = prePath != null ? prePath : currentPath;
        try {
            createFiles(path);
        } catch (IOException e) {
            System.out.println("Нельзя создать  файлы в директории: " + path);
            if (!path.equals(currentPath)) {
                System.out.println("Файлы будут созданы в корневой директории проекта: " + currentPath);
                try {
                    createFiles(currentPath);
                } catch (IOException ex) {
                    System.out.println("Нельзя создать  файлы в директории: " + currentPath);
                }
            }
        }
    }

    private void createFiles(String path) throws IOException {
        String prefix = argument.getOptions().get('p');
        if (!result.getResultString().isEmpty()) {
            String pathName = path + '/' + prefix + "strings.txt";
            createFile(pathName, result.getResultString());
        }
        if (!result.getResultInt().isEmpty()) {
            String pathName = path + '/' + prefix + "integers.txt";
            createFile(pathName, result.getResultInt());
        }
        if (!result.getResultDouble().isEmpty()) {
            String pathName = path + '/' + prefix + "floats.txt";
            createFile(pathName, result.getResultDouble());
        }
    }

    private void createFile(String name, List list) throws IOException {
        File file = new File(name);
        if (!file.exists() || (file.exists() && argument.getOptions().get('a') == null)) {
            file.delete();
            file.createNewFile();
        }
        WriterFiles writerFiles = new WriterFiles<>(list);
        writerFiles.write(file.getPath());
    }
}
