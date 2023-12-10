package com.example.modules.audio;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@FileModuleComponent
public class Mp3TrackInfoModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Display the name of the MP3 track.";
    }

    @Override
    public void process(String filePath) {
        try {
            String trackName = extractTrackName(filePath);
            System.out.println("MP3 Track Name: " + trackName);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error processing the MP3 file: " + e.getMessage());
        }
    }

    private String extractTrackName(String filePath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("ffprobe", "-i", filePath, "-show_entries", "format_tags=title", "-v", "quiet", "-of", "csv=p=0");

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String trackName = reader.readLine();
            return trackName != null ? trackName : "Unknown Track Name";
        }
    }
}