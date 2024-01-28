package com.leohabrom.java.game1.config;

import com.leohabrom.java.game1.main.GamePanel;

import java.io.*;

public class Score {
    GamePanel gamePanel;
    public Score(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void writeScore(int i) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(gamePanel.score));
            writer.write(String.valueOf(i));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int readScore() {
        int out;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(gamePanel.score));
            out = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (IOException e) {
            out =0;
        }
        return out;
    }


}
