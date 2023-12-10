package com.example.modules.directory;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@FileModuleComponent
public class LargeFilesCountModule implements FileModule {

    private static final long MIN_FILE_SIZE = 1024 * 1024; // Минимальный размер файла (1 МБ)

    @Override
    public boolean supports(String filePath) {
        return Files.isDirectory(Path.of(filePath));
    }

    @Override
    public String getDescription() {
        return "Count and display the number of files larger than 1 MB in the directory.";
    }

    @Override
    public void process(String directoryPath) {
        try {
            long largeFilesCount = Files.list(Path.of(directoryPath))
                    .filter(this::isLargeFile)
                    .count();
            System.out.println("Number of files larger than 1 MB in the directory: " + largeFilesCount);
        } catch (IOException e) {
            System.err.println("Error reading the directory: " + e.getMessage());
        }
    }

    private boolean isLargeFile(Path filePath) {
        try {
            return Files.size(filePath) > MIN_FILE_SIZE;
        } catch (IOException e) {
            System.err.println("Error checking file size: " + e.getMessage());
            return false;
        }
    }
}