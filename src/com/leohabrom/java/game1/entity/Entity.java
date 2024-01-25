package com.leohabrom.java.game1.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public BufferedImage imageN,imageNE,imageE,imageSE,imageS,imageSW,imageW,imageNW;
    public String direction;
    public Rectangle hitBox;
    public boolean collisionOn = false;
}
