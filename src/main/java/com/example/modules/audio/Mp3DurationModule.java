package com.example.modules.audio;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;


@FileModuleComponent
public class Mp3DurationModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Display the duration of the MP3 track in seconds.";
    }

    @Override
    public void process(String filePath) {
        try {
            long durationInSeconds = extractDurationInSeconds(filePath);
            System.out.println("MP3 Track Duration: " + durationInSeconds + " seconds");
        } catch (IOException | UnsupportedTagException | InvalidDataException | NotSupportedException e) {
            System.err.println("Error displaying MP3 duration: " + e.getMessage());
        }
    }

    private long extractDurationInSeconds(String filePath) throws IOException, UnsupportedTagException, InvalidDataException, NotSupportedException {
        Mp3File mp3file = new Mp3File(filePath);
        long durationInMilliseconds = mp3file.getLengthInMilliseconds();
        return parseDurationToSeconds(durationInMilliseconds);
    }

    private long parseDurationToSeconds(long durationInMilliseconds) {
        return durationInMilliseconds / 1000;
    }
}