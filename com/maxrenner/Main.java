package com.maxrenner;

import com.maxrenner.levelsystem.LevelManager;

public class Main {
    public static void main(String[] args){
        new Application();
        LevelManager.loadLevels();
    }
}
