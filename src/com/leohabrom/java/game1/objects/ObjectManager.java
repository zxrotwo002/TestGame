package com.leohabrom.java.game1.objects;

import com.leohabrom.java.game1.entity.Player;
import com.leohabrom.java.game1.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectManager {
    GamePanel gamePanel;
    Coin coin;
    int counter = 0;
    int animation = 0;
    int coinOffset = 0;
    public ObjectManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        coin = new Coin();
    }

    public void update() {
        int animationSpeed = 15;

        if (counter < animationSpeed) {
            animation = 0;
            coinOffset = 0;
        }
        else if (counter < 2 * animationSpeed) {
            animation = 1;
            coinOffset = 1;
        }
        else if (counter < 3 * animationSpeed) {
            animation = 2;
            coinOffset = 2;
        }
        else if (counter < 4 * animationSpeed) {
            animation = 3;
            coinOffset = 2;
        }
        else if (counter < 5 * animationSpeed) {
            animation = 4;
            coinOffset = 1;
        }
        else if (counter < 6 * animationSpeed) {
            animation = 5;
            coinOffset = 0;
        }
        else {
            counter = 0;
        }
        counter++;
    }
    public void draw(Graphics2D g2) {
        BufferedImage coinImage = switch (animation) {
            case 0 -> coin.image0;
            case 1 -> coin.image1;
            case 2 -> coin.image2;
            case 3 -> coin.image3;
            case 4 -> coin.image4;
            case 5 -> coin.image5;
            default -> null;
        };
        Player player = gamePanel.player;
        int worldX = gamePanel.tileSize * 20;
        int worldY = gamePanel.tileSize * 20;
        int screenX = worldX - player.worldX + player.screenX;
        int screenY = worldY - player.worldY + player.screenY - coinOffset * 2;
        g2.drawImage(coinImage,screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
