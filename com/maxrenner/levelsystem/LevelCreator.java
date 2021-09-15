package com.maxrenner.levelsystem;

import java.io.FileWriter;
import java.io.IOException;

public class LevelCreator {

    public static void loadLevels(FileWriter w){
        int[][] levels = new int[][] {levelOne(), levelTwo(), levelThree(), levelFour(), levelFive()};


        for (int[] level : levels) {
            StringBuilder currentLevel = new StringBuilder();
            currentLevel.append("|");
            for (int j = 0; j < level.length; j++) {
                if ((j) % 12 == 0 && j != 0) {
                    currentLevel.append(":").append(level[j]);
                } else {
                    currentLevel.append(level[j]);
                }
            }

            try {
                w.write(currentLevel.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int[] levelOne(){

        return new int[]{
                0,0,1,1,1,1,1,1,1,1,0,0,
                0,0,1,1,1,1,1,1,1,1,0,0,
                0,0,1,1,1,1,1,1,1,1,0,0
        };
    }

    private static int[] levelTwo(){

        return new int[] {
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,1,1,1,1,1,1,1,1,1,1,0
        };
    }

    private static int[] levelThree(){

        return new int[] {
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,1,1,1,1,1,1,1,1,1,1,0
        };
    }

    private static int[] levelFour(){

        return new int[] {
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,1,1,1,1,1,1,1,1,1,1,0
        };
    }

    private static int[] levelFive(){

        return new int[] {
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,1,1,1,1,1,1,1,1,1,1,0
        };
    }
}
