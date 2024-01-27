package com.leohabrom.java.game1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class MapGenerator {
    public static void main(String[] args) throws IOException {
        BufferedImage image;
        int[][] rgb;
        image = ImageIO.read(Objects.requireNonNull(MapGenerator.class.getResourceAsStream("/maps/map.png")));
        rgb = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                rgb[i][j] = convertRGBtoMap(image.getRGB(i,j));
                System.out.print(image.getRGB(i,j));
                System.out.print(" ");
            }
            System.out.println();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("map.txt"));
        for (int i = 0; i < 50; i++) {
            writer.write(String.valueOf(rgb[0][i]));
            for (int j = 1; j < 50; j++) {
                writer.write(" ");
                writer.write(String.valueOf(rgb[j][i]));
            }
            writer.write("\n");
        }
        writer.close();
    }
    private static int convertRGBtoMap(int i) {
        return switch (i) {
            case -1 -> 2;
            case -16777216 -> 0;
            case -65536 -> 1;
            case -16744690 -> 3;
            default -> 0;
        };
    }
}
