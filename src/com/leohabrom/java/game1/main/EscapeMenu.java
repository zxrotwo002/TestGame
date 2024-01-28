package com.leohabrom.java.game1.main;

import java.awt.*;

public class EscapeMenu {
    GamePanel gamePanel;
    public EscapeMenu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void update() {

    }
    public void draw(Graphics2D g2) {
        gamePanel.tileManager.draw(g2);
        gamePanel.objectManager.draw(g2);
        gamePanel.player.draw(g2);
        int rule = AlphaComposite.SRC_OVER;
        Composite composite = AlphaComposite.getInstance(rule, 0.5F);
        g2.setColor(Color.black);
        g2.setComposite(composite);
        g2.fillRect(0,0,gamePanel.screenWidth,gamePanel.screenHeight);
        g2.setColor(Color.white);
        Composite composite1 = AlphaComposite.getInstance(rule, 1F);
        g2.setComposite(composite1);
    }
}
