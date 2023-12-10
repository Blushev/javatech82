package com.example.modules.directory;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;

import java.io.File;

@FileModuleComponent
public class DirectorySizeModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Calculate size of all files in the directory.";
    }

    @Override
    public void process(String filePath) {
        File directory = new File(filePath);
        long size = calculateDirectorySize(directory);

        System.out.println("Size of all files in the directory: " + size + " bytes");
    }

    private long calculateDirectorySize(File directory) {
        if (directory.isFile()) {
            return directory.length();
        }

        long size = 0;
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                size += calculateDirectorySize(file);
            }
        }

        return size;
    }
}