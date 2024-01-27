package com.leohabrom.java.game1.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static BufferedImage image;
    public static void main(String[] args) {
        JFrame window = new JFrame("Dickey");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        GamePanel gamePanel = new GamePanel();
        getImage();
        window.setIconImage(image);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }
    public static void getImage() {
        try {
            image = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/gui/icon.png")));
        } catch (IOException e) {
            Logger.getGlobal().log(Level.INFO, e.getMessage());
        }
    }
}