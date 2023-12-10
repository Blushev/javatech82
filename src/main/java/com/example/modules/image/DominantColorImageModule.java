package com.example.modules.image;

import com.example.modules.FileModule;
import com.example.modules.FileModuleComponent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@FileModuleComponent
public class DominantColorImageModule implements FileModule {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().matches(".+\\.(jpg|jpeg|png|gif|bmp)$");
    }

    @Override
    public String getDescription() {
        return "Display information about the dominant color on the image.";
    }

    @Override
    public void process(String filePath) {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));

            Map<Integer, Integer> colorCount = countColors(image);
            int dominantColor = findDominantColor(colorCount);

            System.out.println("Dominant color on the image (RGB): " + getRGBString(dominantColor));
        } catch (IOException e) {
            System.err.println("Error reading the image: " + e.getMessage());
        }
    }

    private Map<Integer, Integer> countColors(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        Map<Integer, Integer> colorCount = new HashMap<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                colorCount.put(rgb, colorCount.getOrDefault(rgb, 0) + 1);
            }
        }

        return colorCount;
    }

    private int findDominantColor(Map<Integer, Integer> colorCount) {
        return colorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0);
    }

    private String getRGBString(int rgb) {
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        return red + ", " + green + ", " + blue;
    }
}