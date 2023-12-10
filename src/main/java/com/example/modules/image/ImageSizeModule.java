package com.example.modules.image;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@FileModuleComponent
public class ImageSizeModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        return isImageFile(filePath);
    }

    @Override
    public String getDescription() {
        return "Display the size of the image.";
    }

    @Override
    public void process(String filePath) {
        try {
            File imageFile = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            System.out.println("Image size: " + width + " x " + height);
        } catch (IOException e) {
            System.err.println("Error reading the image file: " + e.getMessage());
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