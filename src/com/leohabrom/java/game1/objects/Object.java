package com.leohabrom.java.game1.objects;

import com.leohabrom.java.game1.entity.Location;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
    public BufferedImage image0,image1,image2,image3,image4,image5;
    public int worldX, worldY;
    Location location = new Location();

    public Location getLocation() {
        return location;
    }

    Rectangle hitBox;
    public boolean collectable = true;
}
