package com.maxrenner.gamefiles;

import com.maxrenner.Application;
import com.maxrenner.FrameVariables;
import com.maxrenner.levelsystem.LevelID;
import com.maxrenner.levelsystem.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable, FrameVariables {

    private Ball ball;
    private Slider slider;
    public static GameState gameState;
    private boolean moveSlider = false;
    private LevelID level = LevelID.LEVEL_ONE;
    private String[] currentLevelData;
    private final ArrayList<Block[]> blocks = new ArrayList<>();
    private final BufferedImage image = new BufferedImage(FrameVariables.WIDTH, FrameVariables.HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static double deltaTime = 0;

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(FrameVariables.WIDTH,FrameVariables.HEIGHT);
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

        for(Block[] b : blocks){
            for(Block e : b){
                System.out.println(e.y);
            }
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
        double ns = 1000000000/60.0;

        while(gameState == GameState.RUNNING){
            long now = System.nanoTime();
            deltaTime += (now-last)/ns;
            last = now;

            while(deltaTime >= 1){
                update();
                deltaTime--;
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

    public void render(){
        if(Application.app.getBufferStrategy() == null){
            Application.app.createBufferStrategy(3);
        }

        BufferStrategy bs = Application.app.getBufferStrategy();

        renderGraphics(bs);

        bs.show();

    }

    private void renderGraphics(BufferStrategy bs){
        Graphics2D graphics = (Graphics2D) bs.getDrawGraphics();

        Graphics2D g = (Graphics2D) image.getGraphics();

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

        g.dispose();

        graphics.drawImage(image, 8, 32, FrameVariables.WIDTH, FrameVariables.HEIGHT, null);

        graphics.dispose();
    }

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
