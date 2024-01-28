package com.leohabrom.java.game1.objects;

import com.leohabrom.java.game1.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Coin extends Object{
    public Coin(GamePanel gamePanel) {
        collectable = true;
        hitBox = new Rectangle();
        hitBox.x = gamePanel.tileSize / 8;
        hitBox.y = gamePanel.tileSize / 8;
        hitBox.width = hitBox.x + gamePanel.tileSize/8 * 6;
        hitBox.height = hitBox.y + gamePanel.tileSize/8 * 6;

        try {
            image0 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/coin_0.png")));
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/coin_1.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/coin_2.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/coin_3.png")));
            image4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/coin_4.png")));
            image5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/coin_5.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
