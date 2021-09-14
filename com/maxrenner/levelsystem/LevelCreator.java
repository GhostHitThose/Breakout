package com.maxrenner.levelsystem;

import java.io.FileWriter;
import java.io.IOException;

public class LevelCreator {

    public LevelCreator(FileWriter w){
        int[][] levels = new int[][] {levelOne(w), levelTwo(w), levelThree(w), levelFour(w), levelFive(w)};


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

    public int[] levelOne(FileWriter w){

        return new int[]{
                0,0,1,1,1,1,1,1,1,1,0,0,
                0,0,1,1,1,1,1,1,1,1,0,0,
                0,0,1,1,1,1,1,1,1,1,0,0
        };
    }

    public int[] levelTwo(FileWriter w){

        return new int[] {
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,1,1,1,1,1,1,1,1,1,1,0
        };
    }

    public int[] levelThree(FileWriter w){

        return new int[] {
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,1,1,1,1,1,1,1,1,1,1,0
        };
    }

    public int[] levelFour(FileWriter w){

        return new int[] {
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,1,1,1,1,1,1,1,1,1,1,0
        };
    }

    public int[] levelFive(FileWriter w){

        return new int[] {
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,1,1,1,1,1,1,0,0,0,
                0,1,1,1,1,1,1,1,1,1,1,0
        };
    }
}
