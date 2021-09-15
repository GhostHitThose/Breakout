package com.maxrenner.levelsystem;

import com.sun.javafx.scene.control.behavior.TwoLevelFocusComboBehavior;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LevelManager {

    private static ArrayList<String> levels;
    private static ArrayList<String[]> levelData;

    // store what level we are on and how many levels there are
    // using text files load levels
    // if player dies give them set gamestate to dead
    // if player breaks all blocks go to next level

    public static void loadLevels(){
        File file = new File("levels.dat");
        try {
            if(file.createNewFile()){
                System.out.println("Level data not found...");
                System.out.println("Creating level data...");
                FileWriter w = new FileWriter(file);
                LevelCreator.loadLevels(w);
                w.close();
            }

            FileReader r = new FileReader(file);
            Scanner s = new Scanner(r);
            StringBuilder level = new StringBuilder();
            while(s.hasNextLine()){
                String nextLine = s.nextLine();
                level.append(nextLine);
            }
            String levelStr = level.toString();
            String[] tempLevels = levelStr.split("\\|");


            levels = new ArrayList<>(Arrays.asList(tempLevels).subList(1, tempLevels.length));

            levelData = new ArrayList<>();

            for (String value : levels) {
                levelData.add(value.split(":"));
            }


        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String[]> getLevelData(){ return levelData; }
}
