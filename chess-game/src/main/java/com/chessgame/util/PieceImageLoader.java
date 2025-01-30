package com.chessgame.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PieceImageLoader {
    private static final Map<String, ImageIcon> imageCache = new HashMap<>();

    // Change this to match your actual resource directory structure
    private static final String BASE_PATH = "/pieces/";  // Assuming pieces are in a 'pieces' directory under resources

    private final static String[] PIECE_TYPES = {"P", "R", "N", "B", "Q", "K"};
    private final static String[] COLOURS = {"w", "b"};

    static {
        if (!loadAllPieces()) {
            throw new RuntimeException("Failed to load one or more chess pieces. Please check resource paths.");
        }
    }

    private static boolean loadAllPieces() {
        boolean allPiecesLoaded = true;

        for (String color : COLOURS) {
            for (String pieceType : PIECE_TYPES) {
                String key = color + pieceType;
                String path = BASE_PATH + key + ".png";

                URL resourceUrl = PieceImageLoader.class.getResource(path);
                if (resourceUrl == null) {
                    System.err.println("ERROR: Could not find resource: " + path);
                    allPiecesLoaded = false;
                    continue;
                }

                try {
                    ImageIcon icon = new ImageIcon(resourceUrl);
                    // Validate the image was actually loaded
                    if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                        throw new IOException("Failed to load image completely: " + path);
                    }
                    imageCache.put(key, icon);
                } catch (Exception e) {
                    System.err.println("ERROR: Failed to load piece image: " + path);
                    e.printStackTrace();
                    allPiecesLoaded = false;
                }
            }
        }

        return allPiecesLoaded;
    }

    public static ImageIcon getPieceImage(String color, String pieceType) {
        String key = color.toLowerCase() + pieceType.toUpperCase();
        ImageIcon icon = imageCache.get(key);
        if (icon == null) {
            throw new IllegalStateException("No image found for piece: " + key);
        }
        return icon;
    }

    public static ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        if (icon == null) {
            throw new IllegalArgumentException("Cannot resize null image icon");
        }
        Image ogImg = icon.getImage();
        Image resizedImg = ogImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}