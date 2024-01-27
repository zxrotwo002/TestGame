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
    BufferedImage imageDrops_1, imageDrops_2, imageDrops_3;
    boolean shootDrops = false;
    boolean sprite = false;
    int dropShoot = 0;
    int counter = 0;
    int counter2 = 0;
    public final int screenX;
    public final int screenY;
    public Player (GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - gamePanel.tileSize/2;
        screenY = gamePanel.screenHeight/2 - gamePanel.tileSize/2;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            imageN_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_n.png")));
            imageNE_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_ne.png")));
            imageE_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_e.png")));
            imageSE_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_se.png")));
            imageS_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_s.png")));
            imageSW_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_sw.png")));
            imageW_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_w.png")));
            imageNW_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_nw.png")));
            imageN_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_n_2.png")));
            imageNE_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_ne_2.png")));
            imageE_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_e_2.png")));
            imageSE_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_se_2.png")));
            imageS_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_s_2.png")));
            imageSW_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_sw_2.png")));
            imageW_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_w_2.png")));
            imageNW_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_nw_2.png")));
            imageDrops_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/drops.png")));
            imageDrops_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/drops_2.png")));
            imageDrops_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/drops_3.png")));
        } catch (IOException e) {
            Logger.getGlobal().log(Level.INFO, e.getMessage());
        }
    }
    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 19;
        worldY = gamePanel.tileSize * 21;
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
        if (keyHandler.shiftPressed) {
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

        if (w && worldY >= 0) {
            worldY = worldY - speed * speedMultiplier;
        }
        if (a && worldX >= 0) {
            worldX = worldX - speed * speedMultiplier;
        }
        if (s && worldY <= gamePanel.worldHeight - gamePanel.tileSize) {
            worldY = worldY + speed * speedMultiplier;
        }
        if (d && worldX <= gamePanel.worldHeight - gamePanel.tileSize) {
            worldX = worldX + speed * speedMultiplier;
        }

        if (counter < 20) {
            counter++;
        } else {
            counter = 0;
            sprite = !sprite;
        }

        if (counter2 < 20) {
            counter2++;
            dropShoot = 1;
        }
        else if (counter2 < 40) {
            counter2++;
            dropShoot = 2;
        }
        else if (counter2 < 60) {
            counter2++;
            dropShoot = 0;
        }
        else {
            counter2 = 0;
        }

    }
    public void draw(Graphics2D g2) {
        BufferedImage image2;
        BufferedImage image;
        if (sprite) {
            image = switch (direction) {
                case "N" -> imageN_1;
                case "NE" -> imageNE_1;
                case "E" -> imageE_1;
                case "SE" -> imageSE_1;
                case "S" -> imageS_1;
                case "SW" -> imageSW_1;
                case "W" -> imageW_1;
                case "NW" -> imageNW_1;
                default -> null;
            };
        } else {
            image = switch (direction) {
                case "N" -> imageN_2;
                case "NE" -> imageNE_2;
                case "E" -> imageE_2;
                case "SE" -> imageSE_2;
                case "S" -> imageS_2;
                case "SW" -> imageSW_2;
                case "W" -> imageW_2;
                case "NW" -> imageNW_2;
                default -> null;
            };
        }
        if (shootDrops) {
            if (dropShoot == 0) {
                image2 = imageDrops_3;
            }
            else if (dropShoot == 1) {
                image2 = imageDrops_2;
            }
            else {
                image2 = imageDrops_1;
            }
        }
        else {
            image2 = null;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawImage(image2, screenX, screenY - gamePanel.tileSize,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawString(String.valueOf(counter2), 100, 100);
    }
}
