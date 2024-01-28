package com.leohabrom.java.game1.main;

import com.leohabrom.java.game1.entity.Location;

import java.awt.*;

public class HitboxChecker {
    public boolean check(Rectangle h1, Rectangle h2, Location l1, Location l2) {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();
        r1.x = h1.x + l1.getWorldX();
        r1.y = h1.y + l1.getWorldY();
        r1.height = h1.height;
        r1.width = h1.width;
        r2.x = h2.x + l2.getWorldX();
        r2.y = h2.y + l2.getWorldY();
        r2.height = h2.height;
        r2.width = h2.width;
        return r1.intersects(r2) || r1.contains(r2) || r2.contains(r1);
    }
}
