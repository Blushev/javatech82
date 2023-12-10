package com.example.modules.directory;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;

import java.io.File;

@FileModuleComponent
public class ListFilesModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "List files in the directory.";
    }

    @Override
    public void process(String filePath) {
        File directory = new File(filePath);
        File[] files = directory.listFiles();

        if (files != null) {
            System.out.println("Files in the directory:");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.err.println("Error reading the directory.");
        }
    }
}