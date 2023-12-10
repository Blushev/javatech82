package com.example.modules.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.repeat;

@FileModuleComponent
public class ExifInfoModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        return isImageFile(filePath);
    }

    @Override
    public String getDescription() {
        return "Display Exif information of the image.";
    }

    @Override
    public void process(String filePath) {
        try {
            File imageFile = new File(filePath);
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag);
                }
            }
        } catch (IOException | ImageProcessingException e) {
            System.err.println("Error reading Exif information: " + e.getMessage());
        }
    }

    private boolean isImageFile(String filePath) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};
        for (String extension : imageExtensions) {
            if (filePath.toLowerCase().endsWith("." + extension)) {
                return true;
            }
        }
        return false;
    }
}