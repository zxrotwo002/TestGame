package com.leohabrom.java.game1.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainMenu {
    GamePanel gamePanel;
    Button startButton = new Button();
    int hoverStartButton = 0;
    public MainMenu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        loadButtons();
    }
    public void loadButtons() {
        startButton.x = gamePanel.tileSize * 4;
        startButton.y = gamePanel.tileSize * 2;
        startButton.width = gamePanel.tileSize * 8;
        startButton.height = gamePanel.tileSize * 3;
        startButton.rectangle = new Rectangle(startButton.x ,startButton.y,startButton.width,startButton.height);
        startButton.rectangle2 = new Rectangle(startButton.x ,startButton.y,startButton.width,startButton.height);

        try {
            startButton.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gui/start_button.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateRectangle() {
        startButton.rectangle2 = new Rectangle(startButton.x + gamePanel.window.getRootPane().getX() + gamePanel.window.getX(),
                startButton.y + gamePanel.window.getRootPane().getY() + gamePanel.window.getY(),startButton.width,startButton.height);
    }

    public void update() {
        if (startButton.rectangle2.contains(MouseInfo.getPointerInfo().getLocation())) {
            hoverStartButton = 5;
        }
        else {
            hoverStartButton = 0;
        }
        if (gamePanel.mouseHandler.clickPoint != null) {
            if (startButton.rectangle.contains(gamePanel.mouseHandler.clickPoint)) {
                gamePanel.mouseHandler.clickPoint = null;
                gamePanel.running = true;
                gamePanel.playMusic(0);
            }
        }
    }

    public void draw(Graphics2D g2) {
        int ts = gamePanel.tileSize;
        g2.drawRect(ts, ts, gamePanel.screenWidth - ts * 2, gamePanel.screenHeight - ts * 2);
        g2.drawImage(startButton.image, startButton.x, startButton.y + hoverStartButton, startButton.width, startButton.height, null);
    }
}
