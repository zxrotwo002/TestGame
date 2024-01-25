package com.leohabrom.java.game1.main;

import com.leohabrom.java.game1.entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.hitBox.x;
        int entityRightX = entity.x + entity.hitBox.x + entity.hitBox.width;
        int entityTopY = entity.y + entity.hitBox.y;
        int entityBottomY = entity.y + entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = entityLeftX / gamePanel.tileSize;
        int entityRightCol = entityRightX / gamePanel.tileSize;
        int entityTopRow = entityTopY / gamePanel.tileSize;
        int entityBottomRow = entityBottomY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "N":
                entityTopRow = (entityTopY - entity.speed) / gamePanel.tileSize;
                if (entityTopRow > (gamePanel.maxScreenRow - 1)) entityTopRow = (gamePanel.maxScreenRow - 1);
                if (entityRightCol > (gamePanel.maxScreenCol - 1)) entityRightCol = (gamePanel.maxScreenCol - 1);
                if (entityLeftCol > (gamePanel.maxScreenCol - 1)) entityLeftCol = (gamePanel.maxScreenCol - 1);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "NE":
                entityTopRow = (entityTopY - entity.speed) / gamePanel.tileSize;
                entityRightCol = (entityRightX + entity.speed) / gamePanel.tileSize;
                if (entityTopRow > (gamePanel.maxScreenRow - 1)) entityTopRow = (gamePanel.maxScreenRow - 1);
                if (entityRightCol > (gamePanel.maxScreenCol - 1)) entityRightCol = (gamePanel.maxScreenCol - 1);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "E":
                entityRightCol = (entityRightX + entity.speed) / gamePanel.tileSize;
                if (entityTopRow > (gamePanel.maxScreenRow - 1)) entityTopRow = (gamePanel.maxScreenRow - 1);
                if (entityRightCol > (gamePanel.maxScreenCol - 1)) entityRightCol = (gamePanel.maxScreenCol - 1);
                if (entityBottomRow > (gamePanel.maxScreenRow - 1)) entityBottomRow = (gamePanel.maxScreenRow - 1);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "SE":
                entityBottomRow = (entityBottomY + entity.speed) / gamePanel.tileSize;
                entityRightCol = (entityRightX + entity.speed) / gamePanel.tileSize;
                if (entityRightCol > (gamePanel.maxScreenCol - 1)) entityRightCol = (gamePanel.maxScreenCol - 1);
                if (entityBottomRow > (gamePanel.maxScreenRow - 1)) entityBottomRow = (gamePanel.maxScreenRow - 1);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "S":
                entityBottomRow = (entityBottomY + entity.speed) / gamePanel.tileSize;
                if (entityRightCol > (gamePanel.maxScreenCol - 1)) entityRightCol = (gamePanel.maxScreenCol - 1);
                if (entityBottomRow > (gamePanel.maxScreenRow - 1)) entityBottomRow = (gamePanel.maxScreenRow - 1);
                if (entityLeftCol > (gamePanel.maxScreenCol - 1)) entityLeftCol = (gamePanel.maxScreenCol - 1);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "SW":
                entityBottomRow = (entityBottomY + entity.speed) / gamePanel.tileSize;
                entityLeftCol = (entityLeftX - entity.speed) / gamePanel.tileSize;
                if (entityBottomRow > (gamePanel.maxScreenRow - 1)) entityBottomRow = (gamePanel.maxScreenRow - 1);
                if (entityLeftCol > (gamePanel.maxScreenCol - 1)) entityLeftCol = (gamePanel.maxScreenCol - 1);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "W":
                entityLeftCol = (entityLeftX - entity.speed) / gamePanel.tileSize;
                if (entityTopRow > (gamePanel.maxScreenRow - 1)) entityTopRow = (gamePanel.maxScreenRow - 1);
                if (entityBottomRow > (gamePanel.maxScreenRow - 1)) entityBottomRow = (gamePanel.maxScreenRow - 1);
                if (entityLeftCol > (gamePanel.maxScreenCol - 1)) entityLeftCol = (gamePanel.maxScreenCol - 1);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "NW":
                entityTopRow = (entityTopY - entity.speed) / gamePanel.tileSize;
                entityLeftCol = (entityLeftX - entity.speed) / gamePanel.tileSize;
                if (entityTopRow > (gamePanel.maxScreenRow - 1)) entityTopRow = (gamePanel.maxScreenRow - 1);
                if (entityLeftCol > (gamePanel.maxScreenCol - 1)) entityLeftCol = (gamePanel.maxScreenCol - 1);
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
