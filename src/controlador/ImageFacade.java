package controlador;

import javax.swing.*;
import java.awt.*;

public class ImageFacade {
    public static Image load(String path) {
        try {
            return new ImageIcon(ImageFacade.class.getResource(path)).getImage();
        } catch (Exception e) {
            System.err.println("Imagen no encontrada: " + path);
            return new ImageIcon().getImage();
        }
    }

    public static Image[] loadFrames(String basePath, int count, String suffix) {
        Image[] frames = new Image[count];
        for (int i = 0; i < count; i++) {
            frames[i] = load(basePath + (i+1) + suffix);
        }
        return frames;
    }
}
