package com.example.modules.text;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@FileModuleComponent
public class CharacterFrequencyModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Character frequency in the text file.";
    }

    @Override
    public void process(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            Map<Character, Long> charFrequency = content.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            System.out.println("Character frequency in the file:");
            charFrequency.forEach((character, frequency) ->
                    System.out.println(character + ": " + frequency));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
