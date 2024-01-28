package com.leohabrom.java.game1.objects;

import com.leohabrom.java.game1.config.Score;
import com.leohabrom.java.game1.entity.Entity;
import com.leohabrom.java.game1.entity.Player;
import com.leohabrom.java.game1.main.GamePanel;
import com.leohabrom.java.game1.main.HitboxChecker;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ObjectManager {
    GamePanel gamePanel;
    Coin[] coin;
    int counter = 0;
    int counter2 = 0;
    int coinCount = 0;
    int animation = 0;
    int coinOffset = 0;
    int coinSpeed = 600;
    int maxCoins = 20;
    int level = 0;
    int points;
    Score score;
    HitboxChecker hitboxChecker = new HitboxChecker();
    public ObjectManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        score = new Score(gamePanel);
        coin = new Coin[maxCoins];
        points = score.readScore();
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

        if (counter2 < coinSpeed) {
            counter2++;
        }
        else {
            counter2 = 0;
            if (coinCount < maxCoins) {
                createNewCoin();
            }
        }
        if (points % 20 == 19) {
            score.writeScore(points + 1);
            points = score.readScore();
            for (int i = coinCount-1; i >= 0; i--) {
                coin[i] = null;
                coinCount--;
            }
            gamePanel.tileManager.newMap();
            gamePanel.player.setDefaultValues();
            level++;
            gamePanel.playSoundEffect(3);
        }
    }

    private void createNewCoin() {
        Random randomX = new Random();
        Random randomY = new Random();
        int worldTileX = randomX.nextInt(50);
        int worldTileY = randomY.nextInt(50);
        int worldX = worldTileX * gamePanel.tileSize;
        int worldY = worldTileY * gamePanel.tileSize;
        if (gamePanel.tileManager.getMapTileNum(worldTileX,worldTileY) == 3 || gamePanel.tileManager.getMapTileNum(worldTileX,worldTileY) == 1) {
            coin[coinCount] = new Coin(gamePanel);
            coin[coinCount].getLocation().setWorldX(worldX);
            coin[coinCount].getLocation().setWorldY(worldY);
            coinCount++;
            System.out.println("New Coin (" + (coinCount) + ") created");
        }
        else createNewCoin();
    }

    public void checkCoin(Entity entity) {
        for (int i = 0; i < coinCount; i++) {
            if(hitboxChecker.check(coin[i].hitBox, entity.hitBox, coin[i].getLocation(), entity.getLocation())) {
                for (int j = i; j < coinCount; j++) {
                    if (j<coinCount-1) {
                        coin[j] = coin[j+1];
                    } else coin[j] = null;
                }
                coinCount--;
                score.writeScore(points + 1);
                points = score.readScore();
                gamePanel.playSoundEffect(2);
            }
        }

    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < coinCount; i++) {
            BufferedImage coinImage = switch (animation) {
                case 0 -> coin[i].image0;
                case 1 -> coin[i].image1;
                case 2 -> coin[i].image2;
                case 3 -> coin[i].image3;
                case 4 -> coin[i].image4;
                case 5 -> coin[i].image5;
                default -> null;
            };
            Player player = gamePanel.player;
            int screenX = coin[i].getLocation().getWorldX() - player.getLocation().getWorldX() + player.screenX;
            int screenY = coin[i].getLocation().getWorldY() - player.getLocation().getWorldY() + player.screenY - coinOffset * 2;
            g2.drawImage(coinImage,screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        int pointsX = (gamePanel.maxScreenCol - 1) * gamePanel.tileSize;
        int pointsY = gamePanel.tileSize/2;
        g2.drawString("Points: " + points,pointsX , pointsY);
        g2.drawString("Coins: " + coinCount,pointsX , pointsY + gamePanel.tileSize);
        g2.drawString("Level: " + level,pointsX , 2* gamePanel.tileSize);
    }
}
