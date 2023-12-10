package com.example.modules.text;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@FileModuleComponent
public class CountLinesModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Count and display the number of lines in the text file." ;
    }

    @Override
    public void process(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            System.out.println("Number of lines in the file: " + lines.size());
            System.out.println("Contents of the file:");
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}