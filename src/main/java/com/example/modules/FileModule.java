package com.example.modules;

public interface FileModule {
    boolean supports(String filePath);

    String getDescription();

    void process(String filePath);
}