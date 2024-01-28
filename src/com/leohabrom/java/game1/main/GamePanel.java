package com.leohabrom.java.game1.main;

import com.leohabrom.java.game1.entity.Player;
import com.leohabrom.java.game1.objects.ObjectManager;
import com.leohabrom.java.game1.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 4;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;


    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldHeight = maxWorldRow * tileSize;
    public final int worldWidth = maxWorldCol * tileSize;

    //TPS
    int TPS = 120;
    String tpsCount = "0";

    //SYSTEM
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    public MouseHandler mouseHandler = new MouseHandler();
    public TileManager tileManager = new TileManager(this);
    public ObjectManager objectManager;
    MainMenu mainMenu = new MainMenu(this);
    EscapeMenu escapeMenu = new EscapeMenu(this);
    public boolean running = false;
    public boolean started = true;
    private boolean escKeySwitch = true;
    public JFrame window;

    Sound music = new Sound();
    Sound sound = new Sound();
    private boolean mKeySwitch = true;
    private boolean musicSwitch = true;

    //Files
    public File score;


    //ENTITY
    public Player player = new Player(this,keyHandler);
    public CollisionChecker collisionChecker = new CollisionChecker(this);



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.addMouseListener(mouseHandler);
    }
    public void setWindow(JFrame window) {
        this.window = window;
    }

    public void setupGame() {
        try {
            Files.createDirectories(Paths.get(System.getenv("APPDATA")+"/.testgame/"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File gameDir = new File(System.getenv("APPDATA")+"/.testgame/");
        if (!gameDir.exists()){
            System.exit(0);
        }
        score = new File(gameDir, "score.txt");
        objectManager = new ObjectManager(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / TPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        setupGame();

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta = delta + (currentTime - lastTime) / drawInterval;
            timer = timer + (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta = delta - 1;
                drawCount = drawCount + 1;
            }

            if (timer >= 1000000000.0) {
                tpsCount = String.valueOf(drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {
        if (running) {
            player.update();
            objectManager.update();
        }
        else if (started) {
            mainMenu.update();
            mainMenu.updateRectangle();
        }
        else {
            escapeMenu.update();
        }
        if (keyHandler.escPressed && escKeySwitch) {
            escKeySwitch = false;
            started = false;
            running = !running;
        } else if (!keyHandler.escPressed && !escKeySwitch) {
            escKeySwitch = true;
        }
        else {
            escapeMenu.update();
        }
        if (keyHandler.mPressed && mKeySwitch) {
            mKeySwitch = false;
            if (musicSwitch) {
                playMusic(0);
            } else {
                stopMusic();
            }
        } else if (!keyHandler.mPressed && !mKeySwitch) {
            mKeySwitch = true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        if (running) {
            tileManager.draw(g2);
            objectManager.draw(g2);
            player.draw(g2);
        }
        else if (started) {
            mainMenu.draw(g2);
        }
        else {
            escapeMenu.draw(g2);
        }
        g2.drawString("TPS: " + tpsCount, screenWidth - tileSize, tileSize);
        g2.dispose();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
        musicSwitch = false;

    }
    public void stopMusic() {
        music.stop();
        musicSwitch = true;
    }
    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
}
