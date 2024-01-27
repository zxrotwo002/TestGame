package com.leohabrom.java.game1.tile;

import com.leohabrom.java.game1.entity.Player;
import com.leohabrom.java.game1.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNum;
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/maps/map_0.txt");
    }
    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_0.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_1.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_2.png")));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_3.png")));
        } catch (IOException e) {
            Logger.getGlobal().log(Level.INFO, e.getMessage());
        }
    }
    public void loadMap(String mapPath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            for (int i = 0; i < gamePanel.maxWorldRow; i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < gamePanel.maxWorldCol; j++) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[j]);
                    mapTileNum[j] [i] = num;

                }

            }
            bufferedReader.close();

        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, e.getMessage());
        }
    }

    public void draw(Graphics2D g2) {
        int size = gamePanel.tileSize;
        Player player = gamePanel.player;

        for (int i = 0; i < gamePanel.maxWorldCol; i++) {
            for (int j = 0; j < gamePanel.maxWorldRow; j++) {
                int worldX = i * size;
                int worldY = j * size;
                int screenX = worldX - player.worldX + player.screenX;
                int screenY = worldY - player.worldY + player.screenY;
                int tileNum = mapTileNum[i][j];
                if (worldX + size > player.worldX - player.screenX &&
                        worldX - size < player.worldX + player.screenX &&
                        worldY + size > player.worldY - player.screenY &&
                        worldY - size < player.worldY + player.screenY) {
                    g2.drawImage(tiles[tileNum].image, screenX, screenY, size, size, null);
                }
            }
        }
    }
}
