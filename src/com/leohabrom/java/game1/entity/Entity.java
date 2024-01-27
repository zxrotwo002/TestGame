package com.leohabrom.java.game1.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage imageN_1, imageNE_1, imageE_1, imageSE_1, imageS_1, imageSW_1, imageW_1, imageNW_1,
            imageN_2, imageNE_2, imageE_2, imageSE_2, imageS_2, imageSW_2, imageW_2, imageNW_2;
    public String direction;
    public Rectangle hitBox;
    public boolean collisionOn = false;
}
