package com.example.modules.text;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@FileModuleComponent
public class WordFrequencyModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Word frequency analysis in the text file.";
    }

    @Override
    public void process(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
            String[] words = content.split("\\s+");

            Map<String, Long> wordFrequency = Arrays.stream(words)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            System.out.println("Word frequency analysis in the file:");
            wordFrequency.forEach((word, frequency) ->
                    System.out.println(word + ": " + frequency));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}