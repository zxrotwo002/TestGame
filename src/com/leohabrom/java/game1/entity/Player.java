package com.leohabrom.java.game1.entity;

import com.leohabrom.java.game1.main.GamePanel;
import com.leohabrom.java.game1.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    BufferedImage imageDrops;
    boolean shootDrops = false;
    public Player (GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            imageN = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_n.png")));
            imageNE = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_ne.png")));
            imageE = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_e.png")));
            imageSE = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_se.png")));
            imageS = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_s.png")));
            imageSW = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_sw.png")));
            imageW = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_w.png")));
            imageNW = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_nw.png")));
            imageDrops = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/drops.png")));
        } catch (IOException e) {
            Logger.getGlobal().log(Level.INFO, e.getMessage());
        }
    }
    public void setDefaultValues() {
        x = gamePanel.tileSize * 2;
        y = gamePanel.tileSize * 2;
        speed = 1;
        direction = "S";
        hitBox = new Rectangle();
        hitBox.x = gamePanel.tileSize/8;
        hitBox.y = gamePanel.tileSize/2;
        hitBox.height = gamePanel.tileSize/2;
        hitBox.width = (gamePanel.tileSize/8) * 6;
    }
    public void update() {
        int speedMultiplier;
        if (keyHandler.ctrlPressed) {
            speedMultiplier = 2;
        }
        else {
            speedMultiplier = 1;
        }
        boolean w,a,s,d, space;
        w = keyHandler.wPressed;
        a = keyHandler.aPressed;
        s = keyHandler.sPressed;
        d = keyHandler.dPressed;
        space = keyHandler.spacePressed;


        shootDrops = space;

        if (w && !a && !s && !d) {
            direction = "N";
        }
        if (w && !a && !s && d) {
            direction = "NE";
        }
        if (!w && !a && !s && d) {
            direction = "E";
        }
        if (!w && !a && s && d) {
            direction = "SE";
        }
        if (!w && !a && s && !d) {
            direction = "S";
        }
        if (!w && a && s && !d) {
            direction = "SW";
        }
        if (!w && a && !s && !d) {
            direction = "W";
        }
        if (w && a && !s && !d) {
            direction = "NW";
        }

        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);

        if (collisionOn) {
            speedMultiplier = 0;
        }

        if (w && y >= 0) {
            y = y - speed * speedMultiplier;
        }
        if (a && x >= 0) {
            x = x - speed * speedMultiplier;
        }
        if (s && y <= gamePanel.screenHeight - gamePanel.tileSize) {
            y = y + speed * speedMultiplier;
        }
        if (d && x <= gamePanel.screenWidth - gamePanel.tileSize) {
            x = x + speed * speedMultiplier;
        }

    }
    public void draw(Graphics2D g2) {
        BufferedImage image2;
        BufferedImage image = switch (direction) {
            case "N" -> imageN;
            case "NE" -> imageNE;
            case "E" -> imageE;
            case "SE" -> imageSE;
            case "S" -> imageS;
            case "SW" -> imageSW;
            case "W" -> imageW;
            case "NW" -> imageNW;
            default -> null;
        };
        if (shootDrops) {
            image2 = imageDrops;
        }
        else {
            image2 = null;
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawImage(image2,x,y - gamePanel.tileSize,gamePanel.tileSize,gamePanel.tileSize,null);
    }
}
