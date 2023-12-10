package com.example.modules.audio;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;
import com.mpatric.mp3agic.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

@FileModuleComponent
public class Mp3TagEditorModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Edit tags of the MP3 track.";
    }

    @Override
    public void process(String filePath) {
        try {
            Scanner scanner = new Scanner(System.in);

            Mp3File mp3file = new Mp3File(filePath);
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();

            System.out.print("Enter the new track title: ");
            String newTitle = scanner.nextLine();

            System.out.print("Enter the new artist: ");
            String newArtist = scanner.nextLine();

            System.out.print("Enter the new album: ");
            String newAlbum = scanner.nextLine();

            System.out.print("Enter the new year: ");
            String newYear = scanner.nextLine();

            // Логика редактирования тегов
            id3v2Tag.setTitle(newTitle);
            id3v2Tag.setArtist(newArtist);
            id3v2Tag.setAlbum(newAlbum);
            id3v2Tag.setYear(newYear);

            // Сохранение изменений
            mp3file.save(filePath + ".temp");
            Files.move(Paths.get(filePath + ".temp"), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("MP3 tags edited successfully.");
        } catch (IOException | UnsupportedTagException | InvalidDataException | NotSupportedException e) {
            System.err.println("Error editing MP3 tags: " + e.getMessage());
        }
    }
}