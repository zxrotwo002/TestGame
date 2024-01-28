package com.leohabrom.java.game1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class MapReader {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Objects.requireNonNull(MapReader.class.getResourceAsStream("/maps/map.txt"));
        int[][] rgb;
        rgb = new int[50][50];
        File imageFile = new File("map.png");
        BufferedImage image = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        for (int i = 0; i < 50; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < 50; j++) {
                String[] numbers = line.split(" ");
                int num = Integer.parseInt(numbers[j]);
                rgb[j][i] = num;
            }
        }
        bufferedReader.close();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                image.setRGB(i,j,convertMapToRGB(rgb[i][j]));
            }
        }

        ImageIO.write(image,"png",imageFile);
    }
    private static int convertMapToRGB(int i) {
        return switch (i) {
            case 2 -> -1;
            case 0 -> -16777216;
            case 1 -> -65536;
            case 3 -> -16744690;
            case 4 -> -38400;
            default -> -167776;
        };
    }
}
