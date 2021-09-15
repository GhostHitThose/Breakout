package com.maxrenner.gamefiles;

import com.maxrenner.FrameVariables;
import com.maxrenner.levelsystem.LevelID;
import com.maxrenner.levelsystem.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable, FrameVariables {

    private Ball ball;
    private Slider slider;
    public static GameState gameState;
    private boolean moveSlider = false;
    private LevelID level = LevelID.LEVEL_ONE;
    private String[] currentLevelData;
    private ArrayList<Block[]> blocks = new ArrayList<>();

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(FrameVariables.WIDTH,FrameVariables.HEIGHT);
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.darkGray);
        g.fillRect(0,0, FrameVariables.WIDTH, FrameVariables.HEIGHT);

        ball.draw(g);
        slider.draw(g);

        if(currentLevelData != null){
            for(int j = 0; j < currentLevelData.length; j++){
                String e = currentLevelData[j];
                for(int i = 0; i < e.length(); i++){
                    char num = e.charAt(i);
                    if(num != '0'){
                        if(!blocks.get(j)[i].isDestroyed){
                            blocks.get(j)[i].draw(g);
                            blocks.get(j)[i].isDrawn = true;
                        } else {
                            blocks.get(j)[i].isDrawn = false;
                        }
                    } else {
                        blocks.get(j)[i].isDrawn = false;
                    }
                }
            }
        }
    }

    public void initBlocks(){
        for(int i = 0; i < 3; i++){
            Block[] blockRow = new Block[12];
            for(int j = 0; j < 12; j++){
                Block block = new Block();
                block.x = j*50-5;
                block.y = 50+i*50-5;
                blockRow[j] = block;
            }
            blocks.add(blockRow);
        }
    }

    public void run(){
        gameState = GameState.STARTING;

        // init
        ball = new Ball();
        slider = new Slider();

        LevelManager.loadLevels();

        initBlocks();

        gameState = GameState.RUNNING;

        long last = System.nanoTime();
        double delta = 0, ns = 1000000000/60.0;

        while(gameState == GameState.RUNNING){
            long now = System.nanoTime();
            delta += (now-last)/ns;
            last = now;

            while(delta >= 1){
                update();
                delta--;
            }

            render();
        }

        System.exit(0);
    }

    public void changeDirection(char direction){
        slider.changeDirection(direction);
        moveSlider = true;
    }

    public void keyReleased(){
        moveSlider = false;
    }

    public void update(){
        ball.move();
        ball.checkCollision(slider.getX(), slider.getY(), slider.getW(), slider.getH(), blocks);
        if(moveSlider)
            slider.move();

        currentLevelData = getLevelData(level);
    }

    public void render(){ repaint(); }

    public String[] getLevelData(LevelID level){
        String[] currentLevelData;

        switch (level) {
            case LEVEL_ONE:
                currentLevelData = LevelManager.getLevelData().get(0);
                break;
            case LEVEL_TWO:
                currentLevelData = LevelManager.getLevelData().get(1);
                break;
            case LEVEL_THREE:
                currentLevelData = LevelManager.getLevelData().get(2);
                break;
            case LEVEL_FOUR:
                currentLevelData = LevelManager.getLevelData().get(3);
                break;
            case LEVEL_FIVE:
                currentLevelData = LevelManager.getLevelData().get(4);
                break;
            default:
                currentLevelData = null;
        }
        return currentLevelData;
    }
}
