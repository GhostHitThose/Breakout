package com.maxrenner.levelsystem;

import com.sun.javafx.scene.control.behavior.TwoLevelFocusComboBehavior;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LevelManager {

    private static File file;
    private static ArrayList<String> levels;

    // store what level we are on and how many levels there are
    // using text files load levels
    // if player dies give them set gamestate to dead
    // if player breaks all blocks go to next level

    public static void loadLevels(){
        file = new File("levels.txt");
        try {
            if(file.createNewFile()){
                System.out.println("Level data not found...");
                System.out.println("Creating level data...");
                FileWriter w = new FileWriter(file);
                LevelCreator levelCreator = new LevelCreator(w);
                w.close();
            }

            FileReader r = new FileReader(file);
            Scanner s = new Scanner(r);
            StringBuilder level = new StringBuilder();
            levels = new ArrayList<>();
            while(s.hasNextLine()){
                String nextLine = s.nextLine();
                level.append(nextLine);
            }
            String levelStr = level.toString();
            String[] tempLevels = levelStr.split("\\|");


            for(int i = 1; i < tempLevels.length; i++){
                levels.add(tempLevels[i]);
            }

            System.out.println(levels);

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
